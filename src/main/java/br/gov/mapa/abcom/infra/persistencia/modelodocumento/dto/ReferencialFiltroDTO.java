package br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

@Data
@Valid
@ApiModel(description = "Informações de referencial")
public class ReferencialFiltroDTO implements Serializable {

    private String nome;

}
