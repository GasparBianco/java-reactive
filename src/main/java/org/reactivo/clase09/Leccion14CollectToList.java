package org.reactivo.clase09;

import lombok.extern.slf4j.Slf4j;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Leccion14CollectToList {
    public static void main(String[] args) {
        /*Flux.range(1, 10)
                .delayElements(Duration.ofMillis(200))
                .collectList()
                .subscribe(Utils.subscriber());*/

        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lista.add(i);
        }
        log.info(lista.toString());

        log.info("Fuera del flux");
        Utils.awaitSeconds(10);
    }
}
