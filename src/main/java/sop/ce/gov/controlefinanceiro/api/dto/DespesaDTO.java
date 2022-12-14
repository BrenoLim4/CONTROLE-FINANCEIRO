package sop.ce.gov.controlefinanceiro.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

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
