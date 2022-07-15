package com.rostislavlozko.testovoe.centrcifraz.springboot.web.newstype;


import com.rostislavlozko.testovoe.centrcifraz.springboot.dao.NewsTypeRepository;
import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.NewsType;
import com.rostislavlozko.testovoe.centrcifraz.springboot.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = NewsTypeRESTController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsTypeRESTController {
    static final String REST_URL = "/api/news-type";

    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @GetMapping
    public List<NewsType> getAllNewsType() {
        return newsTypeRepository.findAll();
    }

    @PostMapping
    public NewsType createNewsType(@RequestBody NewsType newsType) {
        return newsTypeRepository.save(newsType);
    }

    @PutMapping("/{newsTypeId}")
    public NewsType updateNewsType(@PathVariable Integer newsTypeId, @RequestBody NewsType newsTypeRequest) {
        return newsTypeRepository.findById(newsTypeId).map(newsType -> {
            newsType.setName(newsTypeRequest.getName());
            newsType.setColor(newsTypeRequest.getColor());
            return newsTypeRepository.save(newsType);
        }).orElseThrow(() -> new ResourceNotFoundException("NewsTypeId " + newsTypeId + " not found"));
    }

    @DeleteMapping("/{newsTypeId}")
    public ResponseEntity<?> deleteNewsType(@PathVariable Integer newsTypeId) {
        return newsTypeRepository.findById(newsTypeId).map(post -> {
            newsTypeRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("NewsTypeId " + newsTypeId + " not found"));
    }

    @GetMapping("/{newsTypeId}")
    public NewsType getNewsType(@PathVariable int newsTypeId){
        Optional<NewsType> optional = newsTypeRepository.findById(newsTypeId);
        if(!optional.isPresent()){
            throw new ResourceNotFoundException("NewsId " + newsTypeId + " not found");
        }

        return optional.get();
    }

}
