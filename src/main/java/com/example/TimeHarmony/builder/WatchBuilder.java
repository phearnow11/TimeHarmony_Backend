package com.example.TimeHarmony.builder;

import java.security.Timestamp;

import com.example.TimeHarmony.entity.Watch;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class WatchBuilder implements  IWatchBuilder{
    private String watch_id ;
    private String watch_image ; 
    private String watch_description; 
    private String watch_name; 

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp watch_create_date; 
    private byte state; 
    private float price ; 
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
    
    @Override
    public IWatchBuilder setWatchId(String id) {
        this.watch_id = id ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setWatchImage(String image) {
        this.watch_image = image ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setWatchDescription(String wdescription) {
        this.watch_description = wdescription;
        return this ; 
    }
    @Override
    public IWatchBuilder setWatchName(String wname) {
        this.watch_name = wname ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setWatchCreateDate(Timestamp wcreatedate) {
        this.watch_create_date = wcreatedate; 
        return this ; 
    }
    @Override
    public IWatchBuilder setState(byte state) {
        this.state = state ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setPrice(float price) {
        this.price = price ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setBrand(String brand) {
        this.brand = brand ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setSeries(String series) {
        this.series = series; 
        return this ; 
    }
    @Override
    public IWatchBuilder setModel(String model) {
        this.model = model ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setGender(String gender) {
        this.gender = gender ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setStyleType(String styletype) {
        this.style_type = styletype ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setSubClass(String subclass) {
        this.sub_class = subclass ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setMadeLabel(String madelabel) {
        this.made_label = madelabel; 
        return this ; 
    }
    @Override
    public IWatchBuilder setCalender(String calender) {
        this.calender = calender ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setFeature(String feature) {
        this.feature = feature ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setMovement(String movement) {
        this.movement = movement ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setFunctions(String functions) {
        this.functions = functions ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setEngine(String engine) {
        this.engine = engine ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setWaterResistant(String waterresistant) {
        this.water_resistant = waterresistant ; 
        return this ;    
    }
    @Override
    public IWatchBuilder setBandColor(String bandcolor) {
        this.band_color = bandcolor ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setBandType(String bandtype) {
        this.band_type = bandtype ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setClasp(String clasp) {
        this.clasp = clasp ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setBracelet(String bracelet) {
        this.bracelet = bracelet; 
        return this ; 
    }
    @Override
    public IWatchBuilder setDialType(String dialtype) {
        this.dial_type = dialtype ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setDialColor(String dialcolor) {
        this.dial_color = dialcolor ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setCrystal(String crystal) {
        this.crystal = crystal ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setSecondMakers(String secondmakers) {
        this.second_makers = secondmakers ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setBezel(String bezel) {
        this.bezel = bezel ; 
        return this ; 
    }
    @Override
    public IWatchBuilder setBezelMaterial(String bezelmaterial) {
        this.bezel_material = bezelmaterial; 
        return this ; 
    }
    @Override
    public IWatchBuilder setCaseBack(String caseback) {
        this.case_back = caseback; 
        return this ; 
    }
    @Override
    public IWatchBuilder setCaseDimension(String casedimension) {
        this.case_dimension = casedimension; 
        return this ; 
    }
    @Override
    public IWatchBuilder setCaseShape(String caseshape) {
        this.case_shape= caseshape; 
        return this ; 
    }

    @Override
    public Watch build() {
        return new Watch(watch_id, watch_image, watch_description, watch_name, watch_create_date, state, price, brand,
         series, model, gender, style_type, sub_class, made_label, calender, feature, movement, functions, engine, water_resistant, 
         band_color, band_type, clasp, bracelet, dial_type, dial_color, crystal, second_makers, bezel, bezel_material, case_back, case_dimension, case_shape);

    }

    
    

}
