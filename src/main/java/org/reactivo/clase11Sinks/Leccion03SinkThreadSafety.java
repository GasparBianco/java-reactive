package org.reactivo.clase11Sinks;

import lombok.extern.slf4j.Slf4j;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class Leccion03SinkThreadSafety {
    public static void main(String[] args) {
        prueba2();
    }

    private static void prueba() {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();

        List<Object> lista = new ArrayList<>();
        flux.subscribe(lista::add);

        for (int i = 0; i < 1000; i++) {
            int j = i;
            CompletableFuture.runAsync(() -> {
                sink.tryEmitNext(j);
            });
        }
        Utils.awaitSeconds(2);
        log.info("tamanio lista {}", lista.size()); //no es thread safe
    }

    private static void prueba2() {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        Flux<Object> flux = sink.asFlux();

        List<Object> lista = new ArrayList<>();
        flux.subscribe(lista::add);

        for (int i = 0; i < 1000; i++) {
            int j = i;
            CompletableFuture.runAsync(() -> {
                sink.emitNext(j, ((signalType, emitResult) -> {
                    log.info("Fallo al entregar {}", j);
                    return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult); //retry cuando no entrega el item, ahora es thread safe
                }));
            });
        }
        Utils.awaitSeconds(2);
        log.info("tamanio lista {}", lista.size());
    }
}
