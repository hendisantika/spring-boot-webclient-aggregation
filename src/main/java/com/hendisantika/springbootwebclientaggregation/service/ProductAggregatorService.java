package com.hendisantika.springbootwebclientaggregation.service;

import com.hendisantika.springbootwebclientaggregation.client.ProductClient;
import com.hendisantika.springbootwebclientaggregation.client.PromotionClient;
import com.hendisantika.springbootwebclientaggregation.client.ReviewClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
