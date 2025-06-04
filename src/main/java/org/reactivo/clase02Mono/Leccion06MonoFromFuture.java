package org.reactivo.clase02Mono;

import org.reactivo.common.Utils;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Leccion06MonoFromFuture {
    public static void main(String[] args) throws InterruptedException {
        //no es lazy
        Mono.fromFuture(getName());

        // lazy
        Mono.fromFuture(() -> getName()).subscribe(Utils.subscriber());
        Utils.awaitSeconds(1);
    }

    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Generando nombre");
            return Utils.faker().name().firstName();
        });
    }
}
