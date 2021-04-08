package br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto;

import br.gov.mapa.abcom.infra.enums.NomenclaturaItemDocumentoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Valid
@ApiModel(description = "Informações de documento")
public class TipoItemDTO implements Serializable {

    @ApiModelProperty(value = "Identificador único do tipo de item")
    private Long id;

    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(value = "Nome do tipo de item")
    private String nome;

    private boolean permiteImagem;

    private String nomenclatura;

}
