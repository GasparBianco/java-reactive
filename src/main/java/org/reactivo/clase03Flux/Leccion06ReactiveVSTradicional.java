package org.reactivo.clase03Flux;

import org.reactivo.clase03Flux.auxiliar.GeneradorDeNombres;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Leccion06ReactiveVSTradicional {
    public static void main(String[] args) {
        //System.out.println(GeneradorDeNombres.obtenerListaNombres(10));
        Flux.range(1, 10)
                .flatMap(j -> Mono.fromCallable(() -> GeneradorDeNombres.obtenerListaNombres(10))
                        .subscribeOn(Schedulers.boundedElastic()))
                .flatMapIterable(lista -> lista)
                .subscribe(Utils.subscriber());
        GeneradorDeNombres.obtenerFluxNombres(10)
                //.takeUntil((nombre) -> nombre.contains("J"))
                .subscribe(Utils.subscriber());
    }
}
