package rs.ac.uns.ftn.tim5.apiorganvlasti.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.tim5.apiorganvlasti.model.util.TFizickoLice;
import rs.ac.uns.ftn.tim5.apiorganvlasti.model.zahtev.Zahtev;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Repository
public class ZahtevRepository {

    static List<Zahtev> zahtevi = new ArrayList<>();

    @PostConstruct
    public void initData() {
        Zahtev z1 = new Zahtev();
        z1.setId(1);
        z1.setMesto("Novi Sad");
        z1.setOpisZahteva("Opis 1");

        Zahtev z2 = new Zahtev();
        z2.setId(2);
        z2.setMesto("Beograd");
        z2.setOpisZahteva("Opis 2");

        zahtevi.add(z1);
        zahtevi.add(z2);
    }

    public List<Zahtev> getAllZahtevi() {
        return zahtevi;
    }

    public Zahtev getZahtev(long idZahteva) {
        for(Zahtev zahtev : zahtevi){
            if(zahtev.getId() == idZahteva)
                return zahtev;
        }
        return null;
    }

    public Long createZahtev(Zahtev zahtev) {
        zahtev.setId(zahtevi.size() + 1);
        zahtevi.add(zahtev);
        return zahtev.getId();
    }

    public boolean updateZahtev(Zahtev zahtev) {
        AtomicBoolean found = new AtomicBoolean(false);
        zahtevi = zahtevi
                .stream()
                .map(z -> {
                    if(z.getId() == zahtev.getId()){
                        found.set(true);
                        return zahtev;
                    }else
                        return z;
                })
                .collect(Collectors.toList());
        return found.get();
    }

    public boolean deleteZahtev(long idZahteva) {
        AtomicBoolean found = new AtomicBoolean(false);
        zahtevi = zahtevi
                .stream()
                .filter(z -> {
                    if(z.getId() == idZahteva){
                        found.set(true);
                        return false;
                    }else
                        return true;
                })
                .collect(Collectors.toList());
        return found.get();
    }
}
