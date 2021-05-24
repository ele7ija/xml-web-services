package rs.ac.uns.ftn.tim5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.tim5.model.gradjanin.KolekcijaGradjana;
import rs.ac.uns.ftn.tim5.model.gradjanin.Gradjanin;
import rs.ac.uns.ftn.tim5.service.GradjaninService;

@Controller
@RequestMapping(value = "/gradjanin")
public class GradjaninController {

    @Autowired
    private GradjaninService gradjaninService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<KolekcijaGradjana> findAll() {
        KolekcijaGradjana kolekcijaGradjana = new KolekcijaGradjana();
        kolekcijaGradjana.setGradjanin(this.gradjaninService.findAll());
        return new ResponseEntity<>(kolekcijaGradjana, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Gradjanin> findOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.gradjaninService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/authenticated", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Gradjanin> findAuthenticate() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(this.gradjaninService.findByUsername(email), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Gradjanin> create(@RequestBody String body) {
        return new ResponseEntity<>(this.gradjaninService.create(body), HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Gradjanin> update(@RequestBody String body) {
        return new ResponseEntity<>(this.gradjaninService.update(body), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.gradjaninService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
