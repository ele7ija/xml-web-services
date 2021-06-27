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
import rs.ac.uns.ftn.tim5.model.zalba_na_odluku.KolekcijaZalbiNaOdluku;
import rs.ac.uns.ftn.tim5.model.zalba_na_odluku.ZalbaNaOdluku;
import rs.ac.uns.ftn.tim5.service.ZalbaNaOdlukuService;

import java.io.ByteArrayInputStream;

@Controller
@RequestMapping(value = "/zalba-na-odluku")
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

    @GetMapping(value = "/by-gradjanin/get", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<KolekcijaZalbiNaOdluku> getByGradjaninEmail() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KolekcijaZalbiNaOdluku kolekcijaZalbiNaOdluku = new KolekcijaZalbiNaOdluku();
        kolekcijaZalbiNaOdluku.setZalbaNaOdluku(this.zalbaNaOdlukuService.findAllByGradjaninEmail(email));
        return new ResponseEntity<>(kolekcijaZalbiNaOdluku, HttpStatus.OK);
    }

    @GetMapping(value = "/neobradjene/get", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<KolekcijaZalbiNaOdluku> getNeobradjene() {
        KolekcijaZalbiNaOdluku kolekcijaZalbiNaOdluku = new KolekcijaZalbiNaOdluku();
        kolekcijaZalbiNaOdluku.setZalbaNaOdluku(this.zalbaNaOdlukuService.findAllNeobradjene());
        return new ResponseEntity<>(kolekcijaZalbiNaOdluku, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<ZalbaNaOdluku> create(@RequestBody String body){
        return new ResponseEntity<>(this.zalbaNaOdlukuService.create(body), HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<ZalbaNaOdluku> update(@RequestBody String body){
        return new ResponseEntity<>(this.zalbaNaOdlukuService.update(body), HttpStatus.OK);
    }

    @PutMapping(value = "accept/{id}")
    ResponseEntity<Void> accept(@PathVariable("id") Long id){
        this.zalbaNaOdlukuService.accept(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "decline/{id}")
    ResponseEntity<Void> decline(@PathVariable("id") Long id){
        this.zalbaNaOdlukuService.decline(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.zalbaNaOdlukuService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdf(@PathVariable Long id) {
        ByteArrayInputStream bis = new ByteArrayInputStream(this.zalbaNaOdlukuService.generatePdf(id));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zalba_na_odluku.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/html/{id}")
    public ResponseEntity<InputStreamResource> html(@PathVariable Long id) {
        ByteArrayInputStream bis = new ByteArrayInputStream(this.zalbaNaOdlukuService.generateHtml(id));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zalba_na_odluku.html");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/json/{id}")
    public ResponseEntity<InputStreamResource> jsonMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.zalbaNaOdlukuService.exportMetadataAsJson(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zalba_na_odluku.json");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/xml/{id}")
    public ResponseEntity<InputStreamResource> xmlMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.zalbaNaOdlukuService.exportMetadataAsXml(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zalba_na_odluku.xml");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/rdf/{id}")
    public ResponseEntity<InputStreamResource> rdfMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.zalbaNaOdlukuService.exportMetadataAsRdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zalba_na_odluku.ttl");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }
}
