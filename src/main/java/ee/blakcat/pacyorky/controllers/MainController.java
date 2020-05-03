package ee.blakcat.pacyorky.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.services.EventService;
import ee.blakcat.pacyorky.services.updateData.UpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/api")
@EnableScheduling
public class MainController {
    private UpdateService updateService;
    private EventService eventService;
    private ObjectMapper objectMapper;
    @Value("${updateSecret}")
    private String updateKey;

    @Autowired
    public MainController(UpdateService updateService, EventService eventService, ObjectMapper objectMapper) {

        this.updateService = updateService;
        this.eventService = eventService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/events/{id}")
    public String oneEvent (@PathVariable String id) {
       String eventDto = null;
        try {
            eventDto=objectMapper.writeValueAsString(new EventDto(eventService.findById(id)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventDto;
    }

    @GetMapping("/events")
    public String index() {
        List<PacyorkyEvent> events = eventService.findAll();
        String eventsDTO= null;
        try {
            eventsDTO = objectMapper.writeValueAsString(events.stream().map(EventDto::new).collect(Collectors.toList()));
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return eventsDTO;
    }

    // manual update
//    @GetMapping ("/update")
//    public String update () {
//        updateService.updateAll();
//        return "update";
//    }

    //auto update
    @Scheduled(fixedRate = 1800000)
    public void autoUpdate() {
            updateService.updateAll();
    }

    @GetMapping ("/update")
    public boolean manualUpdate(@RequestParam String key) {
        if (key.equals(updateKey)) {
            try {
                updateService.updateAll();
                return true;
            } catch (Exception e) {
                return false;
            }

        }
        return false;
    }


}
