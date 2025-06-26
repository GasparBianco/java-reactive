package org.reactivo.clase09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Leccion02ConcatWith {
    public static void main(String[] args) {
        Flux.range(6,5)
                .doOnSubscribe((a) -> log.info("Subscribe al segundo publisher"))
                .concatWith(Flux.range(1,5))
                .doOnNext((i) -> log.info(String.valueOf(i)))
                .blockLast();
    }
}
