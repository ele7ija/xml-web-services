package rs.ac.uns.ftn.tim5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.tim5.model.sluzbenik.KolekcijaSluzbenika;
import rs.ac.uns.ftn.tim5.model.sluzbenik.Sluzbenik;
import rs.ac.uns.ftn.tim5.service.SluzbenikService;

@Controller
@RequestMapping(value = "/sluzbenik")
public class SluzbenikController {

    @Autowired
    private SluzbenikService sluzbenikService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<KolekcijaSluzbenika> findAll() {
        KolekcijaSluzbenika kolekcijaSluzbenika = new KolekcijaSluzbenika();
        kolekcijaSluzbenika.setSluzbenik(this.sluzbenikService.findAll());
        return new ResponseEntity<>(kolekcijaSluzbenika, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Sluzbenik> findOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.sluzbenikService.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Sluzbenik> create(@RequestBody String body) {
        return new ResponseEntity<>(this.sluzbenikService.create(body), HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Sluzbenik> update(@RequestBody String body) {
        return new ResponseEntity<>(this.sluzbenikService.update(body), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.sluzbenikService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
