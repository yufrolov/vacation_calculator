package com.yufrolov.vacationcalculator.service.thirdparty;

import com.yufrolov.vacationcalculator.exception.ThirdPartyApiException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class IsDayOffClient {

    private final HttpClient httpClient;

    public IsDayOffClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String send(HttpRequest request) {
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new ThirdPartyApiException("Не удалось выполнить запрос: "+request.uri().toString(), e);
        }

    }
}
