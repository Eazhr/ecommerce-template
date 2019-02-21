package com.sdata.ecommerce.elasticsearch.tool;

import com.sdata.ecommerce.elasticsearch.conf.ElasticsearchAutoConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static com.sdata.ecommerce.mapper.JacksonMapper.mapper;

/**
 * @author nedli
 */
@Service
public class ElasticsearchTool {
    private static final Log logger = LogFactory.getLog(ElasticsearchAutoConfiguration.class);


    private final RestHighLevelClient client;

    @Autowired
    public ElasticsearchTool(RestHighLevelClient client) {
        this.client = client;
    }

    public void deleteIndex(String indexName) {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        try {
            client.indices().delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            logger.error("Elastic search delete index exception. {}", e);
            throw new RuntimeException("Es delete index failed.");
        }
    }

    public void index(String indexName) {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.source("", XContentType.JSON);
        try {
            client.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            logger.error("Elastic search create index exception. {}", e);
            throw new RuntimeException("Es create index failed.");
        }
    }

    public <T> List<T> search(String idx, String type, SearchSourceBuilder builder, Class<T> clazz) {
        try {
            SearchResponse response = client.search(new SearchRequest(idx).types(type).source(builder));

            List<T> results = new ArrayList<>();

            for (SearchHit searchHit : response.getHits().getHits()) {
                results.add(mapper().readValue(searchHit.getSourceAsString(), clazz));
            }

            return results;
        } catch (IOException e) {
            logger.error("Elastic search met io exception. {}", e);
            throw new RuntimeException("ES IO failed");
        }
    }

    public <T> T get(String idx, String type, String id, Class<T> clazz) {
        try {
            GetResponse response = client.get(new GetRequest(idx, type, id));

            T result = null;

            if (response.isExists()) {
                result = mapper().readValue(response.getSourceAsString(), clazz);
            }

            return result;

        } catch (IOException e) {
            logger.error("Elastic search met io exception. {}", e);
            throw new RuntimeException("ES IO failed");
        }
    }

    private <T> List<T> mapHit(SearchHit[] hits, Class<T> cls, BiConsumer<T, String> idSetter) {
        List<T> results = new ArrayList<>();

        for (SearchHit searchHit : hits) {
            String source = searchHit.getSourceAsString();
            try {
                T t = mapper().readValue(source, cls);
                idSetter.accept(t, searchHit.getId());
                results.add(t);
            } catch (Exception e) {
                logger.warn("exception occurs while map " + source + " to " + cls.getName());
            }
        }

        return results;
    }

    /**
     * Gets client
     *
     * @return value of client
     */
    public RestHighLevelClient getClient() {
        return client;
    }
}
