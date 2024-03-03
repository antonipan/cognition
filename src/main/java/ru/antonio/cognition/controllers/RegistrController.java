package ru.antonio.cognition.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api")
public class RegistrController {

    @RequestMapping(method = RequestMethod.GET)
    public String welcome () {
        return "forward:/api/api.html";
    }

}
