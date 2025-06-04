package org.reactivo.clase02Mono;

import org.reactivo.common.Utils;
import reactor.core.publisher.Mono;

import java.util.Arrays;

public class Leccion03MonoFromSupplier {
    public static void main(String[] args){
        prueba3();
    }

    private static  void prueba3(){
        System.out.println("entro al metodo prueba");
        int[] numeros = {1,2,3,4};
        //No usar Mono.just para evitar hacer el procesamiento si no hay un subscriptor que lo requiera,
        // el procesamiento debe ir dentro del constructor del Mono para que no se ejecute, el metodo
        // prueba3 si se esta ejecutando y se esta creando un publisher, pero no se esta ejecutando el metodo sum
        // (representando el procesamiento) hasta que un suscriber lo solicite
        Mono.fromSupplier(() -> sum(numeros))
                        .subscribe(Utils.subscriber());
    }

    private static int sum(int[] numeros){
        System.out.println("Sumando: " + Arrays.toString(numeros));
        return Arrays.stream(numeros).sum();
    }

}
