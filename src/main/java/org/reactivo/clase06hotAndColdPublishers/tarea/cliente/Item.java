package org.reactivo.clase06hotAndColdPublishers.tarea.cliente;

import lombok.Getter;

import java.util.List;

@Getter
public class Item {
    private final String name;
    private final String category;
    private final Integer quantity;
    private final Integer price;
    public Item(String body){
        List<String> objetos = List.of(body.split(":"));
        this.name = objetos.get(0);
        this.category = objetos.get(1);
        this.quantity = Integer.valueOf(objetos.get(2));
        this.price = Integer.valueOf(objetos.get(3));
    }
}
