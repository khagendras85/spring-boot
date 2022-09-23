package com.example.demo.autoconfigure;

import java.time.Clock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import reactivefeign.ReactiveOptions;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactivefeign.client.ReactiveHttpRequestInterceptors;
import reactivefeign.client.log.DefaultReactiveLogger;
import reactivefeign.client.log.ReactiveLoggerListener;
import reactivefeign.retry.BasicReactiveRetryPolicy;
import reactivefeign.retry.ReactiveRetryPolicy;
import reactivefeign.webclient.WebReactiveOptions;

@Configuration
@ConditionalOnClass(WebClient.class)
@EnableConfigurationProperties(ReactiveClientProperties.class)
public class DefaultReactiveClientConfiguration {
	
	private final Logger logger = LoggerFactory.getLogger(DefaultReactiveClientConfiguration.class);
	
	private static final String API_KEY_HEADER = "X-API-KEY";

	
	@Bean
	@ConditionalOnMissingBean(ReactiveOptions.class)
    public ReactiveOptions reactiveOptions(ReactiveClientProperties clientConfigurationSettings) {			          
        return new WebReactiveOptions.Builder()
                .setReadTimeoutMillis(clientConfigurationSettings.getReadTimeoutMillis())
                .setWriteTimeoutMillis(clientConfigurationSettings.getWriteTimeoutMillis())
                .setResponseTimeoutMillis(clientConfigurationSettings.getResponseTimeoutMillis())
                .build();
    }
	
	@Bean
	@ConditionalOnMissingBean(ReactiveLoggerListener.class)
    public ReactiveLoggerListener loggerListener() {
        return new DefaultReactiveLogger(Clock.systemUTC(), logger);
    }
	
	

    @Bean
    @ConditionalOnMissingBean(ReactiveHttpRequestInterceptor.class)
    public ReactiveHttpRequestInterceptor apiKeyIntercepter() {
        return ReactiveHttpRequestInterceptors.addHeader(API_KEY_HEADER, "testing");
    }
    
    @Bean
    @ConditionalOnMissingBean(ReactiveRetryPolicy.class)
    public ReactiveRetryPolicy reactiveRetryPolicy(ReactiveClientProperties clientConfigurationSettings) {
        return BasicReactiveRetryPolicy.retryWithBackoff(clientConfigurationSettings.getRetryCount(), clientConfigurationSettings.getRetryInterval());
    }

}
