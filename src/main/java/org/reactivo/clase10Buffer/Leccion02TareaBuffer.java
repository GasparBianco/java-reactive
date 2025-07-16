package org.reactivo.clase10Buffer;

import lombok.extern.slf4j.Slf4j;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Leccion02TareaBuffer {

    record BookOrder(String genre, int price) {
    }

    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(200))
                .map(i -> new BookOrder(Utils.faker().book().genre(), Utils.faker().number().numberBetween(10, 100)))
                .buffer(Duration.ofSeconds(5))
                .subscribe(new SubscriberLeccion02());

        Utils.awaitSeconds(60);

    }
}

