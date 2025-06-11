package org.reactivo.clase03Flux;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

public class Leccion04Range {
    public static void main(String[] args) {
        //practicamente es un for i in range
        // Esto va a ir de 3 a 12, el segundo numero indica cantidad no hasta donde llega
        Flux.range(3, 10)
                .subscribe(Utils.subscriber());


        Flux.range(3, 10)
                .map((i) -> Utils.faker().name().firstName())
                .subscribe(Utils.subscriber());
    }
}
