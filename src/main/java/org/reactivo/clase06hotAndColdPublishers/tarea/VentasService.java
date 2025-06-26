package org.reactivo.clase06hotAndColdPublishers.tarea;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.reactivo.clase06hotAndColdPublishers.tarea.cliente.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class VentasService implements Subscriber<Item> {
    private final Logger log = LoggerFactory.getLogger(VentasService.class);
    private HashMap<String, Integer> gananciasPorCategoria = new HashMap<>();

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Item item) {
        gananciasPorCategoria.merge(item.getCategory(), item.getPrice(), Integer::sum);
        log.info(gananciasPorCategoria.toString());
    }

    @Override
    public void onError(Throwable throwable) {
        log.error(throwable.toString());
    }

    @Override
    public void onComplete() {
        log.info("Sales completed");
    }
}
