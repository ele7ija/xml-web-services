package rs.ac.uns.ftn.tim5.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.ac.uns.ftn.tim5.security.jwt.JwtConfig;
import rs.ac.uns.ftn.tim5.security.jwt.JwtIssuerFilter;
import rs.ac.uns.ftn.tim5.security.jwt.JwtVerifierFilter;
import rs.ac.uns.ftn.tim5.service.ApplicationUserDetailsService;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserDetailsService applicationUserService;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Autowired
    public AppSecurityConfig(
            PasswordEncoder passwordEncoder,
            ApplicationUserDetailsService applicationUserService,
            SecretKey secretKey,
            JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()

                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers(HttpMethod.POST, "/gradjanin").permitAll()
                .antMatchers(HttpMethod.POST,"/sluzbenik").permitAll()
                .antMatchers("/ws/**").permitAll()
                
                .anyRequest().authenticated()

                .and()

                .cors()
                .and()

                .addFilter(new JwtIssuerFilter(authenticationManager(), jwtConfig, secretKey, "/auth/login", "email"))
                .addFilterBefore(new JwtVerifierFilter(jwtConfig, secretKey), JwtIssuerFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        /*
            Custom filters will ignore register endpoint
         */
        web.ignoring()
                .antMatchers(HttpMethod.POST,"/gradjanin", "/sluzbenik")
                .antMatchers("/ws/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
}
