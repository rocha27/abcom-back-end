package br.gov.mapa.abcom.infra.persistencia.produto.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Valid
@ApiModel(description = "Informações de produto")
public class ProdutoDTO implements Serializable {

    @ApiModelProperty(value = "Identificador único de produto")
    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 256)
    @ApiModelProperty(value = "Nome do produto")
    private String nome;

    private String situacaoProduto;


}
