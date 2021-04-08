package br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

@Data
@Valid
@ApiModel(description = "Informações de referencial documento")
public class ReferencialModeloDTO implements Serializable {

    @ApiModelProperty(value = "Identificador único de referencial modelo")
    private Long idModelo;

    @ApiModelProperty(value = "Identificador único de referencial")
    private Long idReferencial;

    @ApiModelProperty(value = "Identificador único de modelo documento")
    private Long idDocumento;


}
