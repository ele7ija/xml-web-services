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
import rs.ac.uns.ftn.tim5.model.zahtev.KolekcijaZahteva;
import rs.ac.uns.ftn.tim5.model.zahtev.Zahtev;
import rs.ac.uns.ftn.tim5.service.ZahtevService;

import java.io.ByteArrayInputStream;

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


    @GetMapping(value = "/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdf(@PathVariable Long id) {
        ByteArrayInputStream bis = this.zahtevService.generatePdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zahtev.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/html/{id}")
    public ResponseEntity<InputStreamResource> html(@PathVariable Long id) {
        ByteArrayInputStream bis = this.zahtevService.generateHtml(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zahtev.html");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/json/{id}")
    public ResponseEntity<InputStreamResource> jsonMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.zahtevService.exportMetadataAsJson(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zahtev.json");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/xml/{id}")
    public ResponseEntity<InputStreamResource> xmlMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.zahtevService.exportMetadataAsXml(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zahtev.xml");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/metadata/rdf/{id}")
    public ResponseEntity<InputStreamResource> rdfMetadata(@PathVariable Long id) {
        ByteArrayInputStream bis = this.zahtevService.exportMetadataAsRdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zahtev.ttl");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(bis));
    }

    /**
        Vraca sve zahteve na osnovu email-a ulogovanog korisnika
        ID-evi zahteva se dobavljajue iz RDF baze, nakon cega se dobavljaju celi dokumenti
        iz XML baze na osnovu prethodno dobijenih ID-eva
     */
    @GetMapping(value = "/by-gradjanin", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<KolekcijaZahteva> getByGradjaninEmail() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KolekcijaZahteva kolekcijaZahteva = new KolekcijaZahteva();
        kolekcijaZahteva.setZahtev(this.zahtevService.findAllByGradjaninEmail(email));
        return new ResponseEntity<>(kolekcijaZahteva, HttpStatus.OK);
    }

    /**
     Vraca sve zahteve na osnovu naziva organa vlasti ulogovanog sluzbenika
     ID-evi zahteva se dobavljajue iz RDF baze, nakon cega se dobavljaju celi dokumenti
     iz XML baze na osnovu prethodno dobijenih ID-eva
     */
    @GetMapping(value = "/by-organ-vlasti", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<KolekcijaZahteva> getByNazivOrganaVlasti() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KolekcijaZahteva kolekcijaZahteva = new KolekcijaZahteva();
        kolekcijaZahteva.setZahtev(this.zahtevService.findAllByNazivOrganaVlasti(email));
        return new ResponseEntity<>(kolekcijaZahteva, HttpStatus.OK);
    }

}
