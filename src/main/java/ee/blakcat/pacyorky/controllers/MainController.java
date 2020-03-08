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

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
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

    @GetMapping("/")
    public String index(Model model) {
        List<PacyorkyEvent> events = eventService.findAll();

        try {
            String o = objectMapper.writeValueAsString(events.stream().map(this::convertEventToDto).collect(Collectors.toList()));
            model.addAttribute("eventsis", o);
        } catch (Throwable hz) {
            hz.printStackTrace();
        }

        return "index";
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
        eventDto.setDate(formatter.format(sourceEvent.getStartTime()));
        eventDto.setDescription(sourceEvent.getDescription());
        eventDto.setEndTime(Objects.isNull(sourceEvent.getEndTime()) ? "" : formatter.format(sourceEvent.getEndTime()));
        eventDto.setLocation(sourceEvent.getPlace());
        eventDto.setTitle(sourceEvent.getName());
        eventDto.setLink("https://www.facebook.com/events/" + sourceEvent.getId());
        eventDto.setPacyorkyEventOwnerName(sourceEvent.getPacyorkyEventOwner().getName());
        return eventDto;
    }
}
