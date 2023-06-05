package com.hendisantika.springbootwebclientaggregation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

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
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    private String type;
    private Double discount;
    private LocalDate endDate;
}
