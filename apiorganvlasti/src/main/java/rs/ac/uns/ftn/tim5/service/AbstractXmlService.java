package rs.ac.uns.ftn.tim5.service;

import java.util.List;

public interface AbstractXmlService<T> {

    List<T> findAll();

    T findById(Long entityId);

    T create(String entityXml);

    T update(String entityXml);

    boolean deleteById(Long entityId);
}

