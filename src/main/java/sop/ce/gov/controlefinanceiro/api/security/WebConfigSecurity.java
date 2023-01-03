package sop.ce.gov.controlefinanceiro.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import sop.ce.gov.controlefinanceiro.domain.services.ImplUserDetailsServices;

/*Mapeia as url, endereços, autoriza ou bloqueia acessos a URL*/
@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{

    @Autowired
    private ImplUserDetailsServices userDetailsServices;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //ativando proteção contra usuário que não está autenticado pelo token
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                /*Ativando a permissão para acessp a página inicial do Sistema*/
                .disable().authorizeRequests().antMatchers("/login").permitAll()
                /*URL de Logout - redireciona após o user deslogar do sistema*/
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/login")
                /*Mapeia URL de logout e invalida o usuário*/
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        /*TODO: Filtrar requisiççies de login para autenticação*/
//        http.addFilterBefore()
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServices).passwordEncoder(new BCryptPasswordEncoder());
    }

//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        return http.cors().and().authorizeHttpRequests((auth) -> auth.anyRequest().authenticated())
//                .authorizeHttpRequests()
//                .httpBasic(Customizer.withDefaults())
//                .build();
//    }

}
