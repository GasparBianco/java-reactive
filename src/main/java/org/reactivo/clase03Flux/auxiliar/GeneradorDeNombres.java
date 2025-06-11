package org.reactivo.clase03Flux.auxiliar;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GeneradorDeNombres {
    public static List<String> obtenerListaNombres(int cantidad){
        return IntStream
                .rangeClosed(1, cantidad)
                .mapToObj((i) -> generarNombre())
                .toList();
    }

    public static Flux<String> obtenerFluxNombres(int cantidad){
        return Flux.range(1, cantidad)
                .map((i) -> generarNombre());
    }

    private static String generarNombre(){
        Utils.awaitSeconds(1);
        return Utils.faker().name().firstName();
    }

}
