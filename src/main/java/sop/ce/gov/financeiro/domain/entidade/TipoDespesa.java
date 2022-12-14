package sop.ce.gov.financeiro.domain.entidade;

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
@Table(name = "tipo_despesa", schema = "financeiro")
public class TipoDespesa implements Serializable{
    @Id
    private Integer id;
    @Column(nullable = false,length = 1000, insertable = false, updatable = false)
    private String descricao;

    public TipoDespesa(Integer id) {
        this.id = id;
    }
}
