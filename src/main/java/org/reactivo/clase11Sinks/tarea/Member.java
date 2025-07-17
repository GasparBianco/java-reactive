package org.reactivo.clase11Sinks.tarea;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.reactivo.common.DefaultSubscriber;

@RequiredArgsConstructor
@Setter
@Slf4j
public class Member{
  private ChatRoom chatRoom;

  @Getter
  private final String name;

  public void says(String messege){
   chatRoom.postMessege(new Messege(messege, name));
  }

    public void receiveMessege(Messege messege) {
      log.info("{} recibio el mensaje de {}: {}", name, messege.poster(), messege.messege());
    }
}
