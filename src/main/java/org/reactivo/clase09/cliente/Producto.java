package org.reactivo.clase09.cliente;


import lombok.ToString;

@ToString
public class Producto {
    private final String name;
    private final String review;
    private final String price;

    public Producto(String name, String review, String price){
        this.name = name;
        this.review = review;
        this.price = price;
    }
}
