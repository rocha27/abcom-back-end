package br.gov.mapa.abcom.infra.persistencia.grupoProduto;

import br.gov.mapa.abcom.infra.persistencia.grupoProduto.dto.GrupoProdutoDTO;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.ReferencialEntity;
import br.gov.mapa.abcom.infra.persistencia.produto.Produto;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(schema = "ABCOM_MAPA", name = "S_VINCULO_GRUPO_PRODUTO")
public class VinculoGrupoProdutoEntity implements Serializable {

    @Id
    @Column(name = "ID_VINCULO_GRUPO_PRODUTO")
    @SequenceGenerator( name = "SQ_VINCULO_GRUPO_PRODUTO", sequenceName = "ABCOM_MAPA.SQ_VINCULO_GRUPO_PRODUTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_VINCULO_GRUPO_PRODUTO")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GRUPO_ABCOM")
    private GrupoProdutoEntity grupoProduto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PRODUTO_ABCOM")
    private Produto produto;

}
