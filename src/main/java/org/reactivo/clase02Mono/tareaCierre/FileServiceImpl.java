package org.reactivo.clase02Mono.tareaCierre;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl {

    private static final String basePath = "";
    public static Mono<String> read(String nombreArchivo) throws IOException {
        return Mono.fromCallable(() -> Files.readString(Path.of(basePath + nombreArchivo))
        );
    }

    public static Mono<Void> write(String nombreArchivo, String contenido){
        return Mono.fromRunnable(() -> escribirArchivo(basePath + nombreArchivo, contenido));
    }

    private static void escribirArchivo(String nombreArchivo, String contenido){
        try {
            Files.writeString(Path.of(nombreArchivo), contenido);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Mono<Void> eliminarArchivo(String nombreArchivo){
        return Mono.fromRunnable(() -> eliminar(basePath + nombreArchivo));
    }

    private static void eliminar(String nombreArchivo){
        try {
            Files.delete(Path.of(nombreArchivo));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
