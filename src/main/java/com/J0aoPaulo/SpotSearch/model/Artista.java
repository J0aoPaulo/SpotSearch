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
    List<Musica> topMusicas = new ArrayList<>();

    public Artista(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Musica> getTopMusicas() {
        return topMusicas;
    }

    public void setTopMusicas(List<Musica> topMusicas) {
        topMusicas.forEach(m -> m.setArtista(this));
        this.topMusicas = topMusicas;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "nomeArtista='" + nome + '\'' +
                ", topMusicas=" + topMusicas +
                '}';
    }
}