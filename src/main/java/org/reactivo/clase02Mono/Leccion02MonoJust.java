package org.reactivo.clase02Mono;

import reactor.core.publisher.Mono;

public class Leccion02MonoJust {
    public static void main(String[] args){
        prueba2();
    }

    private static void prueba2() {
        getUsername(3).subscribe(s -> System.out.println(s),
                err -> {System.out.println("Error");}
        );
    }

    public static Mono<String> getUsername(Integer userId){
        return switch(userId){
            case 1 -> Mono.just("Gaspar Bianco");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Valor invalido"));
        };
    }

}
