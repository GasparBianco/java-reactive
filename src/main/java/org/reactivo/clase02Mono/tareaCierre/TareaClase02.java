package org.reactivo.clase02Mono.tareaCierre;

import org.reactivo.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TareaClase02 {

    private static final Logger log = LoggerFactory.getLogger(TareaClase02.class);

    public static void main(String[] args){
        //FileServiceImpl.write("prueba.txt", "Hello world").subscribe(Utils.subscriber("Escritor"));
        //FileServiceImpl.read("prueba.txt").subscribe(Utils.subscriber("Lector"));
        FileServiceImpl.eliminarArchivo("prueba.txt").subscribe(Utils.subscriber("Aniquilador"));
    }
}
