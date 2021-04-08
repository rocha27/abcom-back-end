package br.gov.mapa.abcom.infra.persistencia.produto.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

@Data
@Valid
@ApiModel(description = "Informações de produto")
public class FiltroProdutoDTO implements Serializable {

    private String nome;

}
