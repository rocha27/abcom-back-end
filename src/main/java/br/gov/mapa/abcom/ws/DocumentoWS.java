package br.gov.mapa.abcom.ws;

import br.gov.mapa.abcom.dominio.documento.DocumentoService;
import br.gov.mapa.abcom.infra.persistencia.documento.DocumentoEntity;
import br.gov.mapa.abcom.infra.persistencia.documento.IdiomaEntity;
import br.gov.mapa.abcom.infra.persistencia.documento.dto.DocumentoDTO;
import br.gov.mapa.abcom.infra.persistencia.documento.dto.DocumentoFiltroDTO;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto.ModeloDocumentoDTO;
import br.gov.mapa.abcom.infra.persistencia.produto.Produto;
import br.gov.mapa.abcom.infra.persistencia.produto.dto.FiltroProdutoDTO;
import br.gov.mapa.abcom.infra.persistencia.produto.dto.ProdutoDTO;
import br.gov.mapa.servico.resposta.Resposta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "/api/v1/documento",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
@Api(tags = "Documento", description = "Api de documento")
public class DocumentoWS {

    private final DocumentoService documentoService;


    public DocumentoWS(DocumentoService documentoService) {

        this.documentoService = documentoService;
    }

    @PostMapping
    @ApiOperation(value = "Inclui um documento")
    public Resposta<DocumentoDTO> incluir(@RequestBody DocumentoDTO documentoDTO) {
        return new Resposta<>(documentoService.salvar(documentoDTO));
    }

    @GetMapping
    @ApiOperation(value = "Consultar um documento")
    public Resposta<List<DocumentoEntity>> consulta(DocumentoFiltroDTO filtro) {
        return new Resposta<>(documentoService.filtrar(filtro));
    }

    @GetMapping("/idioma")
    @ApiOperation(value = "Consultar Idiomas na Base do Corporativo")
    public Resposta<List<IdiomaEntity>> getIdiomas() {
        return new Resposta<>(documentoService.idioma());
    }

//
//    @PutMapping
//    @ApiOperation(value = "editar um documento")
//    public Resposta<DocumentoDTO> editar(@RequestBody DocumentoDTO documentoDTO) {
//        return new Resposta<>(documentoService.salvar(documentoDTO));
//    }
}
