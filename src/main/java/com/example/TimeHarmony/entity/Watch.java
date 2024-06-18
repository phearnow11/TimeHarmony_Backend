package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

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
    @JsonIgnoreProperties(value = { "watches", "handler", "hibernateLazyInitializer", "seller", "user_log_info",
            "member_image", "first_name", "last_name", "is_active", "email", "phone", "last_login_date",
            "last_logout_date", "addresses", "myOrders", "google_id" }, allowSetters = true)
    @JoinColumn(name = "member_id")
    private Sellers seller;

    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String watch_description;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String watch_name;

    @ElementCollection
    @CollectionTable(name = "Watch_images", joinColumns = @JoinColumn(name = "watch_id"))
    @Column(name = "image_url")
    private List<String> image_url;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp watch_create_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp watch_approval_date;

    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private byte state;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private long price;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String brand;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String series;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String model;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String gender;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String style_type;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String sub_class;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String made_label;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String calender;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String feature;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String movement;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String functions;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String engine;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String water_resistant;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String band_color;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String band_type;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String clasp;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String bracelet;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String dial_type;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String dial_color;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String crystal;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String second_makers;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String bezel;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String bezel_material;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String case_back;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String case_dimension;
    @Field(index= Index.YES, analyze= Analyze.YES, store = Store.NO)
    private String case_shape;

    public Watch() {
    }
}