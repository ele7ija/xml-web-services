package rs.ac.uns.ftn.tim5.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rs.ac.uns.ftn.tim5.dto.EmailDTO;
import rs.ac.uns.ftn.tim5.model.resenje.Resenje;

@Service
public class EmailService {

    @Value("${EMAIL_SERVICE_ENDPOINT}")
    private String emailServiceEndpoint;

    public void sendResenje(Resenje resenje, String recipient, byte[] resenjePdf, byte[] resenjeHtml) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setRecipient(recipient);
        String message = "Обавештавамо Вас да је ваша жалба против органа власти прихваћена. У прилогу шаљемо решење повереника.";
        if (!resenje.getOdluka().isPrihvaceno()) {
            message = "Обавештавамо Вас да је ваша жалба против органа власти одбијена. У прилогу шаљемо решење повереника.";
        }
        emailDTO.setMessage(message);
        emailDTO.setSubject("Решење на уложену жалбу.");
        emailDTO.setAttachmentPdf(resenjePdf);
        emailDTO.setAttachmentHtml(resenjeHtml);
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
}
