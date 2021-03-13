package rs.ac.uns.ftn.tim5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.tim5.model.osnovnapretraga.OsnovnaPretraga;
import rs.ac.uns.ftn.tim5.model.osnovnapretraga.RezultatOsnovnePretrage;
import rs.ac.uns.ftn.tim5.service.OsnovnaPretragaService;

@Controller
@RequestMapping(value = "/osnovnaPretraga")
public class OsnovnaPretragaController {

    @Autowired
    private OsnovnaPretragaService osnovnaPretragaService;

    @PostMapping(
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<RezultatOsnovnePretrage> post(@RequestBody OsnovnaPretraga pretraga) {
        return new ResponseEntity<>(this.osnovnaPretragaService.pronadji(pretraga), HttpStatus.OK);
    }
}
