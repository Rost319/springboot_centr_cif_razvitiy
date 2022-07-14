package com.rostislavlozko.testovoe.centrcifraz.springboot.dao;

import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface NewsTypeRepository extends JpaRepository<NewsType, Integer> {
}
