package ru.sber.lesson3;

import java.net.SocketException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SocketException {
        Service chat = new ChatServer(Service._CHAT_SERVICE_PORT_DEFAULT);
        chat.start();
        System.out.println("For stop of ChatServer type \"quit\"...");
        Scanner scanner = new Scanner(System.in);
        while (chat.getIsRun())
        {
            if (scanner.hasNext("quit"))
            {
                chat.stop();
            }
        }
    }
}
