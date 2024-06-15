package com.J0aoPaulo.SpotSearch.model;

import com.J0aoPaulo.SpotSearch.model.record.AlbumDados;
import jakarta.persistence.*;

@Entity
@Table(name = "albuns")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_album")
    private Long id;

    @ManyToOne
    Artista artista;

    @Column(name = "nome_album")
    private String nomeAlbum;

    @Column(name = "tipo_album")
    private String tipoAlbum;

    @Column(name = "faixas_totais")
    private Integer totalFaixas;

    public Album() {

    }

    public Album(AlbumDados album) {
        this.nomeAlbum = album.nome();
        this.tipoAlbum = album.tipoAlbum();
        this.totalFaixas = album.totalFaixas();
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getNomeAlbum() {
        return nomeAlbum;
    }

    public void setNomeAlbum(String nomeAlbum) {
        this.nomeAlbum = nomeAlbum;
    }

    public String getTipoAlbum() {
        return tipoAlbum;
    }

    public void setTipoAlbum(String tipoAlbum) {
        this.tipoAlbum = tipoAlbum;
    }

    public int getTotalFaixas() {
        return totalFaixas;
    }

    public void setTotalFaixas(int totalFaixas) {
        this.totalFaixas = totalFaixas;
    }
}