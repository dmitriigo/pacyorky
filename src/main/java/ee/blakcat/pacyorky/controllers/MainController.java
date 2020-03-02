package ee.blakcat.pacyorky.controllers;

import ee.blakcat.pacyorky.services.updateData.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private UpdateService updateService;

    @Autowired
    public MainController(UpdateService updateService) {
        this.updateService = updateService;
    }

    @GetMapping ("/")
    public String index () {
        updateService.updateAll();
        return "index";
    }

}
