package com.hendisantika.springbootwebclientaggregation.client;

import com.hendisantika.springbootwebclientaggregation.dto.Review;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webclient-aggregation
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/5/23
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ReviewClient {

    private final WebClient client;

    public ReviewClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:3000/reviews").build();
    }

    public Mono<List<Review>> getReviews(Integer productId) {
        return this.client
                .get()
                .uri(b -> b.queryParam("productId", productId).build())
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .onErrorReturn(Collections.emptyList()); // in case of error, switch it to empty list
    }
}
