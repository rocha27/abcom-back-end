package br.gov.mapa.abcom.ws;

import br.gov.mapa.abcom.dominio.modeloDocumento.ModeloDocumentoService;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto.ModeloDocumentoDTO;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto.ModeloDocumentoFiltroDTO;
import br.gov.mapa.servico.resposta.Resposta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(
        path = "/api/v1/modelo-documento",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
@Api(tags = "Modelo Documento", description = "Api de modelo de documento")
public class ModeloDocumentoWS {

    private final ModeloDocumentoService modeloDocumentoService;

    public ModeloDocumentoWS(ModeloDocumentoService modeloDocumentoService) {
        this.modeloDocumentoService = modeloDocumentoService;
    }

    @GetMapping
    @ApiOperation(value = "Consultar documento")
    public Resposta<List<ModeloDocumentoDTO>> consulta(ModeloDocumentoFiltroDTO filtro) {
        return new Resposta<>(modeloDocumentoService.consulta(filtro));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Consultar documento por id")
    public Resposta<ModeloDocumentoDTO> consultaById(
            @ApiParam(value = "Identificação do registro", required = true) @PathVariable long id) {
        return new Resposta<>(modeloDocumentoService.consultaById(id));
    }

    @PostMapping
    @ApiOperation(value = "Inclui um documento")
    public Resposta<ModeloDocumentoDTO> incluir(@RequestBody ModeloDocumentoDTO documento) {
        return new Resposta<>(modeloDocumentoService.salvar(documento));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "editar um documento")
    public Resposta<ModeloDocumentoDTO> editar(
            @ApiParam(value = "Identificação do registro", required = true) @PathVariable long id,
            @RequestBody ModeloDocumentoDTO documento) {
        documento.setId(id);
        return new Resposta<>(modeloDocumentoService.salvar(documento));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleta um documento")
    public Resposta<String> excluir(@ApiParam(value = "Identificação do registro", required = true) @PathVariable long id) {
        modeloDocumentoService.deletar(id);
        return new Resposta<>();
    }

}
