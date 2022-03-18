package com.example.restservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// One of the main differences is RestTemplate is synchronous and blocking i.e. when you do a rest call you need
// to wait till the response comes back to proceed further.
// But WebClient is complete opposite of this. The caller need not wait till response comes back.
// Instead, he will be notified when there is a response.

// HTTP requests are handled by a controller
// TODO: @RestController?
@Controller
// TODO: change name
public class FormController {
    @GetMapping("/open-sky-stats")
    // return the name of a view ("form" - responsible for rendering the html content)
    // Model is passed to "form" template
    public String form(Model model) {
        model.addAttribute("new_form", new Form());
        return "form";
    }

    // receives the Form object that was populated by the form
    @PostMapping("/open-sky-stats")
    // The Form is a @ModelAttribute, so it is bound to the incoming form content
    public String greetingSubmit(@ModelAttribute Form newForm, Model model) throws IOException, JSONException {
        model.addAttribute("new_form", newForm);

        URL url = new URL("https://opensky-network.org/api/flights/all?begin=1517227200&end=1517230800");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        model.addAttribute("status", connection.getResponseCode());
        JSONArray response = getJsonResponse(connection);
        return "result";
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
}
