package com.rostislavlozko.testovoe.centrcifraz.springboot.service.newstype;





import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.News;
import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.NewsType;

import java.util.List;

public interface NewsTypeService {

    public List<NewsType> getAll();


    public void save(NewsType newsType);

    public NewsType get(int id);

    public void delete(int id);

}
