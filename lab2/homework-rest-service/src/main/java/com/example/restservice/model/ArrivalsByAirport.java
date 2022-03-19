package com.example.restservice.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ArrivalsByAirport {
    // The given time interval must not be larger than seven days!

    @NotNull(message = "You must choose start date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "You must choose end date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @NotEmpty(message = "You must choose start time")
    private String startTime;

    @NotEmpty(message = "You must choose end time")
    private String endTime;

    @NotEmpty(message = "You must choose airport")
    private String airport;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }
}
