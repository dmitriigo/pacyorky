package ee.blakcat.pacyorky.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.services.EventService;
import ee.blakcat.pacyorky.services.updateData.UpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            eventDto=objectMapper.writeValueAsString(convertEventToDto(eventService.findById(id)));
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
            eventsDTO = objectMapper.writeValueAsString(events.stream().map(this::convertEventToDto).collect(Collectors.toList()));
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

    private EventDto convertEventToDto(PacyorkyEvent sourceEvent) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        EventDto eventDto = new EventDto();
        eventDto.setId(sourceEvent.getId());
        eventDto.setDistrict(sourceEvent.getDistrict().name);
        eventDto.setDate(formatter.format(sourceEvent.getStartTime()));
        eventDto.setDescription(sourceEvent.getDescription());
        eventDto.setEndTime(Objects.isNull(sourceEvent.getEndTime()) ? "" : formatter.format(sourceEvent.getEndTime()));
        eventDto.setLocation(sourceEvent.getPlace());
        eventDto.setTitle(sourceEvent.getName());
        eventDto.setLink("https://www.facebook.com/events/" + sourceEvent.getId());
        eventDto.setEventOwner(sourceEvent.getPacyorkyEventOwner().getName());
        return eventDto;
    }
}
