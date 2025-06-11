package org.reactivo.clase03Flux;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

public class Leccion05Log {
    public static void main(String[] args) {
        Flux.range(1,1)
                .map((i) -> Utils.faker().name().firstName())
                .log("Nombre del logger")
                .subscribe(Utils.subscriber());
    }
}
