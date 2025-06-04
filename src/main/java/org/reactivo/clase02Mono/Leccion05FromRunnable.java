package org.reactivo.clase02Mono;

import org.reactivo.common.Utils;
import reactor.core.publisher.Mono;

public class Leccion05FromRunnable {
    public static void main(String[] args){
        prueba5();
    }

    private static void prueba5() {
        getProductName(2).subscribe(Utils.subscriber());
    }
    private static Mono<String> getProductName(int productId){
        if (productId == 1){
            return Mono.fromSupplier(() -> Utils.faker().commerce().productName());
        }

        return Mono.fromRunnable(() -> {System.out.println("Producto faltante. ID: " + productId);});
    }

}
