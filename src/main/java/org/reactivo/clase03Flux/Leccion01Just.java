package org.reactivo.clase03Flux;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

import java.io.Serializable;

public class Leccion01Just {
    public static void main(String[] args) {
        Flux.just(1, "dos", 3L, 0.4, true).subscribe(Utils.subscriber());
    }
}
