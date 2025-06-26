package org.reactivo.clase06hotAndColdPublishers;

import org.reactivo.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Leccion02HotPublisher {
    private static final Logger log = LoggerFactory.getLogger(Leccion02HotPublisher.class);

    public static void main(String[] args) {

        var movieFlux = movieStream().share();

        Utils.awaitSeconds(2);

        movieFlux
                .take(4)
                .subscribe(Utils.subscriber("sam"));

        Utils.awaitSeconds(3);

        movieFlux
                .take(3)
                .subscribe(Utils.subscriber("mike"));


        Utils.awaitSeconds(15);

    }

    private static Flux<String> movieStream() {
        return Flux.generate(
                        () -> {
                            log.info("received the request");
                            return 1;
                        },
                        (state, sink) -> {
                            var scene = "movie scene " + state;
                            log.info("playing {}", scene);
                            sink.next(scene);
                            return ++state;
                        }
                )
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }

}
