package rs.ac.uns.ftn.tim5.apipoverenik.service;

import java.util.List;

public interface AbstractXmlService<T> {

    List<T> findAll();
    T findById(Long entityId);
    Long create(String entityXml);
    boolean update(String entityXml);
    boolean deleteById(Long entityId);
}

