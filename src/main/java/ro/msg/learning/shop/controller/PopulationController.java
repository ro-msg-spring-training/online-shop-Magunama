package ro.msg.learning.shop.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.service.PopulationServiceImpl;

@RestController
@Profile(value = "test")
@RequestMapping("/api")
public class PopulationController {

    private final PopulationServiceImpl service;

    PopulationController(PopulationServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/populate")
    public String populate() {
        this.service.populate();
        return "Populated database!";
    }

    @GetMapping("/depopulate")
    public String depopulate() {
        this.service.depopulate();
        return "Depopulated database!";
    }
}
