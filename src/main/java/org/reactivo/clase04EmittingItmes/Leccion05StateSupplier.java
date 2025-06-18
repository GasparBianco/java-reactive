package org.reactivo.clase04EmittingItmes;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

public class Leccion05StateSupplier {
    public static void main(String[] args) {
        Flux.generate(
                () -> 0,
                (count, synchronousSink) ->
                        {
                            String name = Utils.faker().name().firstName();
                            synchronousSink.next(name);
                            count++;
                            if (count == 10 || name.contains("j")) {
                                synchronousSink.complete();
                            }
                            return count++;
                        }
                )
                .subscribe(Utils.subscriber());
    }
}
