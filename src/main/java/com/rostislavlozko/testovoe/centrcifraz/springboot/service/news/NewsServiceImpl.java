package com.rostislavlozko.testovoe.centrcifraz.springboot.service.news;



import com.rostislavlozko.testovoe.centrcifraz.springboot.dao.NewsRepository;
import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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

    @Transactional
    @Override
    public void delete(int id) {
        newsRepository.deleteById(id);
    }

    @Override
    public List<News> findAllByNewsType(String name) {
        List<News> news = newsRepository.findAllByNewsType(name);
        return news;
    }

}
