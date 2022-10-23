package com.yufrolov.vacationcalculator.service;

import com.yufrolov.vacationcalculator.exception.ThirdPartyApiException;
import com.yufrolov.vacationcalculator.service.isdayoffservice.IsDayOffClient;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsDayOffClientTest {

    @Test
    void sendTrowExceptionTest() throws IOException, InterruptedException {
        var e = new IOException();
        var httpClientMock = Mockito.mock(HttpClient.class);
        var httpRequestMock = Mockito.mock(HttpRequest.class);
        Mockito.when(httpClientMock.send(ArgumentMatchers.any(), ArgumentMatchers.any())).thenThrow(e);
        var service = new IsDayOffClient(httpClientMock);
        var eThrow = assertThrows(ThirdPartyApiException.class, () -> service.send(httpRequestMock));
        assertEquals("Не удалось выполнить запрос", eThrow.getMessage());
    }
}
