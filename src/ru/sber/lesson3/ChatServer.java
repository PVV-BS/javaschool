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
    private boolean IsRunning;
    private boolean IsPaused;
    private int Port;
    private Thread thread;


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
        while (IsRunning)
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
        if (IsRunning) {
            if (IsPaused)
                IsPaused = false;
            return;
        }
        IsRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void pause() {
        IsPaused = true;
    }

    @Override
    public void stop() {
        IsRunning = false;
    }

    @Override
    public boolean getIsRunning(){
        return IsRunning && !IsPaused;
    }

    @Override
    public int getCountClients()
    {
        return CountClients;
    }

    @Override
    public short getServicePort() {
        return Service.CHAT_SERVICE_PORT_DEFAULT;
    }
}
