package rs.ac.uns.ftn.tim5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.tim5.dto.EmailDTO;
import rs.ac.uns.ftn.tim5.service.EmailService;

@Controller
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/no-attach")
    public ResponseEntity<Boolean> noAttachment(@RequestBody EmailDTO emailDTO) {
        this.emailService.noAttach(emailDTO);
        return new ResponseEntity<>(
                true,
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/attach")
    public ResponseEntity<Boolean> nwithAttachment(@RequestBody EmailDTO emailDTO) {
        this.emailService.withAttach(emailDTO);
        return new ResponseEntity<>(
                true,
                HttpStatus.OK
        );
    }
}
