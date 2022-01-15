package com.zq.fallback;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ServiceProviderFallback implements FallbackProvider {

    private final Logger logger = LoggerFactory.getLogger(FallbackProvider.class);

    @Override
    public ClientHttpResponse fallbackResponse(Throwable cause) {
        if (cause != null & cause.getCause() != null) {
            String reason = cause.getCause().getMessage();
            logger.info("Exception {}", reason);
        }
        return fallbackResponse();
    }

    /**
     * 告诉 Zuul 我们要熔断的服务
     *
     * @return
     */
    @Override
    public String getRoute() {
        return "service-provider1";
    }

    /**
     * 告诉 zuul 服务出现异常时我们返回预设的信息是什么
     *
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse() {
        // 匿名类
        return new ClientHttpResponse() {
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("The service is unavailable.".getBytes());
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }
        };
    }
}
