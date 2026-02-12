package com.krilicar.mappers;

import com.krilicar.dtos.BrandDTO;
import com.krilicar.entities.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandDTO toDTO(Brand brand);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true) // Le code est géré par @PrePersist
    Brand toEntity(BrandDTO brandDTO);
}