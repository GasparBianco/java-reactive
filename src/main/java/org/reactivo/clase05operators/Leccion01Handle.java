package org.reactivo.clase05operators;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

public class Leccion01Handle {
    public static void main(String[] args) {
        tarea();
    }

    private static void tarea(){
        Flux.<String>generate((synchronousSink -> {
            synchronousSink.next(Utils.faker.country().name());
        })).handle((item, sink) -> {
            sink.next(item);
            if(item.equalsIgnoreCase("canada")){
                sink.complete();
            }
        }).subscribe(Utils.subscriber());
    }

    private static void demo() {
    /*
   1 -> -2
   4 -> no enviarlo
   7 -> error
     */

        Flux.range(1, 10)
                .handle((item, sink) -> {
                    switch(item){
                        case 1 -> sink.next(-2);
                        case 4 -> {}
                        case 7 -> throw new RuntimeException();
                        default -> sink.next(item);
                    }
                }).subscribe(Utils.subscriber());
    }
}
