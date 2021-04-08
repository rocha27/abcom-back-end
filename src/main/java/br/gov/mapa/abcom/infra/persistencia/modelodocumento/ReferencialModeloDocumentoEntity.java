package br.gov.mapa.abcom.infra.persistencia.modelodocumento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@ToString(exclude = "listaTipoItens")
@Entity
@Table(schema = "ABCOM_MAPA", name = "S_REFERENCIAL_MODELO_DOC")
public class ReferencialModeloDocumentoEntity implements Serializable {

    @Id
    @Column(name = "ID_REFERENCIAL_MODELO_DOC")
    @SequenceGenerator(name = "SQ_REFERENCIAL_MODELO_DOC", sequenceName = "ABCOM_MAPA.SQ_REFERENCIAL_MODELO_DOC", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_REFERENCIAL_MODELO_DOC")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_REFERENCIAL")
    private ReferencialEntity referencial;

    @Column(name = "NM_MODELO_DOCUMENTO")
    private String nome;

    @OrderBy("id")
    @OneToMany(mappedBy = "referencialModeloDocumento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoItemEntity> listaTipoItens;

}
