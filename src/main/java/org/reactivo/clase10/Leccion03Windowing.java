package org.reactivo.clase10;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Leccion03Windowing {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(500))
                .map(String::valueOf)
                .window(5)
                .map((i) -> {
                    i.subscribe(new SubscriberLeccion03());
                    return Flux.empty();
                })
                .blockLast();

    }


}
