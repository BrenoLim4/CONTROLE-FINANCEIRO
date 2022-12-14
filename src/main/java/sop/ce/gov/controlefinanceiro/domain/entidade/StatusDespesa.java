package sop.ce.gov.controlefinanceiro.domain.entidade;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "status_despesa", schema = "financeiro")
public class StatusDespesa implements Serializable{
    @Id
    private Integer id;
    @Column(nullable = false, insertable = false, updatable = false)
    private String status;
    @Column(nullable = false, insertable = false, updatable = false)
    private String descricao;

    public StatusDespesa(Integer id) {
        this.id = id;
    }
}

