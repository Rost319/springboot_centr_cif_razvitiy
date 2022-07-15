package com.rostislavlozko.testovoe.centrcifraz.springboot.entity;


import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "news")
public class News {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "description_short")
    @NotBlank
    private String descriptionShort;

    @Column(name = "description_full")
    @NotBlank
    private String descriptionFull;

    @ManyToOne
    @JoinColumn(name = "type_news_id", nullable = false)
    private NewsType newsType;

    public News() {
    }

    public News(String name, String descriptionShort, String descriptionFull) {
        this.name = name;
        this.descriptionShort = descriptionShort;
        this.descriptionFull = descriptionFull;

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

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public String getDescriptionFull() {
        return descriptionFull;
    }

    public void setDescriptionFull(String descriptionFull) {
        this.descriptionFull = descriptionFull;
    }

    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }


    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descriptionShort='" + descriptionShort + '\'' +
                ", descriptionFull='" + descriptionFull + '\'' +
                ", newsType=" + newsType +
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
        News that = (News) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
