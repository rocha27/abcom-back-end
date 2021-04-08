package br.gov.mapa.abcom.infra.persistencia.documento.dto;

import br.gov.mapa.abcom.infra.persistencia.documento.IdiomaEntity;
import br.gov.mapa.abcom.infra.persistencia.grupoProduto.GrupoProdutoEntity;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.ReferencialEntity;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.ReferencialModeloDocumentoEntity;
import br.gov.mapa.abcom.infra.persistencia.produto.Produto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.Valid;
import java.io.Serializable;

@Data
@Valid
@ApiModel(description = "Informações do documento")
public class DocumentoDTO implements Serializable {

    @ApiModelProperty(value = "Identificador único de documento")
    private Long id;

    @ApiModelProperty(value = "Título Documento")
    private String tituloDocumento;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty(value = "Entidade do referencial modelo")
    private ReferencialModeloDocumentoEntity referencialModeloDocumento;

    @ApiModelProperty(value = "Idioma")
    private IdiomaEntity idioma;

    @ApiModelProperty(value = "IdIdioma")
    private Long idIdioma;

    @ApiModelProperty(value = "Título Documento")
    private String tituloTraduzido;

    @ApiModelProperty(value = "Edição")
    private String edicao;

    @ApiModelProperty(value = "Entidade do produto")
    private Produto produto;

    @ApiModelProperty(value = "Entidade do produto")
    private GrupoProdutoEntity grupoProduto;


}
