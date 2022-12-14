package sop.ce.gov.controlefinanceiro.domain.entidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="empenho", schema = "financeiro")
public class Empenho implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer ano;
    @Column(nullable = false, unique = true)
    private Integer numero;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(nullable = false,  precision = 16, scale = 2)
    private BigDecimal valor;
    private String observacao;
    @OneToMany(mappedBy = "idEmpenho")
    private List<Pagamento> pagamentos;
    @Column(name = "id_despesa")
    private Long idDespesa;

}
