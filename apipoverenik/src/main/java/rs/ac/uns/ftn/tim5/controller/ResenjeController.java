package rs.ac.uns.ftn.tim5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.tim5.model.resenje.KolekcijaResenja;
import rs.ac.uns.ftn.tim5.model.resenje.Resenje;
import rs.ac.uns.ftn.tim5.service.ResenjeService;

import java.io.ByteArrayInputStream;

@Controller
@RequestMapping(value = "/resenje")
public class ResenjeController {

    @Autowired
    private ResenjeService resenjeService;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<KolekcijaResenja> findAll() {
        KolekcijaResenja kolekcijaResenja = new KolekcijaResenja();
        kolekcijaResenja.setResenje(this.resenjeService.findAll());
        return new ResponseEntity<>(kolekcijaResenja, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Resenje> findOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.resenjeService.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Resenje> create(@RequestBody String body) {
        return new ResponseEntity<>(this.resenjeService.create(body), HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<Resenje> update(@RequestBody String body) {
        return new ResponseEntity<>(this.resenjeService.update(body), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.resenjeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdf(@PathVariable Long id) {
        ByteArrayInputStream bis = new ByteArrayInputStream(this.resenjeService.generatePdf(id));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=resenje.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/html/{id}")
    public ResponseEntity<InputStreamResource> html(@PathVariable Long id) {
        ByteArrayInputStream bis = new ByteArrayInputStream(this.resenjeService.generateHtml(id));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=resenje.html");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/json/{id}")
    public ResponseEntity<InputStreamResource> jsonMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.resenjeService.exportMetadataAsJson(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=resenje_metadata.json");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/xml/{id}")
    public ResponseEntity<InputStreamResource> xmlMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.resenjeService.exportMetadataAsXml(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=resenje_metadata.xml");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/rdf/{id}")
    public ResponseEntity<InputStreamResource> rdfMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.resenjeService.exportMetadataAsRdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=resenje_metadata.ttl");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

}
