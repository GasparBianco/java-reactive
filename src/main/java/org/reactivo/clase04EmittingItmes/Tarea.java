package org.reactivo.clase04EmittingItmes;

import org.reactivo.clase01.SubscriberImpl;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Tarea {
    public static Flux<String> leerLinea(Path path){
        return Flux.<String>generate(stringSynchronousSink -> {
            try {
                stringSynchronousSink.next(Files.readString(path));
                System.out.println("Leyendo archivo");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void main(String[] args) {
        Path path = Path.of("prueba.txt");
        SubscriberImpl sub = new SubscriberImpl();
        leerLinea(path).subscribe(sub);

        sub.getSubscription().request(1);
        sub.getSubscription().cancel();
        sub.getSubscription().request(1);
    }
}
