package org.reactivo.clase03Flux;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Leccion09Defer {
    public static void main(String[] args) {
        Stream<Integer> stream = IntStream.rangeClosed(1,10).mapToObj((i)-> {
            System.out.println(i);
            return i;
        });
        //Flux.fromStream(stream).subscribe(Utils.subscriber());

        Flux.defer(() -> Flux.fromStream(stream)).subscribe(Utils.subscriber());
    }
}
