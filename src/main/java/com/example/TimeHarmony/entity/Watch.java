package com.example.TimeHarmony.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Watch")
public class Watch {
    @Id
    private String watch_id;
    private String member_id;
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

    public Watch(String watch_id, String member_id, String watch_image, String watch_description, String watch_name,
            Timestamp watch_create_date, Timestamp watch_approval_date, byte state, float price, String brand,
            String series, String model, String gender, String style_type, String sub_class, String made_label,
            String calender, String feature, String movement, String functions, String engine, String water_resistant,
            String band_color, String band_type, String clasp, String bracelet, String dial_type, String dial_color,
            String crystal, String second_makers, String bezel, String bezel_material, String case_back,
            String case_dimension, String case_shape) {
        this.watch_id = watch_id;
        this.member_id = member_id;
        this.watch_image = watch_image;
        this.watch_description = watch_description;
        this.watch_name = watch_name;
        this.watch_create_date = watch_create_date;
        this.watch_approval_date = watch_approval_date;
        this.state = state;
        this.price = price;
        this.brand = brand;
        this.series = series;
        this.model = model;
        this.gender = gender;
        this.style_type = style_type;
        this.sub_class = sub_class;
        this.made_label = made_label;
        this.calender = calender;
        this.feature = feature;
        this.movement = movement;
        this.functions = functions;
        this.engine = engine;
        this.water_resistant = water_resistant;
        this.band_color = band_color;
        this.band_type = band_type;
        this.clasp = clasp;
        this.bracelet = bracelet;
        this.dial_type = dial_type;
        this.dial_color = dial_color;
        this.crystal = crystal;
        this.second_makers = second_makers;
        this.bezel = bezel;
        this.bezel_material = bezel_material;
        this.case_back = case_back;
        this.case_dimension = case_dimension;
        this.case_shape = case_shape;
    }

    public String getWatch_id() {
        return watch_id;
    }

    public void setWatch_id(String watch_id) {
        this.watch_id = watch_id;
    }

    public String getWatch_image() {
        return watch_image;
    }

    public void setWatch_image(String watch_image) {
        this.watch_image = watch_image;
    }

    public String getWatch_description() {
        return watch_description;
    }

    public void setWatch_description(String watch_description) {
        this.watch_description = watch_description;
    }

    public String getWatch_name() {
        return watch_name;
    }

    public void setWatch_name(String watch_name) {
        this.watch_name = watch_name;
    }

    public Timestamp getWatch_create_date() {
        return watch_create_date;
    }

    public void setWatch_create_date(Timestamp watch_create_date) {
        this.watch_create_date = watch_create_date;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStyle_type() {
        return style_type;
    }

    public void setStyle_type(String style_type) {
        this.style_type = style_type;
    }

    public String getSub_class() {
        return sub_class;
    }

    public void setSub_class(String sub_class) {
        this.sub_class = sub_class;
    }

    public String getMade_label() {
        return made_label;
    }

    public void setMade_label(String made_label) {
        this.made_label = made_label;
    }

    public String getCalender() {
        return calender;
    }

    public void setCalender(String calender) {
        this.calender = calender;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getWater_resistant() {
        return water_resistant;
    }

    public void setWater_resistant(String water_resistant) {
        this.water_resistant = water_resistant;
    }

    public String getBand_color() {
        return band_color;
    }

    public void setBand_color(String band_color) {
        this.band_color = band_color;
    }

    public String getBand_type() {
        return band_type;
    }

    public void setBand_type(String band_type) {
        this.band_type = band_type;
    }

    public String getClasp() {
        return clasp;
    }

    public void setClasp(String clasp) {
        this.clasp = clasp;
    }

    public String getBracelet() {
        return bracelet;
    }

    public void setBracelet(String bracelet) {
        this.bracelet = bracelet;
    }

    public String getDial_type() {
        return dial_type;
    }

    public void setDial_type(String dial_type) {
        this.dial_type = dial_type;
    }

    public String getDial_color() {
        return dial_color;
    }

    public void setDial_color(String dial_color) {
        this.dial_color = dial_color;
    }

    public String getCrystal() {
        return crystal;
    }

    public void setCrystal(String crystal) {
        this.crystal = crystal;
    }

    public String getSecond_makers() {
        return second_makers;
    }

    public void setSecond_makers(String second_makers) {
        this.second_makers = second_makers;
    }

    public String getBezel() {
        return bezel;
    }

    public void setBezel(String bezel) {
        this.bezel = bezel;
    }

    public String getBezel_material() {
        return bezel_material;
    }

    public void setBezel_material(String bezel_material) {
        this.bezel_material = bezel_material;
    }

    public String getCase_back() {
        return case_back;
    }

    public void setCase_back(String case_back) {
        this.case_back = case_back;
    }

    public String getCase_dimension() {
        return case_dimension;
    }

    public void setCase_dimension(String case_dimension) {
        this.case_dimension = case_dimension;
    }

    public String getCase_shape() {
        return case_shape;
    }

    public void setCase_shape(String case_shape) {
        this.case_shape = case_shape;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public Timestamp getWatch_approval_date() {
        return watch_approval_date;
    }

    public void setWatch_approval_date(Timestamp watch_approval_date) {
        this.watch_approval_date = watch_approval_date;
    }

}
