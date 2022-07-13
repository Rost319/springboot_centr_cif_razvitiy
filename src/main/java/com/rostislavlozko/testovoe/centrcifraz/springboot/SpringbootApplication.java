package com.rostislavlozko.testovoe.centrcifraz.springboot;

import com.rostislavlozko.testovoe.centrcifraz.springboot.dao.NewsRepository;
import com.rostislavlozko.testovoe.centrcifraz.springboot.dao.NewsTypeRepository;
import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.News;
import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.NewsType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootApplication {

    @Bean
    InitializingBean saveData(NewsRepository newsRepo, NewsTypeRepository newsTypeRepo) {
        return () -> {
            NewsType newsType1 = new NewsType("General", "Green");
            NewsType newsType2 = new NewsType("IT", "Red");
            newsRepo.save(new News("Global warming", "Changing of the climate", "Due to global warming, Spain and Portugal are experiencing the worst drought in 1200 years.", newsType1));
            newsRepo.save(new News("Intel", "Intel Meteor Lake processors", "Intel Meteor Lake processors will have a third type of cores", newsType2));

        };
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
