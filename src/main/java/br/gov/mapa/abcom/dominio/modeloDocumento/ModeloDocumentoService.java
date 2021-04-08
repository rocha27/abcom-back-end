package br.gov.mapa.abcom.dominio.modeloDocumento;


import br.gov.mapa.abcom.infra.persistencia.modelodocumento.ReferencialModeloDocumentoEntity;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.ReferencialModeloDocumentoRepository;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.TipoItemEntity;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.TipoItemRepository;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto.ModeloDocumentoDTO;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto.ModeloDocumentoFiltroDTO;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto.TipoItemDTO;
import br.gov.mapa.arquitetura.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.gov.mapa.abcom.infra.util.Constantes.NENHUM_REGISTRO_ENCONTRADO;

@Service
public class ModeloDocumentoService {

    private final ReferencialModeloDocumentoRepository modeloDocumentorepository;
    private final TipoItemRepository repositoryItem;

    @Autowired
    public ModeloDocumentoService(ReferencialModeloDocumentoRepository modeloDocumentorepository, TipoItemRepository repositoryItem)
    {
        this.modeloDocumentorepository = modeloDocumentorepository;
        this.repositoryItem = repositoryItem;
    }

    public ModeloDocumentoDTO salvar(ModeloDocumentoDTO modeloDocumentoDTO) {

        if (modeloDocumentoDTO.getId() != null) {
            try {
                ReferencialModeloDocumentoEntity entity = modeloDocumentorepository.findById(modeloDocumentoDTO.getId()).orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
                entity.setReferencial(modeloDocumentoDTO.getReferencial());
                entity.setNome(modeloDocumentoDTO.getNome());
                modeloDocumentoDTO.getListaTipoItens().forEach(item -> {
                    if (item.getId() != null) {
                        repositoryItem.deleteById(item.getId());
                    }
                });
                modeloDocumentorepository.save(entity);
                salvarItem(modeloDocumentoDTO, entity);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        } else {

             List<ReferencialModeloDocumentoEntity> recuperado = modeloDocumentorepository.findAll();
             validaNomeExisteDocumento(modeloDocumentoDTO, recuperado);
            try {
                ReferencialModeloDocumentoEntity entity = new ReferencialModeloDocumentoEntity();
                entity.setNome(modeloDocumentoDTO.getNome());
                entity.setReferencial(modeloDocumentoDTO.getReferencial());
                modeloDocumentorepository.save(entity);
                salvarItem(modeloDocumentoDTO, entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return modeloDocumentoDTO;
    }

    private void validaNomeExisteDocumento(ModeloDocumentoDTO modeloDocumentoDTO, List<ReferencialModeloDocumentoEntity> recuperado) {
        if (recuperado != null) {
            recuperado.forEach(result -> {
                if (result.getNome().toUpperCase().equals(modeloDocumentoDTO.getNome().toUpperCase())) {
                    throw new BusinessException("O Modelo de Documento já existe para o Referencial selecionado");
                }
            });
        }
    }

    private void salvarItem(ModeloDocumentoDTO modeloDocumentoDTO, ReferencialModeloDocumentoEntity modeloDocumentoEntity) {
        if (modeloDocumentoDTO.getListaTipoItens() != null) {
            modeloDocumentoDTO.getListaTipoItens().forEach(item -> {
                try {
                    TipoItemEntity tipoItem = new TipoItemEntity();
                    tipoItem.setNome(item.getNome());
                    tipoItem.setNomenclatura(item.getNomenclatura());
                    tipoItem.setPermiteImagem(item.isPermiteImagem());
                    tipoItem.setReferencialModeloDocumento(modeloDocumentoEntity);
                    repositoryItem.save(tipoItem);
                } catch (Exception e) {
                    throw new BusinessException("Ocorreu um erro ao salvar modelo documento. " + e.getMessage(), e);
                }
            });
        }
    }

        public ModeloDocumentoDTO consultaById(Long id) {
            ReferencialModeloDocumentoEntity modeloDocumento = modeloDocumentorepository.findById(id).orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));

            ModeloDocumentoDTO modeloDocumentoDTO = new ModeloDocumentoDTO();
            modeloDocumentoDTO.setId(modeloDocumento.getId());
            modeloDocumentoDTO.setReferencial(modeloDocumento.getReferencial());
            modeloDocumentoDTO.setNome(modeloDocumento.getNome());
            recuperaItemEditar(modeloDocumento, modeloDocumentoDTO);

            return modeloDocumentoDTO;
    }

    //todo Fazer mapper depois !!!
    private void recuperaItemEditar(ReferencialModeloDocumentoEntity modeloDocumento, ModeloDocumentoDTO modeloDocumentoDTO) {
        List<TipoItemDTO> listaItemDTO = new ArrayList<>();
        modeloDocumento.getListaTipoItens().forEach(item -> {
            TipoItemDTO dto = new TipoItemDTO();
            dto.setId(item.getId());
            dto.setNome(item.getNome());
            dto.setNomenclatura(item.getNomenclatura());
            dto.setPermiteImagem(item.isPermiteImagem());
            listaItemDTO.add(dto);
        });
        modeloDocumentoDTO.setListaTipoItens(listaItemDTO);
    }

    /**
     *
     * @return
     * @param filtro
     */
    public List<ModeloDocumentoDTO> consulta(ModeloDocumentoFiltroDTO filtro) {

        List<ReferencialModeloDocumentoEntity> listaRetorno = modeloDocumentorepository.filtro(filtro);
        List<ModeloDocumentoDTO> listaRecuperada = new ArrayList<>();
            listaRetorno.forEach(result -> {
                ModeloDocumentoDTO dto = new ModeloDocumentoDTO();
                dto.setNome(result.getNome());
                dto.setId(result.getId());
                dto.setReferencial(result.getReferencial());
                listaRecuperada.add(dto);
            });
        return  listaRecuperada;
    }

    public void deletar(Long idModeloDocumento) {
        if (!modeloDocumentorepository.existsById(idModeloDocumento)) {
            throw new BusinessException(NENHUM_REGISTRO_ENCONTRADO);
        }

        try {
            modeloDocumentorepository.deleteById(idModeloDocumento);
        } catch (Exception e) {
            throw new BusinessException("Ocorreu um erro ao excluir o documento. " + e.getMessage(), e);
        }
    }

}
