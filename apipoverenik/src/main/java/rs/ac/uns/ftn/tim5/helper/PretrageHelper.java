package rs.ac.uns.ftn.tim5.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.tim5.service.RDFService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PretrageHelper {

    @Autowired
    private RDFService rdfService;

    public Set<String> searchMetadata(String[] metadata, String collection) {
        List<String> ids = new ArrayList<>();

        boolean flag = false;

        for (String word : metadata) {
            System.out.println("Searching word: " + word);
            if (word.isEmpty()) {
                continue;
            }
            word = word.trim();
            if (!word.startsWith("\"")) {
                word = "\"" + word;
            }
            if (!word.endsWith("\"")) {
                word = word + "\"";
            }
             if (!flag) {
                 for (String url : rdfService.search(collection, word.toLowerCase())) {
                     String[] tokens = url.split("/");
                     ids.add(tokens[4]);
                 }
                 flag = true;
             }
             else {
                 List<String> newlist = new ArrayList<>();
                 for (String url : rdfService.search(collection, word.toLowerCase())) {
                     String[] tokens = url.split("/");
                     if (ids.contains(tokens[4])) {
                         newlist.add(tokens[4]);
                     }
                 }
                 ids = newlist;
             }

        }

        System.out.println("[searchMEtadata] Got ids: " + ids);
        return new HashSet<>(ids); // AND op
    }
}
