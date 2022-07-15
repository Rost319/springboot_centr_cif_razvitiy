package com.rostislavlozko.testovoe.centrcifraz.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "news_type", uniqueConstraints = { @UniqueConstraint(columnNames = { "name_type", "color_type" }) })
public class NewsType {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_type")
    @NotBlank
    private String name;

    @Column(name = "color_type")
    @NotBlank
    private String color;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "newsType")
    @JsonIgnore
    protected List<News> newsList = new ArrayList<>();

    public NewsType() {
    }

    public NewsType(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public void addNews(News news) {
        newsList.add(news);
    }

    @Override
    public String toString() {
        return "NewsType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        NewsType that = (NewsType) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }

}
