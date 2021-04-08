package br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Valid
@ApiModel(description = "Informações de referencial")
public class ReferencialDTO implements Serializable {

    @ApiModelProperty(value = "Identificador único de referencial")
    private Long id;

    @NotEmpty
    @Size(max = 100)
    @ApiModelProperty(value = "Nome do referencial")
    private String nome;

    private boolean ativo;


}
