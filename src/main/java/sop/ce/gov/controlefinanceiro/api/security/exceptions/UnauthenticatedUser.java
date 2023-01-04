package sop.ce.gov.controlefinanceiro.api.security.exceptions;

public class UnauthenticatedUser extends RuntimeException{

    public UnauthenticatedUser() {
        super("Usuário não autenticado no Sistema");
    }
}
