package com.yufrolov.vacationcalculator.service;

import com.yufrolov.vacationcalculator.exception.ThirdPartyApiException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//Этот класс использует isdayoff API для подсчета выходных дней в интервале. Подробнее здесь: https://www.isdayoff.ru/
@Service
public class WorkingDaysService {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static final String URI_TEMPLATE = "https://isdayoff.ru/api/getdata?date1=%s&date2=%s";

    private final HttpClient httpClient;

    public WorkingDaysService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Integer getWorkingDays(Date startDate, Integer totalDays) {
        var calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, totalDays);
        var endDate = calendar.getTime();
        var uri = URI.create(String.format(URI_TEMPLATE, DATE_FORMAT.format(startDate), DATE_FORMAT.format(endDate)));
        var httpRequest = HttpRequest.newBuilder().uri(uri).GET().build();
        try {
            var response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Integer numberWorkingDays = 0;
            for (int i = 0; i < response.body().length(); i++) {
                if (response.body().charAt(i) == '1') {
                    numberWorkingDays++;
                }
            }
            return numberWorkingDays;
        } catch (IOException | InterruptedException e) {
            throw new ThirdPartyApiException("Не удалось получить список выходных дней за период", e);
        }
    }
}
