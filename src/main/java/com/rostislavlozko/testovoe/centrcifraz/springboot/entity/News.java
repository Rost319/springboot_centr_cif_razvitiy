package com.rostislavlozko.testovoe.centrcifraz.springboot.entity;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class News {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description_short")
    private String descriptionShort;

    @Column(name = "description_full")
    private String descriptionFull;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "type_news_id")
    private NewsType newsType;

    public News() {
    }

    public News(String name, String descriptionShort, String descriptionFull, NewsType newsType) {
        this.name = name;
        this.descriptionShort = descriptionShort;
        this.descriptionFull = descriptionFull;
        this.newsType = newsType;
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
}
