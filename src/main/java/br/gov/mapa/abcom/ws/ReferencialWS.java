package br.gov.mapa.abcom.ws;

import br.gov.mapa.abcom.dominio.referencial.ReferencialService;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.ReferencialEntity;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto.ReferencialDTO;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto.ReferencialFiltroDTO;
import br.gov.mapa.servico.resposta.Resposta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(
        path = "/api/v1/referencial",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
@Api(tags = "Referencial", description = "Api de referencial")
public class ReferencialWS {

    private final ReferencialService referencialService;

    @Autowired
    public ReferencialWS(ReferencialService referencialService) {
        this.referencialService = referencialService;
    }

    @PostMapping
    @ApiOperation(value = "Inclui um referencial")
    public Resposta<ReferencialDTO> incluir(@RequestBody ReferencialDTO referencial) {
        return new Resposta<>(referencialService.salvar(referencial));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "editar um referencial")
    public Resposta<ReferencialDTO> editar(@ApiParam(value = "Identificação do registro", required = true) @PathVariable long id,
                                           @RequestBody ReferencialDTO referencial) {
        referencial.setId(id);
        return new Resposta<>(referencialService.salvar(referencial));
    }

    @GetMapping
    @ApiOperation(value = "Consultar referencial")
    public Resposta<List<ReferencialEntity>> consulta(ReferencialFiltroDTO filtro) {
        return new Resposta<>(referencialService.filtrar(filtro));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Consultar referencial por id")
    public Resposta<ReferencialDTO> consultaById(@ApiParam(value = "Identificação do registro", required = true) @PathVariable long id) {
        return new Resposta<>(referencialService.consultaById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "deleta um referencial")
    public Resposta<String> excluir(@ApiParam(value = "Identificação do registro", required = true) @PathVariable long id) {
        referencialService.deletar(id);
        return new Resposta<>();
    }
}
