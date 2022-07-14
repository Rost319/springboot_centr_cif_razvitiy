package com.rostislavlozko.testovoe.centrcifraz.springboot.web.newstype;


import com.rostislavlozko.testovoe.centrcifraz.springboot.dao.NewsTypeRepository;
import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.NewsType;
import com.rostislavlozko.testovoe.centrcifraz.springboot.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = NewsTypeRESTControllerTest.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsTypeRESTControllerTest {
    static final String REST_URL = "/api";

    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @GetMapping("/news-type")
    public List<NewsType> getAllNewsType() {
        return newsTypeRepository.findAll();
    }

    @PostMapping("/news-type")
    public NewsType createNewsType(@RequestBody NewsType newsType) {
        return newsTypeRepository.save(newsType);
    }

    @PutMapping("/news-type/{newsTypeId}")
    public NewsType updateNewsType(@PathVariable Integer newsTypeId, @RequestBody NewsType newsTypeRequest) {
        return newsTypeRepository.findById(newsTypeId).map(newsType -> {
            newsType.setName(newsTypeRequest.getName());
            newsType.setColor(newsTypeRequest.getColor());
            return newsTypeRepository.save(newsType);
        }).orElseThrow(() -> new ResourceNotFoundException("NewsTypeId " + newsTypeId + " not found"));
    }

    @DeleteMapping("/news-type/{newsTypeId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer newsTypeId) {
        return newsTypeRepository.findById(newsTypeId).map(post -> {
            newsTypeRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("NewsTypeId " + newsTypeId + " not found"));
    }

    @GetMapping("/news-type/{id}")
    public NewsType getNewsType(@PathVariable int newsTypeId){
        NewsType newsType = newsTypeRepository.findById(newsTypeId).get();

        if(newsType == null) {
            new ResourceNotFoundException("NewsTypeId " + newsTypeId + " not found");
        }

        return newsType;
    }

}
