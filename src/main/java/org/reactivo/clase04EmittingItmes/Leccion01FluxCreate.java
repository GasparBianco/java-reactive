package org.reactivo.clase04EmittingItmes;

import org.reactivo.clase03Flux.tarea.SubscriberImpl;
import org.reactivo.clase04EmittingItmes.helper.GeneradorDeNombres;
import org.reactivo.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Leccion01FluxCreate {
    private static final Logger log = LoggerFactory.getLogger(Leccion01FluxCreate.class);
    public static void main(String[] args) {

        GeneradorDeNombres generadorDeNombres = new GeneradorDeNombres();
        // Este Flux.create no es lazy, en la leccion 03 vemos como hacerlo lazy
        Flux<String> publisher = Flux.create(generadorDeNombres);
        publisher.subscribe(Utils.subscriber());

        log.info("Publisher creado y subscriptor subscripto");

        for (int i = 0; i < 10; i++) {
            generadorDeNombres.generar();
        }

    }
}

