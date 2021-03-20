package ru.sber.lesson3;


import lombok.Data;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.*;
import java.lang.*;

// TODO: clients connection

@Data
public class ChatServer implements Service, Runnable {

    private int CountClients;
    private DatagramSocket ChatServer;
    private boolean IsRun;
    private boolean IsPaused;
    private int Port;
    Thread thread;


    ChatServer(int Port) {
        CountClients = 0;
        this.Port = Port;
    }

    private void createThread() {
    }

    @Override
    public String getName() {
        return "ChatServer";
    }

    @SneakyThrows
    @Override
    public void run() {
        ChatServer = new DatagramSocket(this.Port);
        while (IsRun)
        {
            try {
                Thread.sleep(100);
                if (IsPaused)
                    continue;

                // TODO: receiving and sending of messages

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start() {
        if (IsRun) {
            if (IsPaused)
                IsPaused = false;
            return;
        }
        IsRun = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void pause() {
        IsPaused = true;
    }

    @Override
    public void stop() {
        IsRun = false;
    }

    @Override
    public boolean getIsRun(){
        return IsRun && !IsPaused;
    }

    @Override
    public int getCountClients()
    {
        return CountClients;
    }

    @Override
    public short getServicePort() {
        return Service._CHAT_SERVICE_PORT_DEFAULT;
    }
}
