package ru.antonio.cognition.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 *
 * @author Antonio
 * @version 1.1
 */
@Controller
@RequestMapping("/")
public class RegistrController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome () {
        return "<h2>Weclome to Cognition!</h2>";
    }



}
