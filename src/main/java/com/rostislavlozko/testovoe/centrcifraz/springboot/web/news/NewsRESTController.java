package com.rostislavlozko.testovoe.centrcifraz.springboot.web.news;


import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.News;
import com.rostislavlozko.testovoe.centrcifraz.springboot.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = NewsRESTController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsRESTController {
    static final String REST_URL = "/api/news";

    @Autowired
    private NewsService newsService;

    @GetMapping
    public List<News> getAll(){
        List<News> allNews = newsService.getAll();
        return allNews;
    }

    @GetMapping("/{id}")
    public News get(@PathVariable int id){
        News news = newsService.get(id);


        return news;
    }

    @PostMapping
    public News save(@RequestBody News news){

        newsService.save(news);
        return news;
    }

    @PutMapping(value = "/{id}")
    public News update(@RequestBody News news, @PathVariable int id) {
        if(newsService.get(id) != null){
            newsService.save(news);
            return news;
        }
       return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){

        newsService.delete(id);
        return "News with ID = " + id + " was deleted";
    }

    @GetMapping("/name/{name}")
    public List<News> showAllEmployeesByName(@PathVariable String name){
        List<News> news = newsService.findAllByName(name);
        return news;
    }
}
