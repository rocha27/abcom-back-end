//package br.gov.mapa.abcom.infra.persistencia.produto;
//
//import br.gov.mapa.abcom.infra.persistencia.camara.entity.Camara;
//import br.gov.mapa.abcom.infra.persistencia.filtro.AbstractCriteria;
//import br.gov.mapa.abcom.infra.persistencia.produto.dto.ProdutoFiltroDTO;
//import br.gov.mapa.abcom.infra.util.ObjetoUtil;
//import org.springframework.data.domain.Page;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * <p> Classe de persistência referente a entidade {@link Camara}. </p>
// */
//public class ProdutoCustomRepositoryImpl extends AbstractCriteria<Produto, ProdutoFiltroDTO> implements ProdutoCustomRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Page<Produto> filtrar(ProdutoFiltroDTO filter) {
//        StringBuilder jpql = new StringBuilder();
//        Map<String, Object> parametros = new HashMap<>();
//
//        jpql.append(" SELECT e FROM Produto e WHERE e.id > 0");
//        if(ObjetoUtil.isNonNull(filter.getDescricao())){
//            jpql.append("  AND CONCAT(UPPER(e.nome), UPPER(e.sigla)) LIKE :descricao");
//            parametros.put("descricao", "%" + filter.getDescricao().toUpperCase() + "%");
//        }
//        jpql.append(" ORDER BY e.id DESC");
//
//        return getPageableResult(entityManager, jpql.toString(), parametros, Produto.class, filter);
//    }
//
//}
