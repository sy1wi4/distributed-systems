package com.example.restservice;

import com.example.restservice.model.ArrivalsByAirport;
import com.example.restservice.model.FlightsInTimeInterval;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;


@Controller
public class FlightController {

    @GetMapping("/open-sky-stats")
    public String start() {
        return "start";
    }

    @GetMapping("/open-sky-stats/flights-form")
    public String flightsForm(Model model) {
        model.addAttribute("time_interval", new FlightsInTimeInterval());
        return "flights-form";
    }

    @PostMapping("/open-sky-stats/flights-form")
    public String submitFlightsForm(@Valid @ModelAttribute("time_interval") FlightsInTimeInterval timeInterval, BindingResult bindingResult, Model model) throws IOException, JSONException {
        model.addAttribute("time_interval", timeInterval);

        if (bindingResult.hasErrors()) {
            return "flights-form";
        }
        long beginParam = getUnixTimestamp(timeInterval.getStartDate(), timeInterval.getStartTime());
        long endParam = getUnixTimestamp(timeInterval.getEndDate(), timeInterval.getEndTime());

        URL url = new URL("https://opensky-network.org/api/flights/all?begin=" + beginParam + "&end=" + endParam);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        return processResponse(connection, model, "flights-result");
    }

    @GetMapping("/open-sky-stats/arrivals-form")
    public String arrivalsForm(Model model) {
        model.addAttribute("arrival", new ArrivalsByAirport());
        return "arrivals-form";
    }

    @PostMapping("/open-sky-stats/arrivals-form")
    public String submitArrivalsForm(@Valid @ModelAttribute("arrival") ArrivalsByAirport arrival, BindingResult bindingResult, Model model) throws IOException, JSONException {
        model.addAttribute("arrival", arrival);

        if (bindingResult.hasErrors()) {
            return "arrivals-form";
        }
        long beginParam = getUnixTimestamp(arrival.getStartDate(), arrival.getStartTime());
        long endParam = getUnixTimestamp(arrival.getEndDate(), arrival.getEndTime());
        String airportParam = arrival.getAirport();

        URL url = new URL("https://opensky-network.org/api/flights/arrival?airport=" + airportParam + "&begin=" + beginParam + "&end=" + endParam);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        return processResponse(connection, model, "arrivals-result");
    }

    private String processResponse(HttpURLConnection connection, Model model, String resultHtml) throws IOException, JSONException {
        System.out.println(connection.getURL());

        switch (connection.getResponseCode()) {
            case HttpURLConnection.HTTP_OK:
                JSONArray response = getJsonResponse(connection);
                generateStats(response, model);
                return resultHtml;

            case HttpURLConnection.HTTP_NOT_FOUND:
                return "errors/404";

            case HttpURLConnection.HTTP_BAD_REQUEST:
                model.addAttribute("error_body", connection.getErrorStream().toString());
                return "errors/400";

            default:
                return "error";
        }
    }

    private void generateStats(JSONArray flights, Model model) throws JSONException {
        int minFlightDuration = Integer.MAX_VALUE;
        int maxFlightDuration = Integer.MIN_VALUE;
        int flightDurationSum = 0;
        int flightsCount = flights.length();

        for (int i = 0; i < flights.length(); i++) {

            // deal with null values
            if (flights.getJSONObject(i).optInt("lastSeen") == 0 || flights.getJSONObject(i).optInt("firstSeen") == 0) {
                flightsCount -= 1;
                continue;
            }

            int flightDuration = flights.getJSONObject(i).getInt("lastSeen") - flights.getJSONObject(i).getInt("firstSeen");

            minFlightDuration = Math.min(minFlightDuration, flightDuration);
            maxFlightDuration = Math.max(maxFlightDuration, flightDuration);
            flightDurationSum += flightDuration;
        }
        model.addAttribute("flights_number", flightsCount);
        model.addAttribute("min_flight_duration", convertUnixToHumanReadable(minFlightDuration));
        model.addAttribute("max_flight_duration", convertUnixToHumanReadable(maxFlightDuration));
        model.addAttribute("avg_flight_duration", convertUnixToHumanReadable(flightDurationSum / flightsCount));
    }

    private JSONArray getJsonResponse(HttpURLConnection connection) throws IOException, JSONException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        in.close();
        return new JSONArray(buffer.toString());
    }

    private long getUnixTimestamp(Date date, String time) {
        return date.getTime() / 1000L + Duration.between(LocalTime.MIN, LocalTime.parse(time)).toSeconds();
    }

    private String convertUnixToHumanReadable(int seconds) {
        return String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, (seconds % 60));
    }
}
