package br.gov.mapa.abcom.infra.persistencia.grupoProduto.dto;

import br.gov.mapa.abcom.infra.persistencia.produto.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
@Valid
@ApiModel(description = "Informações de grupo de produto")
public class GrupoProdutoDTO {

    @ApiModelProperty(value = "Identificador único de grupo de produto")
    private Long id;

    @NotNull
    @NotEmpty
    @Size(max = 256)
    @ApiModelProperty(value = "Nome do grupo de produto")
    private String nome;

    private String descricaoGrupoProduto;

    private ArrayList<Produto> listaProdutoSelecionado;
}
