package org.reactivo.clase06hotAndColdPublishers;

import org.reactivo.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Leccion01ColdPublisher {
    private static final Logger log = LoggerFactory.getLogger(Leccion01ColdPublisher.class);

    public static void main(String[] args) {

        var flux = Flux.create(fluxSink -> {
            log.info("invocado");
            for (int i = 0; i < 30; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        }).delayElements(Duration.ofMillis(500))
                .share();


        flux.subscribe(Utils.subscriber("sub1"));
        Utils.awaitSeconds(2);
        flux.subscribe(Utils.subscriber("sub2"));

        Utils.awaitSeconds(10);

    }
}
