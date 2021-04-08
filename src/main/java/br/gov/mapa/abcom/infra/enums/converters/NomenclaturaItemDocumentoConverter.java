package br.gov.mapa.abcom.infra.enums.converters;

import br.gov.mapa.abcom.infra.enums.CodigoEnum;
import br.gov.mapa.abcom.infra.enums.NomenclaturaItemDocumentoEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class NomenclaturaItemDocumentoConverter implements AttributeConverter<NomenclaturaItemDocumentoEnum, String> {

    @Override
    public String convertToDatabaseColumn(NomenclaturaItemDocumentoEnum attribute) {
        return attribute.getCodigo();
    }

    @Override
    public NomenclaturaItemDocumentoEnum convertToEntityAttribute(String dbData) {
        return CodigoEnum.getEnumPorCodigo(dbData, NomenclaturaItemDocumentoEnum.values());
    }
}

