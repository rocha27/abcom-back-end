package br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto;

import br.gov.mapa.abcom.infra.persistencia.modelodocumento.ReferencialEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@Data
@Valid
@ApiModel(description = "Informações de documento")
public class   ModeloDocumentoDTO implements Serializable {

    @ApiModelProperty(value = "Identificador único de modelo documento")
    private Long id;

    @ApiModelProperty(value = "Nome do documento")
    private String nome;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty(value = "entidade do referencial")
    private ReferencialEntity referencial;

    private List<TipoItemDTO> listaTipoItens;

}
