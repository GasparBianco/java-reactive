package org.reactivo.clase09.cliente;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class OrderService {

    private static final Map<Integer, List<Order>> orderTable = Map.of(
            1, List.of(
                    new Order(1, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(10, 100)),
                    new Order(1, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(10, 100))
            ),
            2, List.of(
                    new Order(2, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(10, 100)),
                    new Order(2, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(10, 100)),
                    new Order(2, Utils.faker().commerce().productName(), Utils.faker().random().nextInt(10, 100))
            ),
            3, List.of()
    );

    public static Flux<Order> getUserOrders(Integer userId) {
        return Flux.fromIterable(orderTable.get(userId))
                .delayElements(Duration.ofMillis(500));
    }
}
