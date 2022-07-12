package com.rostislavlozko.testovoe.centrcifraz.springboot.service;



import com.rostislavlozko.testovoe.centrcifraz.springboot.dao.NewsRepository;
import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.News;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> getAll() {
        return newsRepository.findAll();
    }

    @Override
    public void save(News news) {
        newsRepository.save(news);
    }

    @Override
    public News get(int id) {
        News news = null;
        Optional<News> optional = newsRepository.findById(id);
        if(optional.isPresent()){
            news = optional.get();
        }
        return news;
    }

    @Override
    public void delete(int id) {
        newsRepository.deleteById(id);
    }

    @Override
    public List<News> findAllByName(String name) {
        List<News> news = newsRepository.findAllByName(name);
        return news;
    }

}
