package org.reactivo.clase11Sinks;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Leccion04MultiCast {
    public static void main(String[] args) {
        prueba2();
    }

    private static void prueba() {
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Utils.subscriber("sub1"));
        flux.subscribe(Utils.subscriber("sub2"));

        for (int i = 0; i < 3; i++) {
            sink.tryEmitNext(i);
        }

        Utils.awaitSeconds(2);

        flux.subscribe(Utils.subscriber("sub3"));
        sink.tryEmitNext("Final");
    }

    private static void prueba2() {
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();

        for (int i = 0; i < 3; i++) {
            sink.tryEmitNext(i);
        }

        Utils.awaitSeconds(2);

        flux.subscribe(Utils.subscriber("sub1"));
        flux.subscribe(Utils.subscriber("sub2"));
        flux.subscribe(Utils.subscriber("sub3"));
        sink.tryEmitNext("Final");
        Utils.awaitSeconds(20);
    }
}
