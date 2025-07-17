package org.reactivo.clase11Sinks.tarea;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
public class ChatRoom {
    private Flux<Messege> chatHistory;
    private Sinks.Many<Messege> sink;

    public ChatRoom(){
        sink = Sinks.many().replay().all();
        chatHistory = sink.asFlux();
    }
    public void addMember(Member member){
        member.setChatRoom(this);

        //sink.tryEmitNext(new Messege(member.getName() + " se unio a la sala", "ChatRoom"));

        log.info("{} se unio a la sala", member.getName());
        chatHistory
                .filter((i) -> !i.poster().equals(member.getName()))
                .subscribe(member::receiveMessege);
    }

    public void postMessege(Messege messege) {
        sink.tryEmitNext(messege);
    }
}
