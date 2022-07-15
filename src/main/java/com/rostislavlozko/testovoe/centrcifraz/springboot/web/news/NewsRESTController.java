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
import java.util.Optional;

@RestController
@RequestMapping(value = NewsRESTController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsRESTController {
    static final String REST_URL = "/api/news";

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @GetMapping("/news-type/{nameTypeNews}")
    public List<News> getAllNewsByNewsType(@PathVariable(value = "nameTypeNews") String nameTypeNews) {
        return newsRepository.findAllByNewsType(nameTypeNews);
    }

    @GetMapping("/{newsId}")
    public News getNews(@PathVariable int newsId) {
        Optional<News> optional = newsRepository.findById(newsId);
        if(!optional.isPresent()){
           throw new ResourceNotFoundException("NewsId " + newsId + " not found");
        }

        return optional.get();
    }

    @PostMapping("/news-type/{newsTypeId}")
    public News createNews(@PathVariable(value = "newsTypeId") Integer newsTypeId,
                           @RequestBody News news) {
        return newsTypeRepository.findById(newsTypeId).map(newsType -> {
            news.setNewsType(newsType);
            return newsRepository.save(news);
        }).orElseThrow(() -> new ResourceNotFoundException("NewsId " + newsTypeId + " not found"));
    }

    @PutMapping("/{newsId}/news-type/{newsTypeId}")
    public News updateNews(@PathVariable(value = "newsId") Integer newsId,
                           @PathVariable(value = "newsTypeId") Integer newsTypeId,
                           @RequestBody News newsRequest) {
        if (!newsTypeRepository.existsById(newsTypeId)) {
            throw new ResourceNotFoundException("NewsTypeId " + newsTypeId + " not found");
        }

        return newsRepository.findById(newsId).map(news -> {
            news.setName(newsRequest.getName());
            news.setDescriptionShort(newsRequest.getDescriptionShort());
            news.setDescriptionFull(newsRequest.getDescriptionFull());
            return newsRepository.save(news);
        }).orElseThrow(() -> new ResourceNotFoundException("NewsId " + newsId + "not found"));
    }

    @DeleteMapping("/{newsId}")
    public ResponseEntity<?> deleteNews(@PathVariable(value = "newsId") Integer newsId) {
        return newsRepository.findById(newsId).map(news -> {
            newsRepository.delete(news);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("News not found with id " + newsId));
    }

    @GetMapping
    public List<News> getAllNews() {
        List<News> allNews = newsRepository.findAll();
        return allNews;
    }

}
