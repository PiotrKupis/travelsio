package com.travelsio.hotelservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "location")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location extends AbstractEntity{

    @Column(name = "destination_id")
    private Long destinationId;
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
