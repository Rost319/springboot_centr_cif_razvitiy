package com.rostislavlozko.testovoe.centrcifraz.springboot.dao;



import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
     List<News> findAllByName(String name);
}
