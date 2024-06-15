package com.J0aoPaulo.SpotSearch.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome_artista")
    String nome;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL)
    List<TopMusica> topMusicas = new ArrayList<>();

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL)
    List<Musica> musicas = new ArrayList<>();

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL)
    List<Album> albuns = new ArrayList<>();

    public Artista(String nome) {
        this.nome = nome;
    }

    public Artista() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<TopMusica> getTopMusicas() {
        return topMusicas;
    }

    public void setTopMusicas(List<TopMusica> topMusicas) {
        topMusicas.forEach(m -> m.setArtista(this));
        this.topMusicas = topMusicas;
    }

    public List<Album> getAlbums() {
        return albuns;
    }

    public void setAlbums(List<Album> albums) {
        albums.forEach(a -> a.setArtista(this));
        this.albuns = albums;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        musicas.forEach(m -> m.setArtista(this));
        this.musicas = musicas;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "nomeArtista='" + nome + '\'' +
                ", topMusicas=" + topMusicas +
                '}';
    }
}