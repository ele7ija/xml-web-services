package rs.ac.uns.ftn.tim5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.tim5.dto.PretragaDTO;
import rs.ac.uns.ftn.tim5.dto.ReferencedByDTO;
import rs.ac.uns.ftn.tim5.dto.ReferencedBySearchDTO;
import rs.ac.uns.ftn.tim5.dto.RezultatPretrageDTO;
import rs.ac.uns.ftn.tim5.model.osnovnapretraga.RezultatOsnovnePretrage;
import rs.ac.uns.ftn.tim5.service.PretragaService;

@Controller
@RequestMapping(value = "/pretraga")
public class PretragaController {

    @Autowired
    private PretragaService pretragaService;

    @PostMapping(
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<RezultatPretrageDTO> post(@RequestBody PretragaDTO pretraga) {
        return new ResponseEntity<>(this.pretragaService.pronadji(pretraga), HttpStatus.OK);
    }

    @PostMapping(
            value = "/referenced-by",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE
    )
    ResponseEntity<ReferencedByDTO> getReferencedBy(@RequestBody ReferencedBySearchDTO about) {
        return new ResponseEntity<>(this.pretragaService.referencedBy(about.getAbout()), HttpStatus.OK);
    }
}
