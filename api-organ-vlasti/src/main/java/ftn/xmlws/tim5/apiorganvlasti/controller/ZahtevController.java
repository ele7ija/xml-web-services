package ftn.xmlws.tim5.apiorganvlasti.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/zahtev")
public class ZahtevController {

    @PostMapping(consumes = MediaType.APPLICATION_ATOM_XML_VALUE)
    public void create(){

    }
}
