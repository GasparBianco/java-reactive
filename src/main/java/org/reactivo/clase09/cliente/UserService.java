package org.reactivo.clase09.cliente;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class UserService {

    private static final Map<String, Integer> userTable = Map.of(
            "sam", 1,
            "mike", 2,
            "jake", 3
    );

    public static Flux<User> getAllUsers() {
        return Flux.fromIterable(userTable.entrySet())
                .map(entry -> new User(entry.getValue(), entry.getKey()));
    }

    public static Mono<Integer> getUserId(String username){
        return Mono.fromSupplier(() -> userTable.get(username));
    }

}
