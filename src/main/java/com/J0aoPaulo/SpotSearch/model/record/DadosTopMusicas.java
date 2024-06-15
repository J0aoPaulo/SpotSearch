package com.J0aoPaulo.SpotSearch.model.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTopMusicas(@JsonProperty("tracks") List<NomeTopMusica> nomesTopMusicas) {}