package ee.blakcat.pacyorky.controllers;

import ee.blakcat.pacyorky.services.EventService;
import ee.blakcat.pacyorky.services.updateData.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private UpdateService updateService;
    private EventService eventService;

    @Autowired
    public MainController(UpdateService updateService, EventService eventService) {

        this.updateService = updateService;
        this.eventService=eventService;
    }

    @GetMapping ("/")
    public String index (Model model) {
model.addAttribute("events", eventService.findAll());
        return "index";
    }

    @GetMapping ("/update")
    public String update () {
        updateService.updateAll();
        return "update";
    }

}
