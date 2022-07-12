package com.rostislavlozko.testovoe.centrcifraz.springboot;

import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.News;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.UnsupportedEncodingException;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class NewsTestData {


    public static final News NEWS1 = new News("Global warming", "Changing of the climate", "Due to global warming, Spain and Portugal are experiencing the worst drought in 1200 years.", 100000);
    public static final News NEWS2 = new News("Intel", "Intel Meteor Lake processors", "Intel Meteor Lake processors will have a third type of cores", 100001);

    static {
        NEWS1.setId(100000);
        NEWS2.setId(100001);
    }


    public static final List<News> NEWS_LIST = List.of(NEWS1, NEWS2);

    public static News getNew() {
        return  new News("Created news", "Description short", "Description full", 100002);
    }

    public static News getUpdated() {
        return new News("Update news", "Description short update", "Description full update", 100004);
    }

    public static void assertMatch(News actual, News expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<News> actual, News... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<News> actual, Iterable<News> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static void assertEqualsStatus(MvcResult mvcResult){
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    public static String getContent(MvcResult mvcResult){
        try {
            return mvcResult.getResponse().getContentAsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static ResultMatcher contentJson(News... expected) {
//        return contentJson(List.of(expected));
//    }
//
//    public static ResultMatcher contentJson(Iterable<News> expected) {
//        return result -> assertThat(readListFromJsonMvcResult(result, News.class)).isEqualTo(expected);
//    }
}