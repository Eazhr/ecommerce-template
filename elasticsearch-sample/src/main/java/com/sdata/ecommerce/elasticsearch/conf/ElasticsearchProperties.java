package com.sdata.ecommerce.elasticsearch.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author nedli
 */
@ConfigurationProperties(prefix = "elastic")
public class ElasticsearchProperties {
    /**
     * Elasticsearch seeds connect to.
     */
    private String seeds;
    /**
     * Elasticsearch rest client max retry timeout million seconds.
     */
    private Integer maxRetryTimeoutMillis;

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
    public Integer getMaxRetryTimeoutMillis() {
        return maxRetryTimeoutMillis;
    }

    /**
     * Sets maxRetryTimeoutMillis
     */
    public void setMaxRetryTimeoutMillis(Integer maxRetryTimeoutMillis) {
        this.maxRetryTimeoutMillis = maxRetryTimeoutMillis;
    }
}
