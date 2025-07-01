package org.reactivo.clase09.cliente;

public record Order(Integer userId,
                    String productName,
                    Integer price) {
}
