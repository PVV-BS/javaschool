package ru.sber.lesson3;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.stubbing.OngoingStubbing;

import java.net.SocketException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceTest {

    @Test
    public void start() throws SocketException {
        Service service = new ChatServer(1818);
        service.start();
        assertTrue("The service is not run!", service.getIsRunning());
    }

    @Test
    public void pause() throws SocketException {
        Service service = new ChatServer(1819);
        service.start();
        service.pause();
        assertFalse("The service is still run!", service.getIsRunning());
    }

    @Test
    public void stop() throws SocketException {
        Service service = new ChatServer(1820);
        service.start();
        service.stop();
        assertFalse("The service is still run!", service.getIsRunning());
    }

    @Test
    public void getServicePort() {
        final short _PORT_FOR_TEST_MOCK = 1111;
        Service service = mock(ChatServer.class);
        when(service.getServicePort()).thenReturn(_PORT_FOR_TEST_MOCK);
        assertEquals("Wrong a service port!", service.getServicePort(), _PORT_FOR_TEST_MOCK);
    }
}