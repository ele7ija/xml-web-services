package rs.ac.uns.ftn.tim5.apiorganvlasti.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import rs.ac.uns.ftn.tim5.apiorganvlasti.model.zahtev.*;
import rs.ac.uns.ftn.tim5.apiorganvlasti.service.ZahtevService;

@Endpoint
public class ZahtevEndpoint {
    private static final String NAMESPACE_URI = "http://ftn.uns.ac.rs/tim5/apiorganvlasti/model/zahtev";

    @Autowired
    ZahtevService zahtevService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllZahtevRequest")
    @ResponsePayload
    public GetAllZahtevResponse getAllZahtevi() {
        GetAllZahtevResponse response = new GetAllZahtevResponse();
        response.setZahtev(zahtevService.getAllZahtevi());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getZahtevRequest")
    @ResponsePayload
    public GetZahtevResponse getZahtev(@RequestPayload GetZahtevRequest request) {
        GetZahtevResponse response = new GetZahtevResponse();
        response.setZahtev(zahtevService.getZahtev(request.getIdZahteva()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createZahtevRequest")
    @ResponsePayload
    public CreateZahtevResponse createZahtev(@RequestPayload CreateZahtevRequest request) {
        CreateZahtevResponse response = new CreateZahtevResponse();
        response.setIdZahteva(zahtevService.createZahtev(request.getZahtev()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateZahtevRequest")
    @ResponsePayload
    public UpdateZahtevResponse updateZahtev(@RequestPayload UpdateZahtevRequest request) {
        UpdateZahtevResponse response = new UpdateZahtevResponse();
        request.getZahtev().setId(request.getIdZahteva());
        response.setUspeh(zahtevService.updateZahtev(request.getZahtev()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteZahtevRequest")
    @ResponsePayload
    public DeleteZahtevResponse deleteZahtev(@RequestPayload DeleteZahtevRequest request) {
        DeleteZahtevResponse response = new DeleteZahtevResponse();
        response.setUspeh(zahtevService.deleteZahtev(request.getIdZahteva()));
        return response;
    }
}
