package org.reactivo.clase11Sinks;

import lombok.extern.slf4j.Slf4j;
import org.reactivo.common.Utils;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Slf4j
public class Leccion01SinkOne {
    public static void main(String[] args) {
        prueba2();
    }

    private static void prueba() {
        Sinks.One<Object> sink = Sinks.one();
        sink.tryEmitValue("Hola mundo");

        Mono<Object> mono = sink.asMono();

        mono.subscribe(Utils.subscriber("sub1"));
        mono.subscribe(Utils.subscriber("sub2"));
    }

    private static void prueba2() {
        Sinks.One<Object> sink = Sinks.one();
        sink.emitValue("Hola mundo", ((signalType, emitResult) -> {
            return false;
        }));

        sink.emitValue("Hola mundo", ((signalType, emitResult) -> {
            log.info("segundo item");
            log.info(signalType.name());
            log.info(emitResult.name());
            return false; //retry
        }));

        Mono<Object> mono = sink.asMono();

        mono.subscribe(Utils.subscriber("sub1"));
        mono.subscribe(Utils.subscriber("sub2"));
    }
}
