package br.com.demtech.domain.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Mateus Dantas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ValueDTO(
        @JsonAlias("TipoVeiculo") String vehicleType,
        @JsonAlias("Valor") String value,
        @JsonAlias("Marca") String mark,
        @JsonAlias("Modelo") String model,
        @JsonAlias("AnoModelo") String modelYear,
        @JsonAlias("Combustivel") String fuel,
        @JsonAlias("CodigoFipe") String fipeCode,
        @JsonAlias("MesReferencia") String referenceMonth,
        @JsonAlias("SiglaCombustivel") String fuelAcronym) {
}