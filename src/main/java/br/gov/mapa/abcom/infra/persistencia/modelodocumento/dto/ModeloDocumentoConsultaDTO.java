package br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

@Data
@Valid
@ApiModel(description = "Informações de documento")
public class ModeloDocumentoConsultaDTO implements Serializable {

    @ApiModelProperty(value = "Identificador único de documento")
    private Long id;

    @ApiModelProperty(value = "Nome do documento")
    private String nome;

    private boolean ativo;
}
