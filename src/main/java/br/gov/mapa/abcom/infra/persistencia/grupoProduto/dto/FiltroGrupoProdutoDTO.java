package br.gov.mapa.abcom.infra.persistencia.grupoProduto.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

@Data
@Valid
@ApiModel(description = "Informações de grupo de produto")
public class FiltroGrupoProdutoDTO implements Serializable {

    private String nome;

}
