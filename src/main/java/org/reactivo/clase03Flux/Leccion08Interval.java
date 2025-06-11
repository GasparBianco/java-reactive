package org.reactivo.clase03Flux;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Leccion08Interval {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(500))
                //.map((i) -> Utils.faker().name().firstName())
                .subscribe(Utils.subscriber());

        Utils.awaitSeconds(10);
    }
}
