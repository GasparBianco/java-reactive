package org.reactivo.clase04operators.cliente;

import org.reactivestreams.Publisher;
import org.reactivo.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ExternalServerCliente extends AbstractHttpClient {
    public Flux<String> getProduct(Integer id){
        return this.httpClient
                .get()
                .uri("/demo03/product/" + id)
                .responseContent()
                .asString()
                .timeout(Duration.ofMillis(2000), timeout(id))
                .switchIfEmpty(empty(id));
    }

    private Flux<String> timeout(Integer id){
        return httpClient
                .get()
                .uri("/demo03/timeout-fallback/product/" + id)
                .responseContent()
                .asString();
    }

    private Flux<String> empty(Integer id){
        return httpClient
                .get()
                .uri("/demo03/empty-fallback/product/" + id)
                .responseContent()
                .asString();
    }
}
