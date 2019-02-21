package com.sdata.ecommerce.elasticsearch.conf;

import com.sdata.ecommerce.elasticsearch.client.ElasticsearchRestClientFactoryBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Closeable;

/**
 * @author nedli
 */
@Configuration
@ConditionalOnClass({RestClient.class, ElasticsearchRestClientFactoryBean.class})
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class ElasticsearchAutoConfiguration implements DisposableBean {
    private static final Log logger = LogFactory.getLog(ElasticsearchAutoConfiguration.class);

    private final ElasticsearchProperties properties;

    private Closeable closeable;

    public ElasticsearchAutoConfiguration(ElasticsearchProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public RestHighLevelClient elasticsearchClient() {
        try {
            return createClient();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    private RestHighLevelClient createClient() throws Exception {
        ElasticsearchRestClientFactoryBean factory = new ElasticsearchRestClientFactoryBean();
        factory.setSeeds(properties.getSeeds());
        factory.setMaxRetryTimeoutMillis(properties.getMaxRetryTimeoutMillis());
        factory.afterPropertiesSet();
        RestHighLevelClient client = factory.getObject();
        this.closeable = client;
        return client;
    }

    @Override
    public void destroy() throws Exception {
        if (this.closeable != null) {
            try {
                if (logger.isInfoEnabled()) {
                    logger.info("Closing Elasticsearch client");
                }
                this.closeable.close();
            } catch (final Exception ex) {
                if (logger.isErrorEnabled()) {
                    logger.error("Error closing Elasticsearch client: ", ex);
                }
            }
        }
    }

}
