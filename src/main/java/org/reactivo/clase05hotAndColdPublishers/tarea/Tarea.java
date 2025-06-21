package org.reactivo.clase05hotAndColdPublishers.tarea;

import org.reactivo.clase05hotAndColdPublishers.tarea.cliente.ExternalServerCliente;
import org.reactivo.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang3.StringUtils.split;

public class Tarea {
    private static final Logger log = LoggerFactory.getLogger(Tarea.class);
    public static void main(String[] args) {

        InventoryService inventoryService = new InventoryService();
        VentasService ventasService = new VentasService();

        ExternalServerCliente cliente = new ExternalServerCliente();

        cliente.getFlux().subscribe(inventoryService);
        cliente.getFlux().subscribe(ventasService);

        Utils.awaitSeconds(60);

    }
}
