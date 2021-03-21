package ru.sber.lesson4;

import org.mockito.internal.matchers.Null;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Replacer {
    private final int _BUFFER_CAPACITY = 256;
    private ByteBuffer Buffer;

    Replacer()
    {
        Buffer = ByteBuffer.allocate(_BUFFER_CAPACITY);
    }

    public void replace(InputStream inputStream, byte replacedSymbol, ConversionResultReceiver conversionResultReceiver) throws IOException {
        // split data in Morphemes on words
        while (true)
        {
            int len = inputStream.read(Buffer.array());
            if (len <= 0)
                break;
            int index = 0;
            int start = 0;
            while (len > index)
            {
                byte symbol = Buffer.get(index);
                if (symbol == replacedSymbol)
                {
                    Buffer.put(index, (byte) 0x20);
                }
                index++;
            }
            Buffer.position(len);
            conversionResultReceiver.ReceiveConversion(Buffer);
            Buffer.clear();
        }
    }
}
