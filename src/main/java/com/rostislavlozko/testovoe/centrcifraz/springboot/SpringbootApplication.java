package com.rostislavlozko.testovoe.centrcifraz.springboot;

import com.rostislavlozko.testovoe.centrcifraz.springboot.dao.NewsRepository;
import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.News;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootApplication {

    @Bean
    InitializingBean saveData(NewsRepository repo){
        return ()->{
            repo.save(new News("Global warming", "Changing of the climate", "Due to global warming, Spain and Portugal are experiencing the worst drought in 1200 years.", 100000));
            repo.save(new News("Intel", "Intel Meteor Lake processors", "Intel Meteor Lake processors will have a third type of cores", 100001));

        };
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
