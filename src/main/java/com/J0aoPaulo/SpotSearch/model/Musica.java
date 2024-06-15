package com.J0aoPaulo.SpotSearch.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_top_musicas")
    private Long id;

    @ManyToOne
    private Artista artista;

    @Column(name = "_musica")
    private String nome;

    public Musica(String nomeTopMusica) {
        this.nome = nomeTopMusica;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}