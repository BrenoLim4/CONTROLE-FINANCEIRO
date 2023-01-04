package sop.ce.gov.controlefinanceiro.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import sop.ce.gov.controlefinanceiro.domain.entidade.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//estabelece o gerenciador de Token
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    /*Configurar o gerenciador de autenticação */
    protected JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
        //Obriga a autenticar a URL
        super(new AntPathRequestMatcher(url));
        //Gerenciador de autenticacao
        setAuthenticationManager(authenticationManager);
    }

    //Retorna o usuário ao processar a autenticacao
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //retorna usuário: login, senha e acessos
        User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        new JWTTokenAuthenticateService().addAuthentication(response, authResult.getName());
    }
}
