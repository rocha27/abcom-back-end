package br.gov.mapa.abcom.dominio.produto;

import br.gov.mapa.abcom.infra.persistencia.modelodocumento.ReferencialModeloDocumentoEntity;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.dto.ModeloDocumentoDTO;
import br.gov.mapa.arquitetura.exception.BusinessException;
import br.gov.mapa.abcom.infra.persistencia.produto.Produto;
import br.gov.mapa.abcom.infra.persistencia.produto.ProdutoRepository;
import br.gov.mapa.abcom.infra.persistencia.produto.dto.FiltroProdutoDTO;
import br.gov.mapa.abcom.infra.persistencia.produto.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /**
     *
     * @param produtoDTO
     * @return
     */
    public ProdutoDTO salvar(ProdutoDTO produtoDTO){
        Produto produto = null;
        if (produtoDTO.getId() != null) {
            produto = produtoRepository.findById(produtoDTO.getId()).orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
        } else {
            produto = new Produto();
        }
        List<Produto> recuperado = produtoRepository.findAll();
        validaNomeExisteProduto(produtoDTO, recuperado);

        produto.setNome(produtoDTO.getNome());
        produto.setSituacaoProduto(produtoDTO.getSituacaoProduto());
        produtoRepository.save(produto);
        produtoDTO.setId(produto.getId());
        return produtoDTO;

    }

    private void validaNomeExisteProduto(ProdutoDTO produtoDTO, List<Produto> recuperado) {
        if (recuperado != null) {
            recuperado.forEach(result -> {
                if (result.getNome().toUpperCase().equals(produtoDTO.getNome().toUpperCase())) {
                    throw new BusinessException("O produto preenchido já está cadastrado!");
                }
            });
        }
    }

    public List<Produto> consulta() {
        return produtoRepository.findAll();
    }

    public ProdutoDTO consultaById(Long id) {
        Produto produtoRecuperado = produtoRepository.findById(id).orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
        ProdutoDTO dto = new ProdutoDTO();
        if (produtoRecuperado != null) {
            dto.setId(produtoRecuperado.getId());
            dto.setNome(produtoRecuperado.getNome());
            dto.setSituacaoProduto(produtoRecuperado.getSituacaoProduto());
        }
        return dto;
    }

    public List<Produto> filtrar(FiltroProdutoDTO filtro) {
         List<Produto> lista = produtoRepository.filtro(filtro);
         return lista;
    }


    /**
     *
     * @param idProduto
     * @return
     */
    public String deletar(Long idProduto) {
        Produto produtoRecuperado = produtoRepository.findById(idProduto).orElseThrow(() -> new BusinessException("Nenhum registro encontrado"));
        if (produtoRecuperado != null) {
            produtoRepository.deleteById(produtoRecuperado.getId());
        }
        String msg = "produto excluido com sucesso";

        return  msg;
    }

}
