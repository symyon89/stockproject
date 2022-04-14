package com.stockproject.mapper;

import com.stockproject.dto.ComenziDto;
import com.stockproject.model.Comenzi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProduseMapper {
    ComenziDto mapToDto(Comenzi comenzi);
    Comenzi mapToModel(ComenziDto comenziDto);
}
