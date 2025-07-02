package org.reactivo.clase10;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;

@Slf4j
public class SubscriberLeccion02 implements Subscriber<List<Leccion02TareaBuffer.BookOrder>> {

    private HashMap<String, Integer> informe = new HashMap<>();

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(List<Leccion02TareaBuffer.BookOrder> bookOrder) {
        bookOrder.forEach((i) -> informe.merge(i.genre(), i.price(), Integer::sum));
        log.info(informe.toString());
        informe.clear();
    }

    @Override
    public void onError(Throwable throwable) {
        log.error(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("Completed");
    }
}
