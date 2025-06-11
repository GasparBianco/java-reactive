package org.reactivo.clase03Flux;

import org.reactivo.clase03Flux.cliente.ExternalServerCliente;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClient;

public class Leccion07ServicioExterno {
    public static void main(String[] args) {
        ExternalServerCliente cliente = new ExternalServerCliente();
        cliente.getNames().subscribe(Utils.subscriber("sub1"));
        cliente.getNames().subscribe(Utils.subscriber("sub2"));
        Utils.awaitSeconds(6);
    }
}
