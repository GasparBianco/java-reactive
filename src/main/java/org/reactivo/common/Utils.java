package org.reactivo.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

public class Utils {
    public static final Faker faker = Faker.instance();

    public static <T> Subscriber<T> subscriber(){
        return new DefaultSubscriber<>("");
    }
    public static <T> Subscriber<T> subscriber(String name){
        return new DefaultSubscriber<>(name);
    }

    public static void main(String[] args) {
        Mono<Integer> mono = Mono.just(1);

        mono.subscribe(subscriber("Prueba"));
    }

    public static Faker faker(){
        return faker;
    }

    public static void awaitSeconds(int seconds){
        try{
            Thread.sleep(seconds * 1000);
        }catch (Exception e){}
    }
}
