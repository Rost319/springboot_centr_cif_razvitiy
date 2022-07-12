package com.rostislavlozko.testovoe.centrcifraz.springboot.entity;

import javax.persistence.*;
import java.awt.*;

@Entity
@Table(name = "news_type")
public class NewsType {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_type")
    private String name;

    @Column(name = "color_type")
    private String color;

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

    @Override
    public String toString() {
        return "NewsType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color=" + color +
                '}';
    }
}
