package rs.ac.uns.ftn.tim5.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDHelper {
    public Long getUUID() {
        return UUID.randomUUID().getLeastSignificantBits() * -1;
    }
}
