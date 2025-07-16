package org.reactivo.clase11Sinks;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Leccion02SinkUniCast {
    public static void main(String[] args) {
        prueba();
    }

    private static void prueba() {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();
        flux.subscribe(Utils.subscriber());
        //flux.subscribe(Utils.subscriber()); Error, solo 1 sub con sink.many.unicast

        for (int i = 0; i < 10; i++) {
            sink.tryEmitNext(i);
            Utils.awaitSeconds(1);
        }
    }
}
