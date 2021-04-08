package br.gov.mapa.abcom.dominio.referencial;

import br.gov.mapa.abcom.infra.persistencia.modelodocumento.ReferencialEntity;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.ReferencialRepository;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto.ReferencialDTO;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto.ReferencialFiltroDTO;
import br.gov.mapa.abcom.infra.persistencia.produto.Produto;
import br.gov.mapa.abcom.infra.persistencia.produto.dto.ProdutoDTO;
import br.gov.mapa.arquitetura.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferencialService {

    private final ReferencialRepository referencialRepository;

    @Autowired
    public ReferencialService(ReferencialRepository referencialRepository) {
        this.referencialRepository = referencialRepository;
    }

    public ReferencialDTO salvar(ReferencialDTO referencialDTO) {
        ReferencialEntity referencial = null;

        if (referencialDTO.getId() != null) {
            referencial = referencialRepository.findById(referencialDTO.getId()).orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
        } else {
            referencial = new ReferencialEntity();
        }
        List<ReferencialEntity> recuperado = referencialRepository.findAll();
        validaNomeExisteReferencial(referencialDTO, recuperado);

        referencial.setNome(referencialDTO.getNome());
        referencial.setAtivo(referencialDTO.isAtivo());
        referencialRepository.save(referencial);
        referencialDTO.setId(referencial.getId());
        return referencialDTO;
    }
    private void validaNomeExisteReferencial(ReferencialDTO referencialDTO, List<ReferencialEntity> recuperado) {
        if (recuperado != null) {
            recuperado.forEach(result -> {
                if (result.getNome().toUpperCase().equals(referencialDTO.getNome().toUpperCase())) {
                    throw new BusinessException("Registro preenchido já existe no sistema!");
                }
            });
        }
    }

//    public List<ReferencialEntity> consulta() {
//        return referencialRepository.findAll();
//    }

    public ReferencialDTO consultaById(Long id) {
        ReferencialEntity referencialRecuperado = referencialRepository.findById(id).orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
        ReferencialDTO dto = new ReferencialDTO();
        if (referencialRecuperado != null) {
            dto.setId(referencialRecuperado.getId());
            dto.setNome(referencialRecuperado.getNome());
            dto.setAtivo(referencialRecuperado.isAtivo());
        }
        return dto;
    }

    public List<ReferencialEntity> filtrar(ReferencialFiltroDTO filtro) {
        List<ReferencialEntity> lista = referencialRepository.filtro(filtro);
        return lista;
    }

    public void deletar(Long idReferencial) {
        ReferencialEntity referencialRecuperado = referencialRepository.findById(idReferencial).orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
        if (referencialRecuperado != null) {
            referencialRepository.deleteById(referencialRecuperado.getId());
        }
    }

}
