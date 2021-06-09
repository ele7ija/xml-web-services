package rs.ac.uns.ftn.tim5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.tim5.model.izvestaj.Izvestaj;
import rs.ac.uns.ftn.tim5.model.izvestaj.KolekcijaIzvestaja;
import rs.ac.uns.ftn.tim5.service.IzvestajService;

@Controller
@RequestMapping(value = "/izvestaj")
public class IzvestajController {

    @Autowired
    private IzvestajService izvestajService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<KolekcijaIzvestaja> findAll() {
        KolekcijaIzvestaja kolekcijaIzvestaja = new KolekcijaIzvestaja();
        kolekcijaIzvestaja.setIzvestaj(this.izvestajService.findAll());
        return new ResponseEntity<>(kolekcijaIzvestaja, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Izvestaj> findOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.izvestajService.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Izvestaj> create(@RequestBody String body) {
        return new ResponseEntity<>(this.izvestajService.create(body), HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Izvestaj> update(@RequestBody String body) {
        return new ResponseEntity<>(this.izvestajService.update(body), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.izvestajService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}