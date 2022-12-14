package sop.ce.gov.financeiro.domain.entidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="pagamento", schema = "financeiro")
public class Pagamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer ano;
    @Column(nullable = false)
    private Integer numero;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(nullable = false,scale = 2, precision = 16)
    private BigDecimal valor;
    private String observacao;
    @Column(name = "id_Empenho")
    private Long idEmpenho;

}
