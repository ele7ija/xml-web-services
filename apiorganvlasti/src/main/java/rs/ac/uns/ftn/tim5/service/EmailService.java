package rs.ac.uns.ftn.tim5.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rs.ac.uns.ftn.tim5.dto.EmailDTO;
import rs.ac.uns.ftn.tim5.model.util.Datum;
import rs.ac.uns.ftn.tim5.model.zahtev.Zahtev;

@Service
public class EmailService {

    @Value("${EMAIL_SERVICE_ENDPOINT}")
    private String emailServiceEndpoint;

    public String getDate(Datum datum) {
        String dan = Integer.toString(datum.getDan().getDay());
        String mesec = Integer.toString(datum.getMesec().getMonth());
        String godina = Integer.toString(datum.getGodina().getYear());
        return String.format("%s.%s.%s", dan, mesec, godina);
    }

    public void odbijZahtev(Zahtev zahtev) {
        String datum = this.getDate(zahtev.getDatum());
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient(zahtev.getTrazilac().getContent());
        emailDTO.setSubject(String.format("Обавештење - %s", zahtev.getOrgan().getNaziv()));
        emailDTO.setMessage(String.format("Обавештавамо вас да је Ваш захтев за приступ информацији од јавног значаја, који сте поднели датума %s, одбијен одлуком органа власти.", datum));
        this.sendMail(emailDTO, "no-attach");
    }

    public void prihvatiZahtev(Zahtev zahtev, byte[] obavestenjePdf, byte[] obavestenjeHtml) {
        String datum = this.getDate(zahtev.getDatum());
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient(zahtev.getTrazilac().getContent());
        emailDTO.setSubject(String.format("Обавештење - %s", zahtev.getOrgan().getNaziv()));
        emailDTO.setMessage(String.format("Обавештавамо вас да је Ваш захтев за приступ информацији од јавног значаја, који сте поднели датума %s, прихваћен одлуком органа власти. У прилогу Вам шаљемо обавештење у којем можете пронаћи додатне информације.", datum));
        emailDTO.setAttachmentPdf(obavestenjePdf);
        emailDTO.setAttachmentHtml(obavestenjeHtml);
        this.sendMail(emailDTO, "attach");
    }

    @Async
    public void sendMail(EmailDTO emailDTO, String pathExtension) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<EmailDTO> request = new HttpEntity<EmailDTO>(emailDTO, httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(
                String.format("%s/email/%s", this.emailServiceEndpoint, pathExtension),
                request,
                Boolean.class
        );
    }

    public void istekaoZahtev(Zahtev zahtev) {
        String datum = this.getDate(zahtev.getDatum());
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient(zahtev.getTrazilac().getContent());
        emailDTO.setSubject(String.format("Обавештење - %s", zahtev.getOrgan().getNaziv()));
        emailDTO.setMessage(String.format("Обавештавамо вас да је Ваш захтев за приступ информацији од јавног значаја, који сте поднели датума %s, истекао.", datum));
        this.sendMail(emailDTO, "no-attach");
    }
}
