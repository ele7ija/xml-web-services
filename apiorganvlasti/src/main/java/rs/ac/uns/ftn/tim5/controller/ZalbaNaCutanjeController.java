package rs.ac.uns.ftn.tim5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.tim5.model.zalba_cutanja.KolekcijaZalbiCutanja;
import rs.ac.uns.ftn.tim5.model.zalba_cutanja.ZalbaCutanja;
import rs.ac.uns.ftn.tim5.service.ZalbaNaCutanjeService;

import java.io.ByteArrayInputStream;

@Controller
@RequestMapping(value = "/zalba-na-cutanje")
public class ZalbaNaCutanjeController {

    @Autowired
    private ZalbaNaCutanjeService zalbaNaCutanjeService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<KolekcijaZalbiCutanja> findAll(){
        KolekcijaZalbiCutanja kolekcijaZalbiCutanja = new KolekcijaZalbiCutanja();
        kolekcijaZalbiCutanja.setZalbaCutanja(this.zalbaNaCutanjeService.findAll());
        return new ResponseEntity<>(kolekcijaZalbiCutanja, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<ZalbaCutanja> findOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.zalbaNaCutanjeService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/by-gradjanin/get", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<KolekcijaZalbiCutanja> getByGradjaninEmail() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KolekcijaZalbiCutanja kolekcijaZalbiCutanja = new KolekcijaZalbiCutanja();
        kolekcijaZalbiCutanja.setZalbaCutanja(zalbaNaCutanjeService.findAllByGradjaninEmail(email));
        return new ResponseEntity<>(kolekcijaZalbiCutanja, HttpStatus.OK);
    }

    @GetMapping(value = "/neobradjene/get", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<KolekcijaZalbiCutanja> getNeobradjene() {
        KolekcijaZalbiCutanja kolekcijaZalbiCutanja = new KolekcijaZalbiCutanja();
        kolekcijaZalbiCutanja.setZalbaCutanja(this.zalbaNaCutanjeService.findAllNeobradjene());
        return new ResponseEntity<>(kolekcijaZalbiCutanja, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<ZalbaCutanja> create(@RequestBody String body){
        return new ResponseEntity<>(this.zalbaNaCutanjeService.create(body), HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<ZalbaCutanja> update(@RequestBody String body){
        return new ResponseEntity<>(this.zalbaNaCutanjeService.update(body), HttpStatus.OK);
    }

    @PutMapping(value = "accept/{id}")
    ResponseEntity<Void> accept(@PathVariable("id") Long id){
        this.zalbaNaCutanjeService.accept(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "decline/{id}")
    ResponseEntity<Void> decline(@PathVariable("id") Long id){
        this.zalbaNaCutanjeService.decline(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.zalbaNaCutanjeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdf(@PathVariable Long id) {
        ByteArrayInputStream bis = new ByteArrayInputStream(this.zalbaNaCutanjeService.generatePdf(id));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zalba_na_cutanje.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/html/{id}")
    public ResponseEntity<InputStreamResource> html(@PathVariable Long id) {
        ByteArrayInputStream bis = new ByteArrayInputStream(this.zalbaNaCutanjeService.generateHtml(id));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zalba_na_cutanje.html");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/json/{id}")
    public ResponseEntity<InputStreamResource> jsonMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.zalbaNaCutanjeService.exportMetadataAsJson(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zalba_na_cutanje.json");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/xml/{id}")
    public ResponseEntity<InputStreamResource> xmlMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.zalbaNaCutanjeService.exportMetadataAsXml(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zalba_na_cutanje.xml");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/rdf/{id}")
    public ResponseEntity<InputStreamResource> rdfMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.zalbaNaCutanjeService.exportMetadataAsRdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zalba_na_cutanje.ttl");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

}
