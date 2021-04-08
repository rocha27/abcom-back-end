package br.gov.mapa.abcom.infra.enums;

import java.util.Arrays;

/**
 * Interface a ser utilizada pelos Enums que são recebidos via WebService
 *
 * @author Samuel Correia Guimarães
 */
public interface CodigoEnum {

    default String getCodigo() {
        return toString();
    }

    static <T extends CodigoEnum> T getEnumPorCodigo(String codigoEnum, T[] values) {
        return Arrays.stream(values).filter(e -> e.getCodigo().equals(codigoEnum)).findAny().orElse(null);
    }

}
