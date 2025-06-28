package org.reactivo.clase09;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

public class Leccion04Merge {

    public static void main(String[] args) {
        Flux<Integer> flux1 = Flux.range(1,5);
        Flux<Integer> flux2 = Flux.range(1,5);
        Flux.range(1,5)
                .mergeWith(flux1)
                .subscribe(Utils.subscriber());


        Flux.merge(flux1,flux2)
                .subscribe(Utils.subscriber());
    }
}
