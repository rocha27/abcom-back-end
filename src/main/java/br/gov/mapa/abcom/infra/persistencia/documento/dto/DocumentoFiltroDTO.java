package br.gov.mapa.abcom.infra.persistencia.documento.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

@Data
@Valid
@ApiModel(description = "Informações de documento")
public class DocumentoFiltroDTO implements Serializable {

    private String nome;
}
