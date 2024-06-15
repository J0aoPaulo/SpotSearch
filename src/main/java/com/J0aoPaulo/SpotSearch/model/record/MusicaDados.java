package com.J0aoPaulo.SpotSearch.model.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MusicaDados(@JsonProperty("name") String nomeMusica) {
}
