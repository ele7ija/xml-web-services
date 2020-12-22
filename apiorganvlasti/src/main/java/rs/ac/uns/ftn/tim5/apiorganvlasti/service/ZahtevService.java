package rs.ac.uns.ftn.tim5.apiorganvlasti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.tim5.apiorganvlasti.model.zahtev.Zahtev;
import rs.ac.uns.ftn.tim5.apiorganvlasti.repository.ZahtevRepository;

import java.util.List;

@Service
public class ZahtevService {

    @Autowired
    ZahtevRepository zahtevRepository;

    public List<Zahtev> getAllZahtevi() {
        return zahtevRepository.getAllZahtevi();
    }

    public Zahtev getZahtev(long idZahteva) {
        return zahtevRepository.getZahtev(idZahteva);
    }

    public Long createZahtev(Zahtev zahtev) {
        return zahtevRepository.createZahtev(zahtev);
    }

    public boolean updateZahtev(Zahtev zahtev) {
        return zahtevRepository.updateZahtev(zahtev);
    }

    public boolean deleteZahtev(long idZahteva) {
        return zahtevRepository.deleteZahtev(idZahteva);
    }
}
