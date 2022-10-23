package com.yufrolov.vacationcalculator.service;

import com.yufrolov.vacationcalculator.service.isdayoffservice.IsDayOffClient;
import com.yufrolov.vacationcalculator.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

//Этот класс использует isdayoff API для подсчета выходных дней в интервале. Подробнее здесь: https://www.isdayoff.ru/
@Service
public class WorkingDaysService {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static final String URI_TEMPLATE = "https://isdayoff.ru/api/getdata?date1=%s&date2=%s";

    private final IsDayOffClient isDayOffClient;

    public WorkingDaysService(IsDayOffClient isDayOffClient) {
        this.isDayOffClient = isDayOffClient;
    }


    public Integer getWorkingDays(Date startDate, Integer totalDays) {
        if (startDate == null) {
            return totalDays;
        }
        var endDate = DateUtils.getCountDateAfter(startDate, totalDays);
        var httpRequest = getWorkingDaysRequest(startDate, endDate);
        var response = isDayOffClient.send(httpRequest);
        return countWorkingDay(response);
    }

    private Integer countWorkingDay(String str) {
        return (int) str.chars().filter(x -> x == '0').count();
    }

    private HttpRequest getWorkingDaysRequest(Date startDate, Date endDate) {
        var uri = URI.create(String.format(URI_TEMPLATE, DATE_FORMAT.format(startDate), DATE_FORMAT.format(endDate)));
        return HttpRequest.newBuilder().uri(uri).GET().build();
    }
}
