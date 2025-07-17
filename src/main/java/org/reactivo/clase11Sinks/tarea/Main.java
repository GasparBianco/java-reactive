package org.reactivo.clase11Sinks.tarea;

import org.reactivo.common.Utils;

public class Main {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();
        Member gaspar = new Member("Gaspar");
        Member valot = new Member("valot");
        Member personaRandom = new Member("Perry el ornitorrinco");

        chatRoom.addMember(gaspar);
        chatRoom.addMember(valot);

        gaspar.says("Hola, como estas?");
        valot.says("Bien, vos?");

        Utils.awaitSeconds(3);

        chatRoom.addMember(personaRandom);

        personaRandom.says("Perdi el juego");
        gaspar.says("Uh, vamos a una sala privada");

    }
}
