package ee.blakcat.pacyorky.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.blakcat.pacyorky.dto.AddMailMessageDTO;
import ee.blakcat.pacyorky.dto.EventDTO;
import ee.blakcat.pacyorky.dto.MailUserDTO;
import ee.blakcat.pacyorky.dto.VariantDTO;
import ee.blakcat.pacyorky.models.PacyorkyEvent;
import ee.blakcat.pacyorky.services.pacyorky.EventService;
import ee.blakcat.pacyorky.services.pacyorky.UserService;
import ee.blakcat.pacyorky.services.updateData.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MainController {
    private final UpdateService updateService;
    private final EventService eventService;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(MainController.class);
    @Value("${updateSecret}")
    private String updateKey;

    @Autowired
    public MainController(UpdateService updateService, EventService eventService, ObjectMapper objectMapper, UserService userService) {
        this.updateService = updateService;
        this.eventService = eventService;
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @PostMapping("/add-mail")
    public AddMailMessageDTO addMailUser(@RequestBody MailUserDTO mailUserDTO) {
        boolean result = userService.addUser(mailUserDTO.geteMail(), mailUserDTO.getMailLang(), mailUserDTO.getMailSendPeriod());
        return new AddMailMessageDTO(result, mailUserDTO.getMailLang(), mailUserDTO.geteMail());
    }

    @GetMapping("/mail-variant")
    public String getVariants() {
        VariantDTO variantDTO = new VariantDTO();
        try {
            return objectMapper.writeValueAsString(variantDTO);
        } catch (JsonProcessingException e) {
            logger.error("error in mail variants, exception: " + e.toString());
        }
        return null;
    }

    @GetMapping("/events/{id}")
    public String oneEvent(@PathVariable String id) {
        String eventDto = null;
        try {
            eventDto = objectMapper.writeValueAsString(new EventDTO(eventService.findById(id)));
        } catch (Exception e) {
            logger.error("can not get one event id = " + id + ", exception: " + e.toString());
        }
        return eventDto;
    }

    @GetMapping("/events")
    public String index() {
        List<PacyorkyEvent> events = eventService.findAll();
        String eventsDTO = null;
        try {
            eventsDTO = objectMapper.writeValueAsString(events.stream().map(EventDTO::new).collect(Collectors.toList()));
        } catch (Throwable e) {
            logger.error("can not get events, exception: " + e.toString());
        }

        return eventsDTO;
    }


    @GetMapping("/update")
    public boolean manualUpdate(@RequestParam String key) {
        if (key.equals(updateKey)) {
            try {
                updateService.updateAll();
                return true;
            } catch (Exception e) {
                logger.error("update exception: " + e.toString());
                return false;
            }
        }
        return false;
    }
}
