package org.reactivo.clase09CombiningPublishers.cliente;

import org.reactivo.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class ExternalServerCliente extends AbstractHttpClient {

    public Flux<Producto> getFullProduct(String id){
        return Flux.zip(getProduct(id), getReview(id), getPrice(id))
                .map(i -> new Producto(i.getT1(),i.getT2(), i.getT3()));
    }


    private Flux<String> getProduct(String id){
        return this.httpClient
                .get()
                .uri("/demo05/product/" + id)
                .responseContent()
                .asString();
    }

    private Flux<String> getPrice(String id){
        return this.httpClient
                .get()
                .uri("/demo05/price/" + id)
                .responseContent()
                .asString();
    }

    private Flux<String> getReview(String id){
        return this.httpClient
                .get()
                .uri("/demo05/review/" + id)
                .responseContent()
                .asString();
    }
}
