package com.rostislavlozko.testovoe.centrcifraz.springboot.service.news;





import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.News;

import java.util.List;

public interface NewsService {

    public List<News> getAll();


    public void save(News news);

    public News get(int id);

    public void delete(int id);

    public List<News> findAllByName(String name);

}
