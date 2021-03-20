package ru.sber.lesson3;

import java.net.SocketException;

public interface Service {
    int _CHAT_SERVICE_PORT_DEFAULT = 1818;
    String getName();
    void start() throws SocketException;
    void pause();
    void stop();
    boolean getIsRun();
    int getCountClients();
    short getServicePort();
}
