package com.rostislavlozko.testovoe.centrcifraz.springboot.web.newstype;


import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.NewsType;
import com.rostislavlozko.testovoe.centrcifraz.springboot.service.newstype.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = NewsTypeRESTController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsTypeRESTController {
    static final String REST_URL = "/api/news-type";

    @Autowired
    private NewsTypeService newsTypeService;

    @GetMapping
    public List<NewsType> getAll(){
        List<NewsType> allNews = newsTypeService.getAll();
        return allNews;
    }

    @GetMapping("/{id}")
    public NewsType get(@PathVariable int id){
        NewsType newsType = newsTypeService.get(id);

        return newsType;
    }

    @PostMapping
    public NewsType save(@RequestBody NewsType newsType){

        newsTypeService.save(newsType);
        return newsType;
    }

    @PutMapping(value = "/{id}")
    public NewsType update(@RequestBody NewsType newsType, @PathVariable int id) {
        if(newsTypeService.get(id) != null){
            newsTypeService.save(newsType);
            return newsType;
        }
       return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){

        newsTypeService.delete(id);
        return "News type with ID = " + id + " was deleted";
    }

}
