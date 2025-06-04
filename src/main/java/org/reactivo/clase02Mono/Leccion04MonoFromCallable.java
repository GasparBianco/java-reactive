package org.reactivo.clase02Mono;

import org.reactivo.common.Utils;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public class Leccion04MonoFromCallable {
    public static void main(String[] args){
        prueba4();
    }

    private static int sum(int[] numeros){
        System.out.println("Sumando: " + Arrays.toString(numeros));
        return Arrays.stream(numeros).sum();
    }
    private static  void prueba4(){
        int[] numeros = {1,2,3,4};
        Mono.fromCallable(() -> sum(numeros)) //No usar Mono.just para evitar hacer el procesamiento si no hay un subscriptor que lo requiera
        .subscribe(Utils.subscriber());
    }
}
