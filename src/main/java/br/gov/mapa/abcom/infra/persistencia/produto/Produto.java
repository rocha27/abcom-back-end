package br.gov.mapa.abcom.infra.persistencia.produto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(schema = "ABCOM_MAPA", name = "S_PRODUTO_ABCOM")
public class Produto implements Serializable {

    @Id
    @Column(name = "ID_PRODUTO_ABCOM")
    @SequenceGenerator( name = "SQ_PRODUTO_ABCOM", sequenceName = "ABCOM_MAPA.SQ_PRODUTO_ABCOM", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO_ABCOM")
    private Long id;

    @Column(name="NM_PRODUTO_ABCOM")
    private String nome;

    @Column(name="ST_PRODUTO")
    private String situacaoProduto;

}
