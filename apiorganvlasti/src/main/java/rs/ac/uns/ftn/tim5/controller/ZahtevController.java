package rs.ac.uns.ftn.tim5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.tim5.model.zahtev.KolekcijaZahteva;
import rs.ac.uns.ftn.tim5.model.zahtev.Zahtev;
import rs.ac.uns.ftn.tim5.service.ZahtevService;

@Controller
@RequestMapping(value = "/zahtev")
public class ZahtevController {

    @Autowired
    private ZahtevService zahtevService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<KolekcijaZahteva> findAll() {
        KolekcijaZahteva kolekcijaZahteva = new KolekcijaZahteva();
        kolekcijaZahteva.setZahtev(this.zahtevService.findAll());
        return new ResponseEntity<>(kolekcijaZahteva, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Zahtev> findOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.zahtevService.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Zahtev> create(@RequestBody String body) {
        return new ResponseEntity<>(this.zahtevService.create(body), HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Zahtev> update(@RequestBody String body) {
        return new ResponseEntity<>(this.zahtevService.update(body), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.zahtevService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
