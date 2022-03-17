package com.example.restservice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

// HTTP requests are handled by a controller
// TODO: @RestController?
@Controller
// TODO: change name
public class FormController {
    // TODO: maybe different endpoints or just name
    @GetMapping("/form")
    // return the name of a view ("form" - responsible for rendering the html content)
    // Model jis passed to "form" template
    public String form(Model model) {
        model.addAttribute("new_form", new Form());
        return "form";
    }

    // receives the Form object that was populated by the form
    @PostMapping("/form")
    // The Form is a @ModelAttribute, so it is bound to the incoming form content
    public String greetingSubmit(@ModelAttribute Form newForm, Model model) {
        model.addAttribute("new_form", newForm);
        return "result";
    }
}
