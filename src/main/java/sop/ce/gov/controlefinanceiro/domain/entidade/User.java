package sop.ce.gov.controlefinanceiro.domain.entidade;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user", schema = "financeiro")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;
    private String password;
    private String username;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "financeiro",name = "users_roles", uniqueConstraints = @UniqueConstraint(columnNames= {"user_id", "role_id"}, name = "uk_user_role"),
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", table = "user", unique = true,
    foreignKey = @ForeignKey(name = "fk_user", value = ConstraintMode.CONSTRAINT)),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", table = "role",
    foreignKey = @ForeignKey(name = "fk_role", value = ConstraintMode.CONSTRAINT))
    )
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
