package org.reactivo.clase02Mono;

import org.reactivo.common.Utils;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public class Leccion07MonoDefer {
    public static void main(String[] args){

        //De esta forma solo se crea el publisher cuando tiene un subscriber y se haga una request
        Mono.defer(() -> crearPublisher());
        //.subscribe(Utils.subscriber());
        //crearPublisher();
    }

    private static Mono<Integer> crearPublisher(){
        System.out.println("Creando publisher");
        Utils.awaitSeconds(2);
        int[] numeros = {1,2,3,4};
        return Mono.fromSupplier(() -> sum(numeros));
    }

    private static int sum(int[] numeros){
        System.out.println("Sumando: " + Arrays.toString(numeros));
        Utils.awaitSeconds(3);
        return Arrays.stream(numeros).sum();
    }
}
