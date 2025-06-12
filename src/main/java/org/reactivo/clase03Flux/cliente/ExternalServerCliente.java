package org.reactivo.clase03Flux.cliente;

import org.reactivo.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServerCliente extends AbstractHttpClient {
    public Flux<String> getNames(){
        return this.httpClient
                .get()
                .uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }

    public Flux<String> getStock(){
        return this.httpClient
                .get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString();
    }

}
