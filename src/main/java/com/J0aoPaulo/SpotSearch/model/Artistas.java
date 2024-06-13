package com.J0aoPaulo.SpotSearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Artistas(@JsonProperty("artists") Artista artists) {
}