package rs.ac.uns.ftn.tim5.apipoverenik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.tim5.apipoverenik.model.wrapper.ZalbaCutanjaXmlListWrapper;
import rs.ac.uns.ftn.tim5.apipoverenik.model.zalbe_cutanja.ZalbaCutanja;
import rs.ac.uns.ftn.tim5.apipoverenik.service.ZalbaNaCutanjeService;

@Controller
@RequestMapping(value = "/zalbe-na-cutanje")
public class ZalbaNaCutanjeController {

    @Autowired
    private ZalbaNaCutanjeService zalbaNaCutanjeService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<ZalbaCutanjaXmlListWrapper> findAll(){
        return new ResponseEntity<>(new ZalbaCutanjaXmlListWrapper(this.zalbaNaCutanjeService.findAll()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<ZalbaCutanja> findOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.zalbaNaCutanjeService.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<ZalbaCutanja> create(@RequestBody String body){
        return new ResponseEntity<>(this.zalbaNaCutanjeService.create(body), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<ZalbaCutanja> update(@RequestBody String body){
        return new ResponseEntity<>(this.zalbaNaCutanjeService.update(body), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.zalbaNaCutanjeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
