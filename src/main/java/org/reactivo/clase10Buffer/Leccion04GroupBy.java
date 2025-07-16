package org.reactivo.clase10Buffer;

import lombok.extern.slf4j.Slf4j;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class Leccion04GroupBy {

    record OrdenDeCompra(String item, String categoria, Integer precio) {
    }

    public static void main(String[] args) {
        tarea();
    }

    private static void tarea() {
        Flux.interval(Duration.ofMillis(2000))
                .map(i -> new OrdenDeCompra(Utils.faker().commerce().productName(), Utils.faker().commerce().department(), Utils.faker().number().numberBetween(10, 100)))
                .groupBy(i -> i.categoria)
                .flatMap(Leccion04GroupBy::procesarOrdenDeCompra)
                .subscribe(Utils.subscriber());
        Utils.awaitSeconds(40);
    }

    private static Flux<OrdenDeCompra> procesarOrdenDeCompra(GroupedFlux<String, OrdenDeCompra> ordenDeCompra) {
        log.info(ordenDeCompra.key());
        if (ordenDeCompra.key().equals("Kids")) {
            return ordenDeCompra
                    .flatMap(i -> Flux.just(i, i));
        } else if (ordenDeCompra.key().equals("Automotive")) {
            return ordenDeCompra.map((i) -> new OrdenDeCompra(i.item, i.categoria, i.precio + 100));
        }
        return ordenDeCompra;
    }

    private static void demo() {
        Flux.range(1, 30)
                .delayElements(Duration.ofMillis(1000))
                .groupBy(i -> i % 2)
                .flatMap(Leccion04GroupBy::procesarElementos)
                .blockLast();
    }

    private static Mono<Void> procesarElementos(GroupedFlux<Integer, Integer> groupedFlux) {
        log.info("Flux recibido {}", groupedFlux.key());
        return groupedFlux.doOnNext(i -> log.info("key: {}, item: {}", groupedFlux.key(), i))
                .then();
    }
}
