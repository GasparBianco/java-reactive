package org.reactivo.clase11Sinks;

import lombok.extern.slf4j.Slf4j;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@Slf4j
public class Leccion05MulticasDirectAllOrNothing {
    public static void main(String[] args) {
        prueba();
    }

    private static void prueba() {
        System.setProperty("reactor.bufferSize.small", "16");

        Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Utils.subscriber("sub1"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Utils.subscriber("sub2"));

        for (int i = 0; i < 100; i++) {
            log.info("item {}, result {}", i, sink.tryEmitNext(i));
            //Utils.awaitSeconds(1);
        }

        Utils.awaitSeconds(20);
    }
}
