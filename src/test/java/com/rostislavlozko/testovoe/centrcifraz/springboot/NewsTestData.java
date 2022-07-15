package com.rostislavlozko.testovoe.centrcifraz.springboot;

import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.News;
import org.springframework.test.web.servlet.MvcResult;
import java.io.UnsupportedEncodingException;

import java.util.List;


import static com.rostislavlozko.testovoe.centrcifraz.springboot.NewsTypeTestData.NEWS_TYPE1;
import static com.rostislavlozko.testovoe.centrcifraz.springboot.NewsTypeTestData.NEWS_TYPE2;
import static org.assertj.core.api.Assertions.assertThat;


public class NewsTestData {


    public static final News NEWS1 = new News("Global warming", "Changing of the climate", "Due to global warming, Spain and Portugal are experiencing the worst drought in 1200 years.");
    public static final News NEWS2 = new News("Intel", "Intel Meteor Lake processors", "Intel Meteor Lake processors will have a third type of cores");
    public static final News NEWS3 = new News("NVIDIA", "Nvidia shares fell 30%", "The developer of graphics processors and chips Nvidia reported on the financial results of the fourth quarter and the entire fiscal year.");

    static {
        NEWS1.setId(100001);
        NEWS2.setId(100004);
        NEWS3.setId(100002);

        NEWS1.setNewsType(NEWS_TYPE1);
        NEWS2.setNewsType(NEWS_TYPE2);
        NEWS3.setNewsType(NEWS_TYPE1);
    }


    public static final List<News> NEWS_LIST = List.of(NEWS1, NEWS3, NEWS2);

    public static News getNew() {
        return  new News("Created news", "Description short", "Description full");
    }

    public static News getUpdated() {
        return new News("Update news", "Description short update", "Description full update");
    }

    public static void assertMatch(News actual, News expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<News> actual, Iterable<News> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields("newsType").isEqualTo(expected);
    }

    public static String getContent(MvcResult mvcResult){
        try {
            return mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
