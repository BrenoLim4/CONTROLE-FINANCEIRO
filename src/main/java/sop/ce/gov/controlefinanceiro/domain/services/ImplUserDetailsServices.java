package sop.ce.gov.controlefinanceiro.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sop.ce.gov.controlefinanceiro.domain.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImplUserDetailsServices implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByLogin(username)
                .flatMap(user ->  Optional.of(new User(user.getUsername(),user.getPassword(), user.getAuthorities())))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário nào encontrado"));
    }

}
