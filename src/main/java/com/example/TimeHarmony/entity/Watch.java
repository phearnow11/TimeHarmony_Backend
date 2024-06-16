package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
import lombok.ToString;

@Entity
@Getter
@Setter
@Indexed
@AllArgsConstructor
@ToString
@Table(name = "Watch")
public class Watch {

    @Id
    private String watch_id;

    @ManyToOne
    @JsonIgnoreProperties(value = { "watches", "handler", "hibernateLazyInitializer", "seller" }, allowSetters = true)
    @JoinColumn(name = "member_id")
    private Sellers seller;

    @Field()
    private String watch_description;
    private String watch_name;

    @ElementCollection
    @CollectionTable(name = "Watch_images", joinColumns = @JoinColumn(name = "watch_id"))
    @Column(name = "image_url")
    private List<String> image_url;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp watch_create_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp watch_approval_date;

    private byte state;
    private long price;
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
}