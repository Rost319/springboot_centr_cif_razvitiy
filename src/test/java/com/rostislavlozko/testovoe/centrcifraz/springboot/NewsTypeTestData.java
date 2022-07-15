package com.rostislavlozko.testovoe.centrcifraz.springboot;


import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.NewsType;
import org.springframework.test.web.servlet.MvcResult;
import java.io.UnsupportedEncodingException;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;



public class NewsTypeTestData {


    public static final NewsType NEWS_TYPE1 = new NewsType("General", "Green");
    public static final NewsType NEWS_TYPE2 = new NewsType("IT", "Red");

    static {
        NEWS_TYPE1.setId(100000);
        NEWS_TYPE2.setId(100003);

    }


    public static final List<NewsType> NEWS_TYPE_LIST = List.of(NEWS_TYPE1, NEWS_TYPE2);

    public static NewsType getNew() {
        return  new NewsType("Created news type", "Create Color");
    }

    public static NewsType getUpdated() {
        return new NewsType("Update news type", "Update color");
    }

    public static void assertMatch(NewsType actual, NewsType expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<NewsType> actual, Iterable<NewsType> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields("newsList").isEqualTo(expected);
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
