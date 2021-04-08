package br.gov.mapa.abcom.infra.persistencia.grupoProduto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(schema = "ABCOM_MAPA", name = "S_GRUPO_ABCOM")
public class GrupoProdutoEntity implements Serializable {

    @Id
    @Column(name = "ID_GRUPO_ABCOM")
    @SequenceGenerator( name = "SQ_GRUPO_ABCOM", sequenceName = "ABCOM_MAPA.SQ_GRUPO_ABCOM", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_GRUPO_ABCOM")
    private Long id;

    @Column(name="NM_GRUPO")
    private String nome;

    @Column(name="DS_GRUPO")
    private String descricaoGrupoProduto;

//    @OrderBy("id")
//    @OneToMany(mappedBy = "grupoProduto", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<TipoItemEntity> recuperaGrupo;

}
