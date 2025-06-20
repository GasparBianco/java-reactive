package org.reactivo.clase04operators;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Leccion04timeout {
    public static void main(String[] args) {
        Flux.just(1)
                .delayElements(Duration.ofMillis(500))
                .timeout(Duration.ofMillis(100), fallback())
                .subscribe(Utils.subscriber());

        Utils.awaitSeconds(10);
    }
    private static Flux<Integer> fallback(){
        return Flux.just(2)
                .delayElements(Duration.ofMillis(5000));
    }

}
