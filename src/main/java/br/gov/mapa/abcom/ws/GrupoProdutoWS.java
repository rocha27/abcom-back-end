package br.gov.mapa.abcom.ws;

import br.gov.mapa.abcom.dominio.grupoProduto.GrupoProdutoService;
import br.gov.mapa.abcom.infra.persistencia.grupoProduto.GrupoProdutoEntity;
import br.gov.mapa.abcom.infra.persistencia.grupoProduto.dto.FiltroGrupoProdutoDTO;
import br.gov.mapa.abcom.infra.persistencia.grupoProduto.dto.GrupoProdutoDTO;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto.ModeloDocumentoDTO;
import br.gov.mapa.servico.resposta.Resposta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(
        path = "/api/v1/grupoProduto",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
@Api(tags = "GrupoProduto", description = "Api de produto")

public class GrupoProdutoWS {

    private final GrupoProdutoService grupoProdutoService;

    public GrupoProdutoWS(GrupoProdutoService grupoProdutoService) {

        this.grupoProdutoService = grupoProdutoService;
    }

    @PostMapping
    @ApiOperation(value = "Inclui um grupo de produto")
    public Resposta<GrupoProdutoDTO> incluir(@RequestBody GrupoProdutoDTO grupoProdutoDTO) {
        return new Resposta<>(grupoProdutoService.salvar(grupoProdutoDTO));
    }

    @GetMapping
    @ApiOperation(value = "Consultar grupo de produto")
    public Resposta<List<GrupoProdutoEntity>> consulta(FiltroGrupoProdutoDTO filtro) {
        return new Resposta<>(grupoProdutoService.filtrar(filtro));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Consultar grupo de produto por id")
    public Resposta<GrupoProdutoDTO> consultaById
            (@ApiParam(value = "Identificação do registro", required = true)@PathVariable long id) {
        return new Resposta<>(grupoProdutoService.consultaById(id));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "editar um grupo de produto")
    public Resposta<GrupoProdutoDTO> editar(
            @ApiParam(value = "Identificação do registro", required = true) @PathVariable long id,
            @RequestBody GrupoProdutoDTO grupoProdutoDTO) {
        grupoProdutoDTO.setId(id);
        return new Resposta<>(grupoProdutoService.salvar(grupoProdutoDTO));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleta um grupo de produto")
    public Resposta<String> excluir
            (@ApiParam(value = "Identificação do registro",
                    required = true)@PathVariable long id) {
        return new Resposta<>(grupoProdutoService.deletar(id));
    }
}
