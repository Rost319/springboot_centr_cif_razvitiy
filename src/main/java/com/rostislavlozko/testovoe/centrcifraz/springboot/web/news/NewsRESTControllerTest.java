package com.rostislavlozko.testovoe.centrcifraz.springboot.web.news;


import com.rostislavlozko.testovoe.centrcifraz.springboot.dao.NewsRepository;
import com.rostislavlozko.testovoe.centrcifraz.springboot.dao.NewsTypeRepository;
import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.News;
import com.rostislavlozko.testovoe.centrcifraz.springboot.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = NewsRESTControllerTest.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsRESTControllerTest {
    static final String REST_URL = "/api";

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @GetMapping("/news-type/{nameTypeNews}/news")
    public List<News> getAllNewsByNewsType(@PathVariable (value = "nameTypeNews") String nameTypeNews) {
        return newsRepository.findAllByNewsType(nameTypeNews);
    }

    @GetMapping("/news/{id}")
    public News getNews(@PathVariable int newsId){
        News news = newsRepository.findById(newsId).get();

        if(news == null) {
            new ResourceNotFoundException("NewsId " + newsId + " not found");
        }

        return news;
    }

    @PostMapping("/news-type/{newsTypeId}/news")
    public News createNews(@PathVariable (value = "newsTypeId") Integer newsTypeId,
                                 @RequestBody News news) {
        return newsTypeRepository.findById(newsTypeId).map(newsType -> {
            news.setNewsType(newsType);
            return newsRepository.save(news);
        }).orElseThrow(() -> new ResourceNotFoundException("NewsId " + newsTypeId + " not found"));
    }

    @PutMapping("/news-type/{newsTypeId}/news/{newsId}")
    public News updateNews(@PathVariable (value = "newsTypeId") Integer newsTypeId,
                                 @PathVariable (value = "newsId") Integer newsId,
                                  @RequestBody News newsRequest) {
        if (!newsTypeRepository.existsById(newsTypeId)) {
            throw new ResourceNotFoundException("NewsTypeId " + newsTypeId + " not found");
        }

        return newsRepository.findById(newsId).map(news -> {
            news.setNewsType(newsRequest.getNewsType());
            return newsRepository.save(news);
        }).orElseThrow(() -> new ResourceNotFoundException("NewsId " + newsId + "not found"));
    }

    @DeleteMapping("/news-type/{newsTypeId}/news/{newsId}")
    public ResponseEntity<?> deleteNews(@PathVariable (value = "newsTypeId") Integer newsTypeId,
                                           @PathVariable (value = "newsId") Integer newsId) {
        return newsRepository.findByIdAndNewsTypeId(newsId, newsTypeId).map(news -> {
            newsRepository.delete(news);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("News not found with id " + newsId + " and news type id " + newsTypeId));
    }

        @GetMapping("/news")
    public List<News> getAll(){
        List<News> allNews = newsRepository.findAll();
        return allNews;
    }

}
