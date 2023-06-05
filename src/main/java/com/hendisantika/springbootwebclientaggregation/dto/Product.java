package com.hendisantika.springbootwebclientaggregation.dto;

import lombok.Data;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-webclient-aggregation
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/5/23
 * Time: 16:41
 * To change this template use File | Settings | File Templates.
 */
@Data
@ToString
public class Product {
    private String id;
    private String description;
    private String category;
}
