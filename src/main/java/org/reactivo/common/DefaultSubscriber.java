package org.reactivo.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


@Getter
@RequiredArgsConstructor
public class DefaultSubscriber<T> implements Subscriber<T> {

    private Subscription subscription;
    private final String name;

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T item) {
        System.out.println(name + " recibio: " + item);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(name + ": Error: " + throwable);
    }

    @Override
    public void onComplete() {
        System.out.println(name + ": Finalizado");
    }
}
