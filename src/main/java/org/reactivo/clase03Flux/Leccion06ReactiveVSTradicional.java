package org.reactivo.clase03Flux;

import org.reactivo.clase03Flux.auxiliar.GeneradorDeNombres;
import org.reactivo.common.Utils;

public class Leccion06ReactiveVSTradicional {
    public static void main(String[] args) {
        //System.out.println(GeneradorDeNombres.obtenerListaNombres(10));

        GeneradorDeNombres.obtenerFluxNombres(10)
                //.takeUntil((nombre) -> nombre.contains("J"))
                .subscribe(Utils.subscriber());
    }
}
