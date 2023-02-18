package com.example.obrestdatajpa.entities;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.time.LocalDate;

@Entity // Identifica como una entidad
@Table(name ="books") // Referencia la table de Base de datos
@ApiModel("Entidad Model BOOK") //Comentando una API
public class Book   {
    //Atributos
    @Id //Identifica La primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Resuelve la Primary Key como Auto Incrementable
    private Long Id;
    private String title;
    private String author;
    private Integer pages;
    @ApiModelProperty("Precio con dos decimales")
    private Double price;
    private LocalDate release;
    private Boolean online;

    //Constructores

    public Book() {
    }

    public Book(Long Id, String title, String author, Integer pages, Double price, LocalDate release, Boolean online) {
        this.Id = Id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.release = release;
        this.online = online;
    }

// Getters y Setters

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }


    //ToString

    @Override
    public String toString() {
        return "Book{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", release=" + release +
                ", online=" + online +
                '}';
    }
}
