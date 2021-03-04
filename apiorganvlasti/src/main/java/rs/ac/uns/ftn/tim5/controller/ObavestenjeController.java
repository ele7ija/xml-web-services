package rs.ac.uns.ftn.tim5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.tim5.model.obavestenje.KolekcijaObavestenja;
import rs.ac.uns.ftn.tim5.model.obavestenje.Obavestenje;
import rs.ac.uns.ftn.tim5.service.ObavestenjeService;

@Controller
@RequestMapping(value = "/obavestenje")
public class ObavestenjeController {

    @Autowired
    private ObavestenjeService obavestenjeService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<KolekcijaObavestenja> findAll() {
        KolekcijaObavestenja kolekcijaObavestenja = new KolekcijaObavestenja();
        kolekcijaObavestenja.setObavestenje(this.obavestenjeService.findAll());
        return new ResponseEntity<>(kolekcijaObavestenja, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Obavestenje> findOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.obavestenjeService.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Obavestenje> create(@RequestBody String body) {
        return new ResponseEntity<>(this.obavestenjeService.create(body), HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Obavestenje> update(@RequestBody String body) {
        return new ResponseEntity<>(this.obavestenjeService.update(body), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.obavestenjeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
