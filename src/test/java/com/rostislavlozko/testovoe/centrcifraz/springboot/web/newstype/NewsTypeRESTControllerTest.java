package com.rostislavlozko.testovoe.centrcifraz.springboot.web.newstype;

import com.rostislavlozko.testovoe.centrcifraz.springboot.entity.NewsType;
import com.rostislavlozko.testovoe.centrcifraz.springboot.web.news.NewsRESTController;
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

import static com.rostislavlozko.testovoe.centrcifraz.springboot.NewsTestData.NEWS2;
import static com.rostislavlozko.testovoe.centrcifraz.springboot.NewsTypeTestData.*;
import static com.rostislavlozko.testovoe.centrcifraz.springboot.NewsTypeTestData.NEWS_TYPE1;
import static com.rostislavlozko.testovoe.centrcifraz.springboot.web.json.JsonUtil.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class NewsTypeRESTControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    private static final String REST_URL = NewsTypeRESTController.REST_URL + '/';

    @Autowired
    NewsTypeRESTController newsTypeRESTController;

    @Autowired
    NewsRESTController newsRESTController;

    @Test
    public void getTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + NEWS_TYPE1.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        NewsType newsType = readValue(getContent(mvcResult), NewsType.class);
        assertMatch(newsType, NEWS_TYPE1);

    }

    @Test
    public void getTestNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        List<NewsType> list = readValues(getContent(mvcResult), NewsType.class);
        assertMatch(list, NEWS_TYPE_LIST);

    }


    @Test
    public void createTest() throws Exception {
        NewsType newsType = getNew();

        String inputJson = writeValue(newsType);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isOk())
                .andReturn();

        NewsType result = readValue(getContent(mvcResult), NewsType.class);
        newsType.setId(NEWS2.getId() + 1);
        assertMatch(result, newsType);

    }

    @Test
    public void updateTest() throws Exception {
        NewsType newsType = getUpdated();
        int id = NEWS_TYPE2.getId();
        newsType.setId(id);

        String inputJson = writeValue(newsType);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + id)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isOk())
                .andReturn();

        NewsType result = readValue(getContent(mvcResult), NewsType.class);
        assertMatch(result, newsType);

    }

    @Test
    public void updateTestNotFound() throws Exception {
        NewsType newsType = getUpdated();
        newsType.setId(1);

        String inputJson = writeValue(newsType);
        this.mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isNotFound());

    }


    @Test
    public void deleteTest() throws Exception {
        int id = NEWS_TYPE1.getId();
        this.mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + id))
                .andExpect(status().isOk());

        assertMatch(newsTypeRESTController.getAllNewsType(), List.of(NEWS_TYPE2));

    }

    @Test
    public void deleteTestNotFound() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + 1))
                .andExpect(status().isNotFound());
    }

}
