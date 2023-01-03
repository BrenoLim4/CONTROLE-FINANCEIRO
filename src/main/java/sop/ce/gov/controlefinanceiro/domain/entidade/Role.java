package sop.ce.gov.controlefinanceiro.domain.entidade;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "financeiro")
@Data
@SequenceGenerator(name = "seq_role", sequenceName = "seq_role", allocationSize = 1, initialValue = 1)
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role")
    private Long id;
    private String nome;
    @Override
    public String getAuthority() {
        return this.nome;
    }
}
