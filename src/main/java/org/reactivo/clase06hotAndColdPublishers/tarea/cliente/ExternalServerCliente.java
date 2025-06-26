package org.reactivo.clase06hotAndColdPublishers.tarea.cliente;

import org.reactivo.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

import java.util.Objects;

public class ExternalServerCliente extends AbstractHttpClient {

    private Flux<Item> flux;

    public Flux<Item> getFlux(){
        if (Objects.isNull(flux)) {
            flux = getProduct();
        }
        return flux;
    }

    private Flux<Item> getProduct(){
        return this.httpClient
                .get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(Item::new)
                .publish()
                .refCount(2);
    }
}
