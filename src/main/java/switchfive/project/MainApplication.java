package switchfive.project;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.text.ParseException;


@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(MainApplication.class, args);

    }

    /**
     * <p>The following Spring Configuration declares the servlet wrapper for
     * the H2 database console and maps it to the path of /console.
     * <p><If we are not using Spring Security with the H2 database console,
     * this is all we need to do.
     * <p>When we run Spring Boot application,we'll now be able to access the
     * H2 database console at</p>
     * <link>http://localhost:8080/console</link>
     * <p>spring.datasource.url=jdbc:h2:mem:testdb</p>
     * <p>spring.datasource.driverClassName=org.h2.Driver</p>
     */
    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean =
                new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
                    .authorizeRequests().antMatchers("/console/**").permitAll();
            httpSecurity.csrf().disable();
            httpSecurity.headers().frameOptions().disable();
        }

    }
}
