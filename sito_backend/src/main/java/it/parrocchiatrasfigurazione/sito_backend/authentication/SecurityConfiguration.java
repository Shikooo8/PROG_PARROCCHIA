package it.parrocchiatrasfigurazione.sito_backend.authentication;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import it.parrocchiatrasfigurazione.sito_backend.model.Credenziali;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private final DataSource dataSource;

  public SecurityConfiguration(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Bean
  public UserDetailsService userDetailsService() {
    JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
    manager.setUsersByUsernameQuery("SELECT username, password, 1 as enabled FROM credenziali WHERE username=?");
    manager.setAuthoritiesByUsernameQuery("SELECT username, ruolo FROM credenziali WHERE username=?");
    return manager;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

 @Bean
protected SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception {



    httpSecurity.authorizeHttpRequests(authorize -> {
        authorize.requestMatchers(HttpMethod.GET, "/", "/fragments", "/notizie/*", "/notizie", "/eventi", "/iniziative", "/eventi/*", "/iniziative/*", "/richiesta-registrazione", "/login", "/css/", "/css/**", "/images/**", "/react/", "/favicon.ico", "/api/utente/me").permitAll();
        authorize.requestMatchers(HttpMethod.POST, "/login", "/richiesta-registrazione").permitAll();

        //authorize.requestMatchers(HttpMethod.POST, "/api/film/*/recensione").hasAnyAuthority("USER", "ADMIN");
        //authorize.requestMatchers(HttpMethod.PUT, "/api/recensione/").hasAnyAuthority("USER", "ADMIN");
        //authorize.requestMatchers(HttpMethod.DELETE, "/api/recensione/").hasAnyAuthority("USER", "ADMIN");

        authorize.requestMatchers(HttpMethod.GET, "/admin/").hasAnyAuthority(Credenziali.ADMIN_ROLE);
        authorize.requestMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(Credenziali.ADMIN_ROLE);
        authorize.anyRequest().authenticated();
    });

    httpSecurity.formLogin(form -> {
        form.loginPage("/login").permitAll();
        form.defaultSuccessUrl("/", true);
        form.failureUrl("/login?error=true");
    });

    httpSecurity.logout(logout -> {
        logout.logoutUrl("/logout");
        logout.logoutSuccessUrl("/");
        logout.invalidateHttpSession(true);
        logout.deleteCookies("JSESSIONID");
        logout.clearAuthentication(true);
        logout.permitAll();
    });
    return httpSecurity.build();
  }
}