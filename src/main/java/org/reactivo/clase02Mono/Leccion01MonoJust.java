package org.reactivo.clase02Mono;

import org.reactivo.common.Utils;
import reactor.core.publisher.Mono;

public class Leccion01MonoJust {
    public static void main(String[] args){
        prueba1();
    }

    private static void prueba1() {
        getUsername(1).subscribe(Utils.subscriber());
        getUsername(2).subscribe(Utils.subscriber());
        getUsername(3).subscribe(Utils.subscriber());
    }


    public static Mono<String> getUsername(Integer userId){
        return switch(userId){
            case 1 -> Mono.just("Gaspar Bianco");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Valor invalido"));
        };
    }

}
