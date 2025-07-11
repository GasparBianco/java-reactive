package org.reactivo.clase03Flux.tarea;

import org.reactivo.clase03Flux.tarea.SubscriberImpl;
import org.reactivo.clase03Flux.cliente.ExternalServerCliente;
import org.reactivo.common.Utils;

public class Leccion10Tarea {
    public static void main(String[] args) {
        ExternalServerCliente cliente = new ExternalServerCliente();
        SubscriberImpl subscriber = new SubscriberImpl();
        cliente.getStock().subscribe(subscriber);

        Utils.awaitSeconds(30);
    }
}
