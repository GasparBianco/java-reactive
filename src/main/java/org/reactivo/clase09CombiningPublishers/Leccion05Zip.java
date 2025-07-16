package org.reactivo.clase09CombiningPublishers;

import org.reactivo.clase09CombiningPublishers.cliente.ExternalServerCliente;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

public class Leccion05Zip {

    public static void main(String[] args) {
        ExternalServerCliente cliente = new ExternalServerCliente();
        Flux.range(1, 10)
                .map(String::valueOf)
                .flatMap(cliente::getFullProduct)
                .subscribe(Utils.subscriber());


        Utils.awaitSeconds(30);
    }
}
