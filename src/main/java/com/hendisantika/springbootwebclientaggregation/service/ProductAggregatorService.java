package com.hendisantika.springbootwebclientaggregation.service;

import com.hendisantika.springbootwebclientaggregation.client.ProductClient;
import com.hendisantika.springbootwebclientaggregation.client.PromotionClient;
import com.hendisantika.springbootwebclientaggregation.client.ReviewClient;
import com.hendisantika.springbootwebclientaggregation.dto.Product;
import com.hendisantika.springbootwebclientaggregation.dto.ProductAggregate;
import com.hendisantika.springbootwebclientaggregation.dto.Promotion;
import com.hendisantika.springbootwebclientaggregation.dto.Review;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webclient-aggregation
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/5/23
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
@Service
@AllArgsConstructor
public class ProductAggregatorService {

    private final ProductClient productClient;
    private final PromotionClient promotionClient;
    private final ReviewClient reviewClient;

    public Mono<ProductAggregate> getProduct(Integer productId) {
        return Mono.zip(
                        this.productClient.getProduct(productId),
                        this.promotionClient.getPromotion(productId),
                        this.reviewClient.getReviews(productId)
                )
                .map(this::combine);
    }

    private ProductAggregate combine(Tuple3<Product, Promotion, List<Review>> tuple) {
        return ProductAggregate.create(
                tuple.getT1(),
                tuple.getT2(),
                tuple.getT3()
        );
    }
}
