package org.reactivo.clase01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


@Getter
public class SubscriberImpl implements Subscriber<String> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(String email) {
        System.out.println("Email recibido: " + email);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error");
    }

    @Override
    public void onComplete() {
        System.out.println("Finalizado");
    }
}
