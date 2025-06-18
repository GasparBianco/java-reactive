package org.reactivo.clase04EmittingItmes;

import org.reactivo.clase01.SubscriberImpl;
import org.reactivo.clase04EmittingItmes.helper.GeneradorDeNombres;
import org.reactivo.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Leccion03ComportamientoDefault {
    private static final Logger log = LoggerFactory.getLogger(Leccion03ComportamientoDefault.class);
    public static void main(String[] args) {

        //usando el onRequest podemos hacerlo lazy
        Flux.<String>create(fluxSink -> {
            fluxSink.onRequest(request -> {
                for (int i = 0; i < request && !fluxSink.isCancelled(); i++) {
                    fluxSink.next(Utils.faker.name().firstName());
                }
            });
        }).subscribe(new SubscriberImpl());
    }
}
