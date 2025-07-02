package org.reactivo.clase10;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class SubscriberLeccion03 implements Subscriber<String>{

    private Path path;
    private static Integer contador = 0;
    private BufferedWriter bufferedWriter;

    @Override
    public void onSubscribe(Subscription subscription) {
        contador += 1;
        path = Path.of("informe-"+contador);
        try {
            bufferedWriter = Files.newBufferedWriter(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(String s){
            try {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("completed");
    }
}
