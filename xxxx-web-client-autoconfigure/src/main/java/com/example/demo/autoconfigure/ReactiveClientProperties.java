package com.example.demo.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "default.reactive.client")
public class ReactiveClientProperties {
	
	public static final int DEFAULT_READ_TIMEOUT = 2000;
	
	public static final int DEFAULT_WRITE_TIMEOUT = 2000;
	
	public static final int DEFAULT_RESPONSE_TIMEOUT = 2000;
	
	public static final int DEFAULT_RETRY_COUNT = 3;
	
	public static final int DEFAULT_RETRY_INTERVAL = 2000;
	
	
	private int readTimeoutMillis = DEFAULT_READ_TIMEOUT;
    private int writeTimeoutMillis = DEFAULT_WRITE_TIMEOUT;
    private int responseTimeoutMillis = DEFAULT_RESPONSE_TIMEOUT;
    private int retryCount = DEFAULT_RETRY_COUNT;
    private int retryInterval = DEFAULT_RETRY_INTERVAL;
    
	public int getReadTimeoutMillis() {
		return readTimeoutMillis;
	}
	public void setReadTimeoutMillis(int readTimeoutMillis) {
		this.readTimeoutMillis = readTimeoutMillis;
	}
	public int getWriteTimeoutMillis() {
		return writeTimeoutMillis;
	}
	public void setWriteTimeoutMillis(int writeTimeoutMillis) {
		this.writeTimeoutMillis = writeTimeoutMillis;
	}
	public int getResponseTimeoutMillis() {
		return responseTimeoutMillis;
	}
	public void setResponseTimeoutMillis(int responseTimeoutMillis) {
		this.responseTimeoutMillis = responseTimeoutMillis;
	}
	public int getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}
	public int getRetryInterval() {
		return retryInterval;
	}
	public void setRetryInterval(int retryInterval) {
		this.retryInterval = retryInterval;
	}
    
	

}
