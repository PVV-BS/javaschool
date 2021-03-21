package ru.sber.lesson4;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface ConversionResultReceiver {
    void ReceiveConversion(ByteBuffer Buffer) throws IOException;
}
