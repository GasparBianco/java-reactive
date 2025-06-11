package org.reactivo.clase02Mono;

import org.reactivo.clase02Mono.cliente.ExternalServerCliente;
import org.reactivo.common.AbstractHttpClient;
import org.reactivo.common.DefaultSubscriber;
import org.reactivo.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Leccion08ComunicacionNoBloqueante {
        private static final Logger log = LoggerFactory.getLogger(Leccion08ComunicacionNoBloqueante.class);
    public static void main(String[] args) {

        ExternalServerCliente cliente = new ExternalServerCliente();

        log.info("Comienzo");
        for (int i = 1; i < 100; i++) {
            //cliente.getProductName(i)
              //      .subscribe(Utils.subscriber());
            log.info(cliente.getProductName(i).block());
        }
        Utils.awaitSeconds(2);
    }
}
