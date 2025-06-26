package org.reactivo.clase09;

import lombok.extern.slf4j.Slf4j;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class Leccion01StartWith {
    public static void main(String[] args) {
        Flux.range(6,5)
                .doOnSubscribe((a) -> log.info("Subscribe al segundo publisher"))
                .startWith(Flux.range(1,5))
                .doOnNext((i) -> log.info(String.valueOf(i)))
                .blockLast();
    }
}
