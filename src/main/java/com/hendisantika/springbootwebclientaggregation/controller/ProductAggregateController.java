package com.hendisantika.springbootwebclientaggregation.controller;

import com.hendisantika.springbootwebclientaggregation.dto.ProductAggregate;
import com.hendisantika.springbootwebclientaggregation.service.ProductAggregatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webclient-aggregation
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/5/23
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductAggregateController {

    private final ProductAggregatorService productAggregatorService;

    @GetMapping("/{productId}")
    public Mono<ResponseEntity<ProductAggregate>> getProduct(@PathVariable Integer productId) {
        return this.productAggregatorService.getProduct(productId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
