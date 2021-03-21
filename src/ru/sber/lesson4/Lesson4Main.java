package ru.sber.lesson4;

import java.io.*;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Lesson4Main {
    public static void main(String[] args) throws IOException {
        String classDirectory = Lesson4Main.class.
                getProtectionDomain().
                getCodeSource().
                getLocation().
                getPath();
        String[] path = classDirectory.split("/");
        classDirectory = "";
        // remove "/target/classes" from path
        for (int i = 1 /* skip first slash */; i < path.length-2; i++)
        {
            classDirectory = classDirectory.concat(path[i] + "/");
        }
        // now we have the root directory of the project; concat path to the source
        classDirectory = classDirectory.concat("src/ru/sber/lesson4/");
        Replacer replacer = new Replacer();
        InputStream inFile = Files.newInputStream(Paths.get(classDirectory.concat("Lesson4Main.java")));
        OutputStream outFile = Files.newOutputStream(Paths.get(classDirectory.concat("Lesson4Main.java.copy.txt")));
        ConversionResultReceiver ConversionResultReceiverImp = new ConversionResultReceiver() {
            @Override
            public void ReceiveConversion(ByteBuffer Buffer) throws IOException {
                outFile.write(Buffer.array(), 0, Buffer.position());
            }
        };
        replacer.replace(inFile, (byte)0x61/*a*/, ConversionResultReceiverImp);
    }

}
