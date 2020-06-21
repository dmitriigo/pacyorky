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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MainController {
    private final UpdateService updateService;
    private final EventService eventService;
    private final ObjectMapper objectMapper;
    private final UserService userService;
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
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/events/{id}")
    public String oneEvent(@PathVariable String id) {
        String eventDto = null;
        try {
            eventDto = objectMapper.writeValueAsString(new EventDTO(eventService.findById(id)));
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
                return false;
            }
        }
        return false;
    }

    @GetMapping("/confirm")
    public boolean confirm(@RequestParam String user, @RequestParam String token) {
       return userService.confirmUser(Long.parseLong(user), token);
    }
}
