package org.reactivo.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Getter
@RequiredArgsConstructor
public class DefaultSubscriber<T> implements Subscriber<T> {

    private Subscription subscription;
    private final String name;
    private static final Logger log = LoggerFactory.getLogger(DefaultSubscriber.class);
    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T item) {
        log.info(name + " recibio: " + item);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error(name + ": Error: " + throwable);
    }

    @Override
    public void onComplete() {
        log.info(name + ": Finalizado");
    }
}
