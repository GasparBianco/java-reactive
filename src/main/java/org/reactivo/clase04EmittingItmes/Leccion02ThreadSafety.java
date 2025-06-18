package org.reactivo.clase04EmittingItmes;

import org.reactivo.clase04EmittingItmes.helper.GeneradorDeNombres;
import org.reactivo.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Leccion02ThreadSafety {
    private static final Logger log = LoggerFactory.getLogger(Leccion02ThreadSafety.class);
    public static void main(String[] args) {
        demo2();
    }

    private static void demo(){

        //Esto no es thread safe, es decir que se pueden (y van a) perder datos.
        // La lista deberia tener 10000 elementos pero no los tiene
        List<Integer> lista = new ArrayList<Integer>();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                lista.add(i);
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }
        Utils.awaitSeconds(3);
        log.info("Tamanio de la lista: {}", lista.size());
    }

    private static void demo2(){

        List<String> lista = new ArrayList<String>();
        GeneradorDeNombres generadorDeNombres = new GeneradorDeNombres();
        Flux<String> publisher = Flux.create(generadorDeNombres);
        publisher.subscribe(lista::add);
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                generadorDeNombres.generar();
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }
        Utils.awaitSeconds(3);
        log.info("Tamanio de la lista: {}", lista.size());
    }
}
