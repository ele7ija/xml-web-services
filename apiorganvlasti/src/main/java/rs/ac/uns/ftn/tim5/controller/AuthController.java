package rs.ac.uns.ftn.tim5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.tim5.dto.LoginDTO;
import rs.ac.uns.ftn.tim5.security.jwt.JwtConfig;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/auth")
public class AuthController
{
    @Autowired
    private JwtConfig jwtConfig;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginDTO> login(HttpServletResponse response) {
        String jwt = response.getHeader(jwtConfig.getAuthorizationHeader()).replace(jwtConfig.getTokenPrefix(), "");
        return new ResponseEntity<>(
                new LoginDTO(jwt),
                HttpStatus.OK
        );
    }

}
