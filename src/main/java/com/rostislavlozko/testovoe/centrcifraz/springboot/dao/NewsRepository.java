package com.rostislavlozko.testovoe.centrcifraz.springboot.dao;



import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface NewsRepository extends JpaRepository<News, Integer> {

     @Query("SELECT n FROM News n JOIN  n.newsType t WHERE t.name = ?1")
     List<News> findAllByNewsType(String name);

     Optional<News> findByIdAndNewsTypeId(Integer id, Integer postId);

}


