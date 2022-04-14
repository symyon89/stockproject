package com.stockproject.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ComenziDto implements Serializable {
    @JsonProperty("id_produs")
    private Integer idProdus;

    @JsonProperty("nume_client")
    private String numeClient;

    private Integer cantitate;
}
