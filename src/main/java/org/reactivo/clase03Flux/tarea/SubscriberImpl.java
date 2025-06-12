package org.reactivo.clase03Flux.tarea;

import lombok.Getter;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Getter
public class SubscriberImpl implements Subscriber<String> {

    private Subscription subscription;
    private float saldo = 1000;
    private int cantidadAcciones = 0;
    private static final Logger log = LoggerFactory.getLogger(SubscriberImpl.class);

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(String valorAccion) {
        float valor = Float.parseFloat(valorAccion);
        if(valor <= 90){
            log.info("Valor de accion {}, Comprando accion.", valorAccion);
            if(valor > saldo){
                log.info("Saldo insuficiente.");
            }else{
                saldo -= valor;
                cantidadAcciones += 1;
                log.info("Accion comprada. Nuevo saldo: {}. Cantidad de acciones: {}", saldo, cantidadAcciones);
            }
        } else if (valor >=110) {
            log.info("Valor de accion {}, vendiendo todas las acciones", valorAccion);
            saldo += cantidadAcciones * valor;
            cantidadAcciones = 0;
            log.info("Acciones vendidas, nuevo saldo: {}", saldo);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable);
    }

    @Override
    public void onComplete() {
        System.out.println("Finalizado");
    }
}
