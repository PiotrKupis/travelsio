package com.travelsio.hotelservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location extends AbstractEntity{

    @Column(name = "external_id")
    private Long externalId;
    private String country;
    private String region;
    private String name;
    private String label;
    private String type;
    @Column(name = "time_zone")
    private String timeZone;
    private Double latitude;
    private Double longitude;
    @Column(name = "hotels_quantity")
    private Integer hotelsQuantity;
}
