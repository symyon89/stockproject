package com.stockproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stockproject.model.StatusComanda;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComenziResponseDto {
    @JsonProperty("id_comanda")
    private Integer idComanda;
    @JsonProperty("status_comanda")
    private StatusComanda statusComanda;
}
