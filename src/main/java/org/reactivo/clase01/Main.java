package org.reactivo.clase01;

import org.reactivestreams.Publisher;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Publisher<String> publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3L);
        Thread.sleep(2000);
        subscriber.getSubscription().request(2L);
    }
}