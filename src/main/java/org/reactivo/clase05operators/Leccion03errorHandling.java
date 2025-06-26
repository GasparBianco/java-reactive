package org.reactivo.clase05operators;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

public class Leccion03errorHandling {
    public static void main(String[] args) {
        Flux.range(1,5)
                .map((i) -> {throw new RuntimeException();})
                .onErrorReturn(-1)
                .subscribe(Utils.subscriber());
    }
}
