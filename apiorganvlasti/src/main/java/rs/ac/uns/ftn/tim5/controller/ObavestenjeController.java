package rs.ac.uns.ftn.tim5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.tim5.model.obavestenje.KolekcijaObavestenja;
import rs.ac.uns.ftn.tim5.model.obavestenje.Obavestenje;
import rs.ac.uns.ftn.tim5.service.ObavestenjeService;
import java.io.ByteArrayInputStream;

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

    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdf(@PathVariable Long id) {
        ByteArrayInputStream bis = new ByteArrayInputStream(this.obavestenjeService.generatePdf(id));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=obavestenje.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/html/{id}")
    public ResponseEntity<InputStreamResource> html(@PathVariable Long id) {
        ByteArrayInputStream bis = new ByteArrayInputStream(this.obavestenjeService.generateHtml(id));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=obavestenje.html");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/json/{id}")
    public ResponseEntity<InputStreamResource> jsonMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.obavestenjeService.exportMetadataAsJson(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zahtev.json");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/xml/{id}")
    public ResponseEntity<InputStreamResource> xmlMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.obavestenjeService.exportMetadataAsXml(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zahtev.xml");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/rdf/{id}")
    public ResponseEntity<InputStreamResource> rdfMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.obavestenjeService.exportMetadataAsRdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zahtev.ttl");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/by-zahtev/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Obavestenje> findByZahtevId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.obavestenjeService.findByZahtevId(id), HttpStatus.OK);
    }

}
