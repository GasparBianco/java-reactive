package org.reactivo.clase04EmittingItmes.helper;

import org.reactivo.common.Utils;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class GeneradorDeNombres implements Consumer<FluxSink<String>> {
    private FluxSink<String> sink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        sink = stringFluxSink;
    }

    public void generar(){
        //System.out.println("Generando nombre");
        sink.next(Utils.faker().name().firstName());
    }
}
