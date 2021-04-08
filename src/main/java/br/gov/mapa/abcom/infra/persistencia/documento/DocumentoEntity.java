package br.gov.mapa.abcom.infra.persistencia.documento;

import br.gov.mapa.abcom.infra.persistencia.grupoProduto.GrupoProdutoEntity;
import br.gov.mapa.abcom.infra.persistencia.modelodocumento.ReferencialModeloDocumentoEntity;
import br.gov.mapa.abcom.infra.persistencia.produto.Produto;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString(exclude = "listaTipoItens")
@Entity
@Table(schema = "ABCOM_MAPA", name = "S_DOCUMENTO_ABCOM")
public class DocumentoEntity implements Serializable {

    @Id
    @Column(name = "ID_DOCUMENTO_ABCOM")
    @SequenceGenerator(name = "SQ_DOCUMENTO_ABCOM", sequenceName = "ABCOM_MAPA.SQ_DOCUMENTO_ABCOM", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DOCUMENTO_ABCOM")
    private Long id;

    @Column(name = "DS_TITULO_DOCUMENTO")
    private String tituloDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_REFERENCIAL_MODELO_DOC")
    private ReferencialModeloDocumentoEntity referencialModeloDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_IDIOMA")
    private IdiomaEntity idioma;

    @Column(name = "DS_TITULO_TRADUZIDO")
    private String tituloTraduzido;

    @Column(name = "DS_EDICAO")
    private String edicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PRODUTO_ABCOM")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GRUPO_ABCOM")
    private GrupoProdutoEntity grupoProduto;

}
