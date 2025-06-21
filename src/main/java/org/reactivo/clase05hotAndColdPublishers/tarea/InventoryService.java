package org.reactivo.clase05hotAndColdPublishers.tarea;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.reactivo.clase05hotAndColdPublishers.tarea.cliente.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class InventoryService implements Subscriber<Item> {
    private final Logger log = LoggerFactory.getLogger(InventoryService.class);
    private HashMap<String, Integer> stock = new HashMap<>();

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Item item) {
        stock.merge(item.getCategory(), 500 - item.getQuantity(), (anterior, nuevo) -> anterior - item.getQuantity());
        log.info(stock.toString());
    }

    @Override
    public void onError(Throwable throwable) {
        log.error(throwable.toString());
    }

    @Override
    public void onComplete() {
        log.info("Inventory completed");
    }
}
