package com.hendisantika.springbootwebclientaggregation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webclient-aggregation
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/5/23
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class ProductAggregate {
    private Product product;
    private Promotion promotion;
    private List<Review> reviews;
}
