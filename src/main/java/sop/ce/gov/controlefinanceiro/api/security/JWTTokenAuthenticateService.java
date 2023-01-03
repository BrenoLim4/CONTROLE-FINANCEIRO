package sop.ce.gov.controlefinanceiro.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

@Service
@Component
public class JWTTokenAuthenticateService {

    /*Validade do token*/
    private static final long EXPIRATION_TIME = 3600000;
    /*Senha única de autenticação do token*/
//    @Value("${jwt-secret}")
    private static final String SECRET = "Çl#@1¨7*0_-_][}4$";

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";



    public static void addAuthentication(HttpServletResponse response, String username){
        /*Montagem do Token*/
        //Defini qual vai ser o algoritmo da assinatura no caso vai ser o HMAC SHA512
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        SecretKeySpec key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());


        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(signatureAlgorithm, key).compact();

        String token = TOKEN_PREFIX +" "+ JWT;

        response.addHeader(HEADER_STRING, token);

    }

}
