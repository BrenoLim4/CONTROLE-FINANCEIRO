package com.pedro5722.controlefinanceiro.api.dto;

import com.pedro5722.controlefinanceiro.domain.entidade.Empenho;
import com.pedro5722.controlefinanceiro.domain.entidade.StatusDespesa;
import com.pedro5722.controlefinanceiro.domain.entidade.TipoDespesa;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class DespesaDTO {

    private Long id;

    private String numeroProtocolo;

    private Integer tipo;
    private Integer status;
    private Date dataProtocolo;
    private Date dataVencimento;
    private String credor;
    private String descricao;
    private BigDecimal valor;


}
