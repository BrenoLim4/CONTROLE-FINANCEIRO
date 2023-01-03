package sop.ce.gov.controlefinanceiro.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sop.ce.gov.controlefinanceiro.domain.entidade.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);
}
