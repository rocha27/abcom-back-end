package br.gov.mapa.abcom.dominio.grupoProduto;

import br.gov.mapa.abcom.infra.persistencia.grupoProduto.GrupoProdutoEntity;
import br.gov.mapa.abcom.infra.persistencia.grupoProduto.GrupoProdutoRepository;
import br.gov.mapa.abcom.infra.persistencia.grupoProduto.VinculoGrupoProdutoEntity;
import br.gov.mapa.abcom.infra.persistencia.grupoProduto.VinculoGrupoProdutoRepository;
import br.gov.mapa.abcom.infra.persistencia.grupoProduto.dto.FiltroGrupoProdutoDTO;
import br.gov.mapa.abcom.infra.persistencia.grupoProduto.dto.GrupoProdutoDTO;
import br.gov.mapa.abcom.infra.persistencia.produto.Produto;
import br.gov.mapa.arquitetura.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GrupoProdutoService {

    private final GrupoProdutoRepository grupoProdutoRepository;
    private final VinculoGrupoProdutoRepository vinculoGrupoProdutoRepository;

    @Autowired
    public GrupoProdutoService(GrupoProdutoRepository grupoProdutoRepository, VinculoGrupoProdutoRepository vinculoGrupoProdutoRepository) {
        this.grupoProdutoRepository = grupoProdutoRepository;
        this.vinculoGrupoProdutoRepository = vinculoGrupoProdutoRepository;
    }
    /**
     *
     * @param grupoProdutoDTO
     * @return
     */

    public GrupoProdutoDTO salvar(GrupoProdutoDTO grupoProdutoDTO) {
        GrupoProdutoEntity grupoProdutoEntity = new GrupoProdutoEntity();
        if (grupoProdutoDTO.getId() != null) {
            grupoProdutoEntity = editarGrupoProduto(grupoProdutoDTO);

        } else {
            grupoProdutoEntity = new GrupoProdutoEntity();
        }

        try {
            grupoProdutoEntity.setNome(grupoProdutoDTO.getNome());
            grupoProdutoEntity.setDescricaoGrupoProduto(grupoProdutoDTO.getDescricaoGrupoProduto());
            grupoProdutoRepository.save(grupoProdutoEntity);
            grupoProdutoDTO.setId(grupoProdutoEntity.getId());
            SalvarVinculoProduto(grupoProdutoDTO, grupoProdutoEntity);
        } catch (Exception e) {
            throw new BusinessException("Ocorreu um erro no servidor");
        }

        return grupoProdutoDTO;
    }

    private GrupoProdutoEntity editarGrupoProduto(GrupoProdutoDTO grupoProdutoDTO) {
        GrupoProdutoEntity grupoProdutoEntity;
        List<VinculoGrupoProdutoEntity> listaVinculo = vinculoGrupoProdutoRepository.findAll();
        grupoProdutoEntity = grupoProdutoRepository.findById(grupoProdutoDTO.getId()).orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
        listaVinculo.forEach(vinculo -> {
            if (grupoProdutoDTO.getId().equals(vinculo.getGrupoProduto().getId())) {
                vinculoGrupoProdutoRepository.deleteById(vinculo.getId());
            }
        });
        return grupoProdutoEntity;
    }

    private void SalvarVinculoProduto(GrupoProdutoDTO grupoProdutoDTO, GrupoProdutoEntity grupoProdutoEntity) {
        if (null != grupoProdutoDTO.getListaProdutoSelecionado()) {
            GrupoProdutoEntity finalGrupoProdutoEntity = grupoProdutoEntity;
            grupoProdutoDTO.getListaProdutoSelecionado().forEach(produto -> {
                VinculoGrupoProdutoEntity novoVinculo = new VinculoGrupoProdutoEntity();
                novoVinculo.setProduto(produto);
                novoVinculo.setGrupoProduto(finalGrupoProdutoEntity);
                vinculoGrupoProdutoRepository.save(novoVinculo);
            });
        }
    }

    private void validaNomeExisteGrupoProduto(GrupoProdutoDTO grupoProdutoDTO, List<GrupoProdutoEntity> recuperado) {
        if(recuperado != null) {
            recuperado.forEach(result -> {
                if (result.getNome().toUpperCase().equals(grupoProdutoDTO.getNome().toUpperCase())) {
                    throw new BusinessException("O grupo de produto preenchido já está cadastrado!");
                }
            });
        }
    }

    public List<GrupoProdutoEntity> consulta() {
        return grupoProdutoRepository.findAll();
    }

    public GrupoProdutoDTO consultaById(Long id) {
        GrupoProdutoEntity grupoProdutoRecuperado = grupoProdutoRepository.findById(id).orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
        GrupoProdutoDTO dto = null;
        try {
            dto = new GrupoProdutoDTO();
            List<VinculoGrupoProdutoEntity> listaVinculo = vinculoGrupoProdutoRepository.findAll();
            List<Produto> listaProduto = new ArrayList<>();

            recuperarProdutoGrupo(grupoProdutoRecuperado, dto, listaVinculo, listaProduto);
        } catch (Exception e) {
            throw new BusinessException("Ocorreu um erro no servidor");
        }
        return dto;
    }

    private void recuperarProdutoGrupo(GrupoProdutoEntity grupoProdutoRecuperado, GrupoProdutoDTO dto, List<VinculoGrupoProdutoEntity> listaVinculo, List<Produto> listaProduto) {
        for (VinculoGrupoProdutoEntity vinculo : listaVinculo) {
            if (vinculo.getGrupoProduto().getId().equals(grupoProdutoRecuperado.getId())) {
                listaProduto.add(vinculo.getProduto());
                dto.setId(grupoProdutoRecuperado.getId());
                dto.setNome(grupoProdutoRecuperado.getNome());
                dto.setDescricaoGrupoProduto(grupoProdutoRecuperado.getDescricaoGrupoProduto());
                dto.setListaProdutoSelecionado((ArrayList<Produto>) listaProduto);
            }
        }
    }

    public List<GrupoProdutoEntity> filtrar(FiltroGrupoProdutoDTO filtro) {
        List<GrupoProdutoEntity> lista = grupoProdutoRepository.filtro(filtro);
        return lista;

    }

    public String deletar(Long idGrupoProduto) {
        GrupoProdutoEntity grupoProdutoRecuperado = grupoProdutoRepository.findById(idGrupoProduto).orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
        String msg = null;
        try {
            List<VinculoGrupoProdutoEntity> listaVinculo = vinculoGrupoProdutoRepository.findAll();
            listaVinculo.forEach(vinculo -> {
                if (vinculo.getGrupoProduto().getId().equals(idGrupoProduto)) {
                    vinculoGrupoProdutoRepository.deleteById(vinculo.getId());
                }
            });
            grupoProdutoRepository.deleteById(grupoProdutoRecuperado.getId());
            msg = "registro excluído com sucesso";
        } catch (Exception e) {
            throw new BusinessException("Ocorreu um erro no servidor");
        }
        return msg;
        }

    }

