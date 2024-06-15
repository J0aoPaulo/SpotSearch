package com.J0aoPaulo.SpotSearch.model;

import com.J0aoPaulo.SpotSearch.model.record.NomeTopMusica;
import jakarta.persistence.*;

@Entity
@Table(name = "top_musicas")
public class TopMusica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_top_musicas")
    private Long id;

    @ManyToOne
    private Artista artista;

    @Column(name = "nome_top_musicas")
    private String nome;

    public TopMusica(NomeTopMusica nomeTopMusica) {
        this.nome = nomeTopMusica.name();
    }

    public TopMusica() {

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