package rs.ac.uns.ftn.tim5.apipoverenik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.tim5.apipoverenik.model.zalba_na_odluku.KolekcijaZalbiNaOdluku;
import rs.ac.uns.ftn.tim5.apipoverenik.model.zalba_na_odluku.ZalbaNaOdluku;
import rs.ac.uns.ftn.tim5.apipoverenik.service.ZalbaNaOdlukuService;

import java.util.List;

@Controller
@RequestMapping(value = "/zalbe-na-odluku")
public class ZalbaNaOdlukuController {

    @Autowired
    private ZalbaNaOdlukuService zalbaNaOdlukuService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<KolekcijaZalbiNaOdluku> findAll(){
        KolekcijaZalbiNaOdluku kolekcijaZalbiNaOdluku = new KolekcijaZalbiNaOdluku();
        kolekcijaZalbiNaOdluku.setZalbaNaOdluku(this.zalbaNaOdlukuService.findAll());
        return new ResponseEntity<>(kolekcijaZalbiNaOdluku, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<ZalbaNaOdluku> findOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.zalbaNaOdlukuService.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<ZalbaNaOdluku> create(@RequestBody String body){
        return new ResponseEntity<>(this.zalbaNaOdlukuService.create(body), HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<ZalbaNaOdluku> update(@RequestBody String body){
        return new ResponseEntity<>(this.zalbaNaOdlukuService.update(body), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.zalbaNaOdlukuService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
