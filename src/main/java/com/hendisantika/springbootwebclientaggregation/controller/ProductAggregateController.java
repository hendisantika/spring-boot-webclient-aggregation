package com.hendisantika.springbootwebclientaggregation.controller;

import com.hendisantika.springbootwebclientaggregation.service.ProductAggregatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("product")
public class ProductAggregateController {

    private final ProductAggregatorService productAggregatorService;
}
