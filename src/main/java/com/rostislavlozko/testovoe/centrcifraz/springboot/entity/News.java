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

    @Column(name = "type_news_id")
    private int typeNewsId;

    public News() {
    }

    public News(String name, String surname, String department, int salary) {
        this.name = name;
        this.descriptionShort = surname;
        this.descriptionFull = department;
        this.typeNewsId = salary;
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

    public void setDescriptionShort(String surname) {
        this.descriptionShort = surname;
    }

    public String getDescriptionFull() {
        return descriptionFull;
    }

    public void setDescriptionFull(String department) {
        this.descriptionFull = department;
    }

    public int getTypeNewsId() {
        return typeNewsId;
    }

    public void setTypeNewsId(int salary) {
        this.typeNewsId = salary;
    }


}
