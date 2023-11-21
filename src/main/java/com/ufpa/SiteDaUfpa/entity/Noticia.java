package com.ufpa.SiteDaUfpa.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "tb_noticia")
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @Column(nullable = false)
    @Column(columnDefinition="text")
    private String titulo;

    @Column(columnDefinition="text")
    private String descricao;

    @Column(columnDefinition="text")
    private String texto;

    @Column(columnDefinition="text")
    private String imagem;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime hora;

    @ManyToMany
    @JoinTable(
            name = "tb_noticia_has_tb_categoria",
            joinColumns = @JoinColumn(name = "tb_noticia_id"),
            inverseJoinColumns = @JoinColumn(name = "tb_categoria_id")
    )
    private List<Categoria> categorias;
    @PrePersist
    public void dataHoraAtual() {
        data = LocalDate.now();
        hora = LocalTime.now();
    }
    public String HoraFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return hora.format(formatter);
    }

    public Noticia() {
    }
    public Noticia(Long id, String titulo, String descricao, String texto, String imagem, LocalDate data, LocalTime hora, List<Categoria> categorias) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.texto = texto;
        this.imagem = imagem;
        this.data = data;
        this.hora = hora;
        this.categorias = categorias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
