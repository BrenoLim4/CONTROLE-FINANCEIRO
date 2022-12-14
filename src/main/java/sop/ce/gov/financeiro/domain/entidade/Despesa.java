package sop.ce.gov.financeiro.domain.entidade;

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
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "despesa", schema = "financeiro")
public class Despesa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_protocolo",nullable = false,unique = true)
    private String numeroProtocolo;
    @ManyToOne
    @JoinColumn(name = "id_tipo",referencedColumnName = "id", nullable = false)
    private TipoDespesa tipo;
    @ManyToOne
    @JoinColumn(name="id_status", referencedColumnName = "id", nullable = false)
    private StatusDespesa status;
    @Column(name = "data_protocolo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataProtocolo;
    @Column(name = "date_vencimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    private String credor;
    private String descricao;
    @Column(scale = 2, precision = 16)
    private BigDecimal valor;
    @OneToMany(mappedBy = "idDespesa")
    private List<Empenho> empenhos;

    public Despesa(Long id) {
        this.id = id;
    }
}
