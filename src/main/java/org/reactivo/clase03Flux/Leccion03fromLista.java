package org.reactivo.clase03Flux;

import org.reactivo.clase01.SubscriberImpl;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class Leccion03fromLista {
    public static void main(String[] args) {
        List<String> lista = List.of("A", "B", "C", "D");
        //Flux.fromIterable(lista).subscribe(Utils.subscriber("iterable"));

        //Un stream solo se puede recorrer una vez (eso no tiene nada que ver con reactive)
        Stream stream = lista.stream();
        SubscriberImpl subscriber1 = new SubscriberImpl();
        Flux.fromStream(stream).subscribe(subscriber1);
        subscriber1.getSubscription().request(1L);

        //Flux.fromStream(stream).subscribe(Utils.subscriber("stream2")); //este lanza error, incluso si el subscriber anterior no recorrio el stream completo

        subscriber1.getSubscription().request(1L); //Este si puede volver a pedir, si creamos el flux con un stream solo acepta un subscriber

        //solucion para tener multiples subscribers con un stream

        Flux<String> flux = Flux.fromStream(() -> lista.stream());

        flux.subscribe(Utils.subscriber("stream1"));
        flux.subscribe(Utils.subscriber("stream2"));

    }
}
