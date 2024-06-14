package com.J0aoPaulo.SpotSearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMusicaArtista(@JsonProperty("tracks") List<NomeMusica> tracks) {}

