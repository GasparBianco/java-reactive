package org.reactivo.clase04EmittingItmes;

import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

public class Leccion04Generate {
    public static void main(String[] args) {
        Flux.<String>generate(synchronousSink ->
                        {
            synchronousSink.next(Utils.faker().name().firstName());
            }
        ).takeUntil(
                (name) -> {
                    return name.contains("j");
                    }
                )
                .subscribe(Utils.subscriber());
    }
}
