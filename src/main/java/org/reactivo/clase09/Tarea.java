package org.reactivo.clase09;

import lombok.extern.slf4j.Slf4j;
import org.reactivo.clase09.cliente.*;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class Tarea {

    record UserInfo(Integer userId, String username, Integer balance, List<Order> orders) {
    }

    public static void main(String[] args) {
        UserService.getAllUsers()
                .flatMap((user) -> Mono.zip(Mono.just(user.id()), Mono.just(user.username()), PaymentService.getUserBalance(user.id()), obtenerOrdenes(user.id())))
                        .map((i) -> new UserInfo(i.getT1(), i.getT2(), i.getT3(), i.getT4()))
                        .subscribe(Utils.subscriber());

        Utils.awaitSeconds(5);
    }

    private static Mono<List<Order>> obtenerOrdenes(Integer id) {
        return OrderService.getUserOrders(id)
                .collectList();
    }
}
