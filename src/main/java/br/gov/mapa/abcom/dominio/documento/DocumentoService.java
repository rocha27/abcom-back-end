package br.gov.mapa.abcom.dominio.documento;

import br.gov.mapa.abcom.infra.persistencia.documento.DocumentoEntity;
import br.gov.mapa.abcom.infra.persistencia.documento.DocumentoRepository;
import br.gov.mapa.abcom.infra.persistencia.documento.IdiomaEntity;
import br.gov.mapa.abcom.infra.persistencia.documento.IdiomaRepository;
import br.gov.mapa.abcom.infra.persistencia.documento.dto.DocumentoDTO;
import br.gov.mapa.abcom.infra.persistencia.documento.dto.DocumentoFiltroDTO;
import br.gov.mapa.arquitetura.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final IdiomaRepository idiomaRepository;

    @Autowired
    public DocumentoService(DocumentoRepository documentoRepository, IdiomaRepository idiomaRepository) {
        this.documentoRepository = documentoRepository;
        this.idiomaRepository = idiomaRepository;
    }
    /**
     *
     * @param documentoDTO
     * @return
     */
    public DocumentoDTO salvar(DocumentoDTO documentoDTO) {
        return documentoDTO;
    }

    private void validaNomeExisteDocumento(DocumentoDTO documentoDTO, List<DocumentoEntity> recuperado) {
        if (recuperado != null) {
            recuperado.forEach(result -> {
                if (result.getEdicao().equals(documentoDTO.getEdicao())) {
                    throw new BusinessException("Documento já cadastrado!");
                }
            });
        }
    }

    public List<IdiomaEntity> idioma() {
        List<IdiomaEntity> idioma =  idiomaRepository.findAll();
        return idioma;
    }
    public List<DocumentoEntity> consulta() {
        return documentoRepository.findAll();
    }

    public DocumentoDTO consultaById(Long id) {
        DocumentoEntity documentoRecuperado = documentoRepository.findById(id).orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
        DocumentoDTO dto = new DocumentoDTO();
        if (documentoRecuperado != null) {
            dto.setId(documentoRecuperado.getId());
            dto.setEdicao(documentoRecuperado.getEdicao());
        }
        return dto;
    }

     public List<DocumentoEntity> filtrar(DocumentoFiltroDTO filtro) {
         List<DocumentoEntity> lista = documentoRepository.filtro(filtro);
         return lista;
    }

}
