package br.gov.mapa.abcom.ws;

import br.gov.mapa.servico.resposta.Resposta;
import br.gov.mapa.abcom.dominio.produto.ProdutoService;
import br.gov.mapa.abcom.infra.persistencia.produto.Produto;
import br.gov.mapa.abcom.infra.persistencia.produto.dto.FiltroProdutoDTO;
import br.gov.mapa.abcom.infra.persistencia.produto.dto.ProdutoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(
        path = "/api/v1/produto",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
@Api(tags = "Produto", description = "Api de produto")

public class ProdutoWS {

    private final ProdutoService produtoService;

    public ProdutoWS(ProdutoService produtoService) {

        this.produtoService = produtoService;
    }

    @PostMapping
    @ApiOperation(value = "Inclui um produto")
    public Resposta<ProdutoDTO> incluir(@RequestBody ProdutoDTO produtoDTO) {
        return new Resposta<>(produtoService.salvar(produtoDTO));
    }

    @PutMapping
    @ApiOperation(value = "editar um produto")
    public Resposta<ProdutoDTO> editar(@RequestBody ProdutoDTO produtoDTO) {
        return new Resposta<>(produtoService.salvar(produtoDTO));
    }

    @GetMapping
    @ApiOperation(value = "Consultar produto")
    public Resposta<List<Produto>> consulta(FiltroProdutoDTO filtro) {
        return new Resposta<>(produtoService.filtrar(filtro));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Consultar produto por id")
    public Resposta<ProdutoDTO> consultaById
            (@ApiParam(value = "Identificação do registro",
            required = true)@PathVariable long id) {
        return new Resposta<>(produtoService.consultaById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleta um produto")
    public Resposta<String> excluir
            (@ApiParam(value = "Identificação do registro",
             required = true)@PathVariable long id) {
        return new Resposta<>(produtoService.deletar(id));

    }
}
