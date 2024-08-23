package br.com.demtech.domain.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Mateus Dantas
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelAndYearDTO(@JsonAlias("codigo") String code, @JsonAlias("nome") String name) { }
