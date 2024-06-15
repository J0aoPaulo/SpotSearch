package com.J0aoPaulo.SpotSearch.model;

import com.J0aoPaulo.SpotSearch.model.record.MusicaDados;
import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    Artista artista;

    @Column(name = "nome_musica")
    private String nome;

    public Musica(MusicaDados musicaDados) {
        this.nome = musicaDados.nomeMusica();
    }

    public Musica() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "nome='" + nome + '\'';
    }
}
