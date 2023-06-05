package com.hendisantika.springbootwebclientaggregation.client;

import com.hendisantika.springbootwebclientaggregation.dto.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webclient-aggregation
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/5/23
 * Time: 16:43
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ProductClient {

    private final WebClient client;

    public ProductClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:3000/products/").build();
    }

    public Mono<Product> getProduct(Integer productId) {
        return this.client
                .get()
                .uri("{productId}", productId)
                .retrieve()
                .bodyToMono(Product.class)
                .onErrorResume(ex -> Mono.empty()); // switch it to empty in case of error
    }
}
