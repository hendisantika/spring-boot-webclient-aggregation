package com.hendisantika.springbootwebclientaggregation.client;

import com.hendisantika.springbootwebclientaggregation.dto.Promotion;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

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
public class PromotionClient {

    private final WebClient client;
    private final Promotion noPromotion = new Promotion("no-promotion", 0.0, LocalDate.of(2999, 12, 31));

    public PromotionClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:3000/promotions/").build();
    }

    public Mono<Promotion> getPromotion(Integer productId) {
        return this.client
                .get()
                .uri("{productId}", productId)
                .retrieve()
                .bodyToMono(Promotion.class)
                .onErrorReturn(noPromotion); // if no result, it returns 404, so switch to no promotion
    }
}
