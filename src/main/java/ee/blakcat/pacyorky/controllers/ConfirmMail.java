package ee.blakcat.pacyorky.controllers;

import ee.blakcat.pacyorky.services.pacyorky.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfirmMail {
    private final UserService userService;

    @Autowired
    public ConfirmMail(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam String user, @RequestParam String token, Model model) {
        model.addAttribute("confirmed", userService.confirmUser(Long.parseLong(user), token));
        return "confirmmail";
    }

}
