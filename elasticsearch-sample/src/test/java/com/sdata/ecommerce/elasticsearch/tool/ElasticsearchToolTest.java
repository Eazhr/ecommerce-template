package com.sdata.ecommerce.elasticsearch.tool;

import com.sdata.ecommerce.domain.Customer;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author nedli
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchToolTest {
    @Autowired
    private ElasticsearchTool elasticsearchTool;

    @Test
    public void testGet() {
        Customer customer = elasticsearchTool.get("customer", "_doc", "25", Customer.class);
        Assert.assertNotNull(customer);
        Assert.assertEquals(customer.getLastname(), "Ayala");
    }

    @Test
    public void testMatchAllSearch() {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        List<Customer> customers = elasticsearchTool.search("customer", "_doc", builder, Customer.class);
        Assert.assertNotNull(customers);
    }

    @Test
    public void testNameSearch() {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("lastname", "Ayala"));
        List<Customer> customers = elasticsearchTool.search("customer", "_doc", builder, Customer.class);
        Assert.assertNotNull(customers);
        Assert.assertEquals(customers.get(0).getLastname(), "Ayala");
    }

    public void createIndex() {

    }
}
