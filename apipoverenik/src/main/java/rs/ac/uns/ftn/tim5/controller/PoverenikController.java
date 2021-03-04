package rs.ac.uns.ftn.tim5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.tim5.model.poverenik.KolekcijaPoverenika;
import rs.ac.uns.ftn.tim5.model.poverenik.Poverenik;
import rs.ac.uns.ftn.tim5.service.PoverenikService;

@Controller
@RequestMapping(value = "/poverenik")
public class PoverenikController {

    @Autowired
    private PoverenikService poverenikService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<KolekcijaPoverenika> findAll() {
        KolekcijaPoverenika kolekcijaResenja = new KolekcijaPoverenika();
        kolekcijaResenja.setPoverenik(this.poverenikService.findAll());
        return new ResponseEntity<>(kolekcijaResenja, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Poverenik> findOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.poverenikService.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Poverenik> create(@RequestBody String body) {
        return new ResponseEntity<>(this.poverenikService.create(body), HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Poverenik> update(@RequestBody String body) {
        return new ResponseEntity<>(this.poverenikService.update(body), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.poverenikService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
