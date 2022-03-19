package com.example.restservice;

import com.example.restservice.model.Flight;
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

// One of the main differences is RestTemplate is synchronous and blocking i.e. when you do a rest call you need
// to wait till the response comes back to proceed further.
// But WebClient is complete opposite of this. The caller need not wait till response comes back.
// Instead, he will be notified when there is a response.

// HTTP requests are handled by a controller
// TODO: @RestController?
@Controller
// TODO: change name
public class FlightController {
    @GetMapping("/open-sky-stats")
    // return the name of a view ("form" - responsible for rendering the html content)
    // Model is passed to "form" template
    public String form(Model model) {
        model.addAttribute("new_flight", new Flight());
        return "form";
    }

    // receives the Flight object that was populated by the form
    @PostMapping("/open-sky-stats")
    // The Flight is a @ModelAttribute, so it is bound to the incoming form content
    public String submitForm(@Valid @ModelAttribute("new_flight") Flight newFlight, BindingResult bindingResult, Model model) throws IOException, JSONException {
        model.addAttribute("new_flight", newFlight);

        if (bindingResult.hasErrors()) {
            return "form";
        }
        long beginParam = getUnixTimestamp(newFlight.getStartDate(), newFlight.getStartTime());
        long endParam = getUnixTimestamp(newFlight.getEndDate(), newFlight.getEndTime());

        URL url = new URL("https://opensky-network.org/api/flights/all?begin=" + beginParam + "&end=" + endParam);
//        URL url = new URL("https://opensky-network.org/api/flights/all?begin=1647663480&end=1647663600");  // empty example
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        System.out.println("Query: " + "https://opensky-network.org/api/flights/all?begin=" + beginParam + "&end=" + endParam);

        System.out.println("Start: " + newFlight.getStartDate() + " " + newFlight.getStartTime());
        System.out.println("End: " + newFlight.getEndDate() + " " + newFlight.getEndTime());

        System.out.println("UNIX start time: " + getUnixTimestamp(newFlight.getStartDate(), newFlight.getStartTime()));
        System.out.println("UNIX end time: " + getUnixTimestamp(newFlight.getEndDate(), newFlight.getEndTime()));

        model.addAttribute("status", connection.getResponseCode());

        if (connection.getResponseCode() == 404) {
            return "errors/404";
        }

        switch (connection.getResponseCode()) {
            case HttpURLConnection.HTTP_OK:
                JSONArray response = getJsonResponse(connection);
                generateStats(response, model);
                return "result";

            case HttpURLConnection.HTTP_NOT_FOUND:
                return "errors/404";

            case HttpURLConnection.HTTP_BAD_REQUEST:
                model.addAttribute("error_body", connection.getErrorStream().toString());
                return "errors/400";

            default:
                return "error";
        }
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

    private void generateStats(JSONArray flights, Model model) {

        model.addAttribute("flights_number", flights.length());
        // avg/min/max flight duration (last seen - first seen)
        // distance to the departure airport (?)


        // another endpoint: Arrivals/Departures by Airport
    }
}
