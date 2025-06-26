package org.reactivo.clase05operators;

import org.reactivo.clase05operators.cliente.ExternalServerCliente;
import org.reactivo.common.Utils;

public class Tarea {
    public static void main(String[] args) {
        ExternalServerCliente cliente = new ExternalServerCliente();

        for (int i = 0; i <4 ; i++) {

            cliente.getProduct(i).subscribe(Utils.subscriber());
        }

        Utils.awaitSeconds(10);
    }
}
