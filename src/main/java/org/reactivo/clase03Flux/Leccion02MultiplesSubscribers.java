package org.reactivo.clase03Flux;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

import java.io.Serializable;

public class Leccion02MultiplesSubscribers {
    public static void main(String[] args) {
        Flux<? extends Serializable> publisherFlux = Flux.just(1, "dos", 3L, 0.4, true);

        publisherFlux.subscribe(Utils.subscriber("sub1"));

        publisherFlux
                .filter(i -> i.getClass() == Integer.class)
                .subscribe(Utils.subscriber("sub2"));
    }
}
