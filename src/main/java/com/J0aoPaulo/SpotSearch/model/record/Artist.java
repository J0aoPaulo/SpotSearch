package com.J0aoPaulo.SpotSearch.model.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Artist(@JsonProperty("items") List<DadosArtista> items) {
}
