package br.gov.mapa.abcom.infra.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum NomenclaturaItemDocumentoEnum implements CodigoEnum {

    NENHUM("1", "Nenhum"),
    ALGORISMOS_ROMANOS("2", "Algorismos romanos"),
    CARDINAIS("3", "Cardinais"),
    LETRAS("4", "Letras"),
    DESTACAR_COM_MARCADOR("5", "Destacar com marcador");

    private final String codigo;
    private final String descricao;

}
