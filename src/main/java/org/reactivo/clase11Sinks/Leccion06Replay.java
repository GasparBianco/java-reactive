package org.reactivo.clase11Sinks;

import lombok.extern.slf4j.Slf4j;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@Slf4j
public class Leccion06Replay {
    public static void main(String[] args) {
        prueba();
    }

    private static void prueba() {
        Sinks.Many<Object> sink = Sinks
                .many()
                .replay()
                .limit(1);
                //.limit(Duration.ofMillis(200));
                //.all();
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Utils.subscriber("sub1"));
        flux.subscribe(Utils.subscriber("sub2"));

        for (int i = 0; i < 3; i++) {
            sink.tryEmitNext(i);
        }

        Utils.awaitSeconds(2);

        flux.subscribe(Utils.subscriber("sub3"));
        sink.tryEmitNext("Fin");
    }
}
