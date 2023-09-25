package com.travelsio.hotelservice.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private String name;
    private Integer starsNumber;
    private Double reviewScore;
    private String reviewsNumber;
    private BigDecimal price;
    private String url;
    private String photoUrl;
}
