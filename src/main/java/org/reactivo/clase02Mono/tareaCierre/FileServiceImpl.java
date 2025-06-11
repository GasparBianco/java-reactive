package org.reactivo.clase02Mono.tareaCierre;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl {

    private static final String basePath = "";
    public static Mono<String> read(String nombreArchivo) throws IOException {
        return Mono.fromSupplier(() -> leerArchivo(basePath + nombreArchivo));
    }

    private static String leerArchivo(String nombreArchivo) {
        try {
            return Files.readString(Path.of(nombreArchivo));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Mono<Void> write(String nombreArchivo, String contenido){
        return Mono.fromSupplier(() -> escribirArchivo(basePath + nombreArchivo, contenido));
    }

    private static Void escribirArchivo(String nombreArchivo, String contenido){
        try {
            Files.writeString(Path.of(nombreArchivo), contenido);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Mono<Void> eliminarArchivo(String nombreArchivo){
        return Mono.fromSupplier(() -> eliminar(basePath + nombreArchivo));
    }

    private static Void eliminar(String nombreArchivo){
        try {
            Files.delete(Path.of(nombreArchivo));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
