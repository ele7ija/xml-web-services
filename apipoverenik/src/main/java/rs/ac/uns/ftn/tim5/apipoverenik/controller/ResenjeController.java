package rs.ac.uns.ftn.tim5.apipoverenik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.tim5.apipoverenik.model.dummy.Dummy;
import rs.ac.uns.ftn.tim5.apipoverenik.model.resenje.Resenje;
import rs.ac.uns.ftn.tim5.apipoverenik.service.ResenjeService;

import java.util.List;

@Controller
@RequestMapping(value = "/resenja")
public class ResenjeController {

    @Autowired
    private ResenjeService resenjeService;

    @GetMapping(value = "/dummy", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Dummy> getDummy(){
        Dummy dummy = new Dummy();
        dummy.setField(123);
        return new ResponseEntity<>(dummy, HttpStatus.OK);
    }

    @PostMapping(value = "/dummy", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Dummy> createDummy(@RequestBody Dummy dummy){
        dummy.setField(123);
        return new ResponseEntity<>(dummy, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<List<Resenje>> findAll(){
        return new ResponseEntity<>(this.resenjeService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Resenje> findOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.resenjeService.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Resenje> create(@RequestBody String body){
        return new ResponseEntity<>(this.resenjeService.create(body), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Resenje> update(@RequestBody String body){
        return new ResponseEntity<>(this.resenjeService.update(body), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.resenjeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
