package com.example.TimeHarmony.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "Watch")
public class Watch {

    @Id
    private String watch_id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Sellers seller;

    private String watch_image;
    private String watch_description;
    private String watch_name;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp watch_create_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp watch_approval_date;

    private byte state;
    private float price;
    private String brand;
    private String series;
    private String model;
    private String gender;
    private String style_type;
    private String sub_class;
    private String made_label;
    private String calender;
    private String feature;
    private String movement;
    private String functions;
    private String engine;
    private String water_resistant;
    private String band_color;
    private String band_type;
    private String clasp;
    private String bracelet;
    private String dial_type;
    private String dial_color;
    private String crystal;
    private String second_makers;
    private String bezel;
    private String bezel_material;
    private String case_back;
    private String case_dimension;
    private String case_shape;

    public Watch() {
    }

    @Override
    public String toString() {
        return "Watch [watch_id=" + watch_id + ", seller=" + seller + ", watch_image=" + watch_image
                + ", watch_description=" + watch_description + ", watch_name=" + watch_name + ", watch_create_date="
                + watch_create_date + ", watch_approval_date=" + watch_approval_date + ", state=" + state + ", price="
                + price + ", brand=" + brand + ", series=" + series + ", model=" + model + ", gender=" + gender
                + ", style_type=" + style_type + ", sub_class=" + sub_class + ", made_label=" + made_label
                + ", calender=" + calender + ", feature=" + feature + ", movement=" + movement + ", functions="
                + functions + ", engine=" + engine + ", water_resistant=" + water_resistant + ", band_color="
                + band_color + ", band_type=" + band_type + ", clasp=" + clasp + ", bracelet=" + bracelet
                + ", dial_type=" + dial_type + ", dial_color=" + dial_color + ", crystal=" + crystal
                + ", second_makers=" + second_makers + ", bezel=" + bezel + ", bezel_material=" + bezel_material
                + ", case_back=" + case_back + ", case_dimension=" + case_dimension + ", case_shape=" + case_shape
                + "]";
    }

}