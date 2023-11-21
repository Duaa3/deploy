package com.hotels.com.hotels.wrapper;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class roomWrapper {
    Integer id;
    String name;
    String description;
    Integer price;
    String status;
    Integer hotelId;
    String hotelName;

    public roomWrapper(Integer id, String name , String description , Integer price , Integer hotelId , String hotelName , String status ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.status = status;
    }

    public roomWrapper(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public roomWrapper(Integer id, String name, String description, Integer price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}