package org.reactivo.clase10;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Leccion01Buffer {
    public static void main(String[] args) {
        /*Flux.interval(Duration.ofMillis(200))
                .buffer()
                .subscribe(Utils.subscriber());*/

        /*Flux.interval(Duration.ofMillis(200))
                .take(10)
                .buffer()
                .subscribe(Utils.subscriber());*/


        /*Flux.interval(Duration.ofMillis(200))
                .buffer(Duration.ofMillis(500)
                .subscribe(Utils.subscriber());*/

        /*Flux.interval(Duration.ofMillis(200))
                .buffer(3)
                .subscribe(Utils.subscriber());*/


        Flux.interval(Duration.ofMillis(200))
                .bufferTimeout(3, Duration.ofMillis(1000))
                .subscribe(Utils.subscriber());
    }
}
