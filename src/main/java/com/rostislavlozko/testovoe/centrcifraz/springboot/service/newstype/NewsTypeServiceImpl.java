package com.rostislavlozko.testovoe.centrcifraz.springboot.service.newstype;



import com.rostislavlozko.testovoe.centrcifraz.springboot.dao.NewsTypeRepository;
import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.News;
import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.NewsType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class NewsTypeServiceImpl implements NewsTypeService {

    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @Override
    public List<NewsType> getAll() {
        return newsTypeRepository.findAll();
    }

    @Override
    public void save(NewsType newsType) {
        newsTypeRepository.save(newsType);
    }

    @Override
    public NewsType get(int id) {
        NewsType newsType = null;
        Optional<NewsType> optional = newsTypeRepository.findById(id);
        if(optional.isPresent()){
            newsType = optional.get();
        }
        return newsType;
    }

    @Override
    public void delete(int id) {
        newsTypeRepository.deleteById(id);
    }

}
