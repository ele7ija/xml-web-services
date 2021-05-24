package rs.ac.uns.ftn.tim5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.tim5.model.gradjanin.Gradjanin;
import rs.ac.uns.ftn.tim5.model.sluzbenik.Sluzbenik;
import rs.ac.uns.ftn.tim5.security.CustomUserDetails;
import rs.ac.uns.ftn.tim5.security.UserRole;
import java.util.Collections;
import java.util.HashSet;


@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private GradjaninService gradjaninService;

    @Autowired
    private SluzbenikService sluzbenikService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Gradjanin gradjanin = this.gradjaninService.findByUsername(username);
        if(gradjanin == null){
            Sluzbenik sluzbenik = this.sluzbenikService.findByUsername(username);
            if(sluzbenik == null) {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            } else {
                return new CustomUserDetails(
                        sluzbenik.getId(),
                        sluzbenik.getKorisnickoIme(),
                        sluzbenik.getLozinka(),
                        new HashSet<>(Collections.singletonList(new SimpleGrantedAuthority(UserRole.SLUZBENIK.name()))),
                        UserRole.SLUZBENIK,
                        true
                );
            }
        }else{
            return new CustomUserDetails(
                    gradjanin.getId(),
                    gradjanin.getKorisnickoIme(),
                    gradjanin.getLozinka(),
                    new HashSet<>(Collections.singletonList(new SimpleGrantedAuthority(UserRole.GRADJANIN.name()))),
                    UserRole.GRADJANIN,
                    true
            );
        }
    }


}
