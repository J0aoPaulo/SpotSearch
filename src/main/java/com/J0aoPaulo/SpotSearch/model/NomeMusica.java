package com.J0aoPaulo.SpotSearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NomeMusica(@JsonProperty("name") String name) {}
