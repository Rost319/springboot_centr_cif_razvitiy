package com.rostislavlozko.testovoe.centrcifraz.springboot.web.news;

import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.rostislavlozko.testovoe.centrcifraz.springboot.NewsTestData.*;
import static com.rostislavlozko.testovoe.centrcifraz.springboot.NewsTypeTestData.NEWS_TYPE2;
import static com.rostislavlozko.testovoe.centrcifraz.springboot.web.json.JsonUtil.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class NewsRESTControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    private static final String REST_URL = NewsRESTController.REST_URL + '/';

    @Autowired
    NewsRESTController newsRESTController;

    @Test
    public void getTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + NEWS1.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        News news = readValue(getContent(mvcResult), News.class);
        assertMatch(news, NEWS1);

    }

    @Test
    public void getAllTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        List<News> list = readValues(getContent(mvcResult), News.class);
        assertMatch(list, NEWS_LIST);

    }


    @Test
    public void createTest() throws Exception {
        News news = getNew();

        String inputJson = writeValue(news);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isOk())
                .andReturn();

        News result = readValue(getContent(mvcResult), News.class);
        news.setId(NEWS_TYPE2.getId() + 1);
        news.getNewsType().setId(NEWS_TYPE2.getId() + 2);
        assertMatch(result, news);

    }

    @Test
    public void updateTest() throws Exception {
        News news = getUpdated();
        int id = NEWS2.getId();
        news.setId(id);

        String inputJson = writeValue(news);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + id)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isOk())
                .andReturn();

        News result = readValue(getContent(mvcResult), News.class);
        assertMatch(result, news);

    }


    @Test
    public void deleteTest() throws Exception {
        int id = NEWS1.getId();
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + id))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(getContent(mvcResult), "News with ID = " + id + " was deleted");
        assertMatch(newsRESTController.getAll(), List.of(NEWS2));

    }

}
