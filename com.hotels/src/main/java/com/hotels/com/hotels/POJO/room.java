package com.hotels.com.hotels.POJO;



import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "room.getAllRoom", query = "select new com.hotels.com.hotels.wrapper.roomWrapper(u.id , u.name , u.description , u.price , u.hotel.id , u.hotel.name , u.status) from room u")

@NamedQuery(name = "room.updateRoomStatus" , query = "update room u set u.status =:status where u.id =:id")

@NamedQuery(name = "room.getByHotel", query = "select new com.hotels.com.hotels.wrapper.roomWrapper(u.id , u.name , u.description , u.price , u.hotel.id , u.hotel.name , u.status  ) from room u where u.hotel.id=:id and u.status='true'")

@NamedQuery(name = "room.getRoomById", query = "select new com.hotels.com.hotels.wrapper.roomWrapper(u.id , u.name , u.description , u.price) from room u where u.id=:id")


@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "room")
public class room implements Serializable {
    private static final long serialVersionUID = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_fk", nullable = false)
    private Hotel hotel;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    private String status;


    public room() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hotel=" + hotel+
                ", description='" + description + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}