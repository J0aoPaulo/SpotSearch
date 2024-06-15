package com.J0aoPaulo.SpotSearch.repository;

import com.J0aoPaulo.SpotSearch.model.Album;
import com.J0aoPaulo.SpotSearch.model.Artista;
import com.J0aoPaulo.SpotSearch.model.Musica;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    @Query("SELECT a.albuns FROM Artista a WHERE LOWER(a.nome) = LOWER(:nome)")
    Optional<List<Album>> findAllAlbumsByArtistName(@Param("nome") String nome);

    @Query("SELECT a.musicas FROM Artista a WHERE LOWER(a.nome) = LOWER(:nome)")
    Optional<List<Musica>> findAllTracksByArtistName(@Param("nome") String nome, Pageable pageable);
}
