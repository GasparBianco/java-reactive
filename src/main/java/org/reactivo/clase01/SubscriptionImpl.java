package org.reactivo.clase01;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
public class SubscriptionImpl implements Subscription {
    private boolean estaCancelada;
    private final Subscriber<? super String> subscriber;
    private final Faker faker;
    private final int CANTIDAD_MAXIMA = 10;
    private int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber){
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long cantidadSolicitada) {
        if (estaCancelada){
            return;
        } else if (cantidadSolicitada > CANTIDAD_MAXIMA) {
            subscriber.onError(new RuntimeException());
            estaCancelada = true;
            return;
        }
        System.out.println("subscriber solicito " + cantidadSolicitada + " items");
        for (int i = 0; i < cantidadSolicitada && i < CANTIDAD_MAXIMA; i++) {
            count++;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            subscriber.onNext(faker.internet().emailAddress());
        }
        if (count == CANTIDAD_MAXIMA){
            subscriber.onComplete();
            estaCancelada = true;
        }
    }

    @Override
    public void cancel() {
        System.out.println("Subscriber cancel");
        estaCancelada = true;
    }
}
