package com.J0aoPaulo.SpotSearch.repository;

import com.J0aoPaulo.SpotSearch.model.Album;
import com.J0aoPaulo.SpotSearch.model.Artista;
import com.J0aoPaulo.SpotSearch.model.Musica;
import com.J0aoPaulo.SpotSearch.model.TopMusica;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    @Query("SELECT a.albuns FROM Artista a WHERE LOWER(a.nome) = LOWER(:nome)")
    Optional<List<Album>> getAllAlbumsByArtistName(@Param("nome") String nome);

    @Query("SELECT a.musicas FROM Artista a WHERE LOWER(a.nome) = LOWER(:nome)")
    Optional<List<Musica>> getAllTracksByArtistName(@Param("nome") String nome, Pageable pageable);

    @Query("SELECT a.topMusicas FROM Artista a WHERE LOWER(a.nome) = LOWER(:nome)")
    Optional<List<TopMusica>> getArtistTopMusics(@Param("nome") String nome);

    @Query("SELECT a FROM Album a WHERE LOWER(a.tipoAlbum) = LOWER(:tipo)")
    Optional<List<Album>> getAlbunsForType(@Param("tipo") String tipo);

    @Query("SELECT a FROM Album a ORDER BY a.totalFaixas DESC")
    Optional<List<Album>> getAlbunsTracksDesc();

    @Query(value = "SELECT nome_artista, nome_album " +
            "FROM artistas JOIN albuns ON artistas.id=albuns.artista_id", nativeQuery = true)
    Optional<List<Map<String, String>>> getArtistNameAndAlbumsName();
}