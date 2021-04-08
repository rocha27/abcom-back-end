package br.gov.mapa.abcom.infra.persistencia.modelodocumento;

import br.gov.mapa.abcom.infra.enums.NomenclaturaItemDocumentoEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(schema = "ABCOM_MAPA", name = "S_TIPO_ITEM")
public class TipoItemEntity implements Serializable {

    @Id
    @Column(name = "ID_TIPO_ITEM")
    @SequenceGenerator(name = "SQ_TIPO_ITEM", sequenceName = "ABCOM_MAPA.SQ_TIPO_ITEM", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIPO_ITEM")
    private Long id;

    @Column(name = "NM_TIPO_ITEM")
    private String nome;

    @Column(name = "ST_VINCULA_IMAGEM")
    private boolean permiteImagem;

    @Column(name = "CS_VINCULA_NOMENCLATURA")
    private String nomenclatura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_REFERENCIAL_MODELO_DOC")
    private ReferencialModeloDocumentoEntity referencialModeloDocumento;

}
