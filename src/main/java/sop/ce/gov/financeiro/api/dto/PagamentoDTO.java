package com.pedro5722.controlefinanceiro.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PagamentoDTO {

    private Long id;
    private Integer ano;
    private Integer numero;
    private Date data;
    private BigDecimal valor;
    private String observacao;
    private Integer numeroEmpenho;
    private Integer anoEmpenho;
}
