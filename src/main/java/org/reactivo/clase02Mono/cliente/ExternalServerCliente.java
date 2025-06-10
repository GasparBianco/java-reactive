package org.reactivo.clase02Mono.cliente;

import org.reactivo.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServerCliente extends AbstractHttpClient {
    public Mono<String> getProductName(int productId){
        return this.httpClient
                .get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .next();
    }

}
