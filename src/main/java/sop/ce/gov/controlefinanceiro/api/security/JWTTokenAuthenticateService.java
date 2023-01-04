package sop.ce.gov.controlefinanceiro.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sop.ce.gov.controlefinanceiro.api.config.ApplicationContextLoad;
import sop.ce.gov.controlefinanceiro.api.security.exceptions.UnauthenticatedUser;
import sop.ce.gov.controlefinanceiro.domain.entidade.User;
import sop.ce.gov.controlefinanceiro.domain.repositories.UserRepository;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
@Component
public class JWTTokenAuthenticateService {

    /*Validade do token*/
    private static final long EXPIRATION_TIME = 3600000;
    /*Senha única de autenticação do token*/
//    @Value("${jwt-secret}")
    private static final String SECRET = "1369*$#El";

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public void addAuthentication(HttpServletResponse response, String username) throws IOException {
        /*Montagem do Token*/

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        SecretKeySpec key = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());


        String JWT = Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SIGNATURE_ALGORITHM, key).compact();

        String token = TOKEN_PREFIX +" "+ JWT;

        response.addHeader(HEADER_STRING, token);
        response.getWriter().write("{\"Authorization\": \""+token+"\"}");

    }

    public Authentication getAuthetication(HttpServletRequest request){
        /*Pega token enviado no cabeçalho*/

        String token  = request.getHeader("Authorization");
        if(token != null){
            String username = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            if (username != null){
                Optional<User> user = ApplicationContextLoad
                        .getApplicationContext()
                        .getBean(UserRepository.class)
                        .findUserByLogin(username);
                if(user.isPresent()){
                    User us = user.get();
                    return new UsernamePasswordAuthenticationToken(
                            us.getUsername(), us.getPassword(), us.getAuthorities());
                }
            }
        }

        throw new UnauthenticatedUser();
    }

}
