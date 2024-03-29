package br.com.utfpr.projeto.projetofinaladministrativo.config;

import br.com.utfpr.projeto.projetofinaladministrativo.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling().accessDeniedPage("/403")
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=bad_credentials").permitAll()
                .and().logout()
                .logoutSuccessUrl("/login")
                .and().authorizeRequests()
                .antMatchers("/produto/api/**").permitAll()
                .antMatchers("/categoria/api/**").permitAll()
                .antMatchers("/fornecedor/estado/**").permitAll()
                .antMatchers("/fornecedor/cidade/**").permitAll()
                .antMatchers("/**").hasRole("ADMIN")
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/images/**")
                .antMatchers("/assets/**")
                .antMatchers("/webjars/**")
                .antMatchers("/vendors/**");
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return usuarioService;
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }
}
