package com.example.TimeHarmony.builder.interfacepack;

import java.sql.Timestamp;
import java.util.List;

import com.example.TimeHarmony.entity.Sellers;
import com.example.TimeHarmony.entity.Watch;

public interface IWatchBuilder {
    public IWatchBuilder setWatchId(String id);

    public IWatchBuilder setWatchImage(List<String> image);

    public IWatchBuilder setOwner(Sellers seller);

    public IWatchBuilder setWatchDescription(String wdescription);

    public IWatchBuilder setWatchName(String wname);

    public IWatchBuilder setWatchCreateDate(Timestamp wcreatedate);

    public IWatchBuilder setWatchApprovalDate(Timestamp wapprovaldate);

    public IWatchBuilder setState(byte state);

    public IWatchBuilder setPrice(long price);

    public IWatchBuilder setBrand(String brand);

    public IWatchBuilder setSeries(String series);

    public IWatchBuilder setModel(String model);

    public IWatchBuilder setGender(String gender);

    public IWatchBuilder setStyleType(String styletype);

    public IWatchBuilder setSubClass(String subclass);

    public IWatchBuilder setMadeLabel(String madelabel);

    public IWatchBuilder setCalender(String calender);

    public IWatchBuilder setFeature(String feature);

    public IWatchBuilder setMovement(String movement);

    public IWatchBuilder setFunctions(String functions);

    public IWatchBuilder setEngine(String engine);

    public IWatchBuilder setWaterResistant(String waterresistant);

    public IWatchBuilder setBandColor(String bandcolor);

    public IWatchBuilder setBandType(String bandtype);

    public IWatchBuilder setClasp(String clasp);

    public IWatchBuilder setBracelet(String bracelet);

    public IWatchBuilder setDialType(String dialtype);

    public IWatchBuilder setDialColor(String dialcolor);

    public IWatchBuilder setCrystal(String crystal);

    public IWatchBuilder setSecondMakers(String secondmakers);

    public IWatchBuilder setBezel(String bezel);

    public IWatchBuilder setBezelMaterial(String bezelmaterial);

    public IWatchBuilder setCaseBack(String caseback);

    public IWatchBuilder setCaseDimension(String casedimension);

    public IWatchBuilder setCaseShape(String caseshape);

    public Watch build();
}
