package jmaster.io.notificationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jmaster.io.notificationservice.model.MessageDTO;
import jmaster.io.notificationservice.service.EmailService;

@RestController
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @PreAuthorize("#oauth2.hasScope('notification')")
    @PostMapping("/send-notification")
    public void sendNotification(@RequestBody MessageDTO messageDTO) {
	    emailService.sendEmail(messageDTO);
    }
}
