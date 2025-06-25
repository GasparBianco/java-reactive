package org.reactivo.practica.practica1;

import lombok.extern.slf4j.Slf4j;
import org.reactivo.common.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Integer tamanioPagina = 256;
        List<Boolean> resultado = new ArrayList<>();

//        Mono.fromCallable(BaseDeDatos::obtenerCantidadClientes)
//                .map((cantidadClientes) -> cantidadClientes/tamanioPagina)
//                .flatMapMany((c) -> {
//                    return Flux.range(0, c);
//                })
//                .doOnNext((i -> log.info("Inicio pagina {}", i)))
//                .flatMap((i) -> Flux.fromIterable(BaseDeDatos.obtenerClientes(tamanioPagina, i)))
//                .doOnNext((i) -> log.info("Cliente obtenido: {}", i))
//                .map(ServicioExterno::clasificar)
//                .onErrorContinue((ex,obj) -> log.error("Error"))
//                .map(resultado::add)
//                .subscribeOn(Schedulers.boundedElastic())
//                .blockLast();

//        Mono.fromCallable(BaseDeDatos::obtenerCantidadClientes)
//                .map(cantidadClientes -> cantidadClientes / tamanioPagina)
//                .flatMapMany(c -> Flux.range(0, c))
//                .concatMap((i) -> {
//                    log.info("Inicio pagina {}", i);
//                    return Flux.fromIterable(BaseDeDatos.obtenerClientes(tamanioPagina, i));
//                })
//                .flatMap((cliente) ->
//                        {
//                            log.info("Cliente obtenido: {}", cliente);
//                           return Mono.fromCallable(() -> ServicioExterno.clasificar(cliente))
//                                    .subscribeOn(Schedulers.boundedElastic())
//                                    .onErrorResume(ex -> {
//                                        log.error("Error clasificando cliente {}", cliente);
//                                        return Mono.empty();
//                                    });
//                        }
//                )
//                .doOnNext(resultado::add)
//                .blockLast();


        Mono.fromCallable(BaseDeDatos::obtenerCantidadClientes)
                .subscribeOn(Schedulers.boundedElastic())
                .map(cantidadClientes -> cantidadClientes / tamanioPagina)
                .flatMapMany(c -> Flux.range(0, c))
                .concatMap(i -> {
                    log.info("Inicio pagina {}", i);
                    return Mono.fromCallable(() -> BaseDeDatos.obtenerClientes(tamanioPagina, i))
                            .subscribeOn(Schedulers.boundedElastic())
                            .flatMapMany(Flux::fromIterable);
                })
                .flatMap(cliente -> {
                    log.info("Cliente obtenido: {}", cliente);
                    return Mono.fromCallable(() -> ServicioExterno.clasificar(cliente))
                            .subscribeOn(Schedulers.boundedElastic())
                            .onErrorResume(ex -> {
                                log.error("Error clasificando cliente {}", cliente);
                                return Mono.empty();
                            });
                })
                .doOnNext(resultado::add)
                .blockLast();


    }

    private static class ServicioExterno{
        public static boolean clasificar(String cliente){
            Utils.awaitSeconds(1);
            if(cliente.contains("J")){
                throw new RuntimeException("Error al clasificar");
            } else return !cliente.contains("B");
        }
    }

    private static class BaseDeDatos{
        public static Integer obtenerCantidadClientes(){
            return 1000000;
        }

        public static List<String> obtenerClientes(Integer tamanioPagina, Integer numeroPagina){
            List<String> lista = new ArrayList<>();
            for (int i = 0; i < tamanioPagina; i++) {
                lista.add(Utils.faker().name().firstName());
            }
            return lista;
        }
    }
}
