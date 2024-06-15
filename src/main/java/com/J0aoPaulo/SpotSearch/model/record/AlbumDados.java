package com.J0aoPaulo.SpotSearch.model.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AlbumDados(@JsonProperty("id") String albumId,
                         @JsonProperty("name") String nome,
                         @JsonProperty("album_type") String tipoAlbum,
                         @JsonProperty("total_tracks") int totalFaixas) {
}
