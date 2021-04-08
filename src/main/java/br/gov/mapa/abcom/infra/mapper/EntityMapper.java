package br.gov.mapa.abcom.infra.mapper;

import java.util.List;

/**
 * Contrato para um dto genérico para o mapeador de entidades.
 *
 * @param <E> - Entity
 * @param <D> - DTO
 */
public interface EntityMapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);

}
