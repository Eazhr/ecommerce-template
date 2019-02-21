package com.sdata.ecommerce.elasticsearch.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * @author nedli
 */
public class ElasticsearchRestClientFactoryBean implements FactoryBean<RestHighLevelClient>, InitializingBean, DisposableBean {
    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchRestClientFactoryBean.class);
    private static final String COLON = ":";
    private static final String COMMA = ",";

    private RestHighLevelClient client;

    private String seeds = "127.0.0.1:9300";
    private int maxRetryTimeoutMillis = 10000;

    @Override
    public void destroy() throws Exception {
        try {
            logger.info("Closing elasticSearch  client");
            if (client != null) {
                client.close();
            }
        } catch (final Exception e) {
            logger.error("Error closing ElasticSearch client: ", e);
        }
    }

    @Override
    public RestHighLevelClient getObject() throws Exception {
        return client;
    }

    @Override
    public Class<?> getObjectType() {
        return RestHighLevelClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        buildClient();
    }

    protected void buildClient() throws Exception {
        Assert.hasText(seeds, "[Assertion failed] elasticsearch seeds settings missing.");

        List<HttpHost> httpHosts = new ArrayList<>();
        for (String node : split(seeds, COMMA)) {
            String hostName = substringBeforeLast(node, COLON);
            String port = substringAfterLast(node, COLON);

            Assert.hasText(hostName, "[Assertion failed] missing host name in 'seeds'");
            Assert.hasText(port, "[Assertion failed] missing port in 'seeds'");

            logger.info("adding node : " + node);
            httpHosts.add(new HttpHost(hostName, Integer.valueOf(port)));
        }

        RestClientBuilder clientBuilder = RestClient.builder(httpHosts.toArray(new HttpHost[]{}))
                .setMaxRetryTimeoutMillis(maxRetryTimeoutMillis)
                .setFailureListener(
                        new RestClient.FailureListener() {
                            @Override
                            public void onFailure(Node node) {
                                logger.warn("Elasticsearch request fail on node {}", node);
                            }
                        }
                );

        client = new RestHighLevelClient(clientBuilder);

    }

    /**
     * Gets seeds
     *
     * @return value of seeds
     */
    public String getSeeds() {
        return seeds;
    }

    /**
     * Sets seeds
     */
    public void setSeeds(String seeds) {
        this.seeds = seeds;
    }

    /**
     * Gets maxRetryTimeoutMillis
     *
     * @return value of maxRetryTimeoutMillis
     */
    public int getMaxRetryTimeoutMillis() {
        return maxRetryTimeoutMillis;
    }

    /**
     * Sets maxRetryTimeoutMillis
     */
    public void setMaxRetryTimeoutMillis(int maxRetryTimeoutMillis) {
        this.maxRetryTimeoutMillis = maxRetryTimeoutMillis;
    }
}
