package com.kdb2018.sasa.percent.model.hisseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class FiftyTwoWeek implements Serializable {

    @SerializedName("low")
    @Expose
    private String low;
    @SerializedName("high")
    @Expose
    private String high;
    @SerializedName("low_change")
    @Expose
    private String lowChange;
    @SerializedName("high_change")
    @Expose
    private String highChange;
    @SerializedName("low_change_percent")
    @Expose
    private String lowChangePercent;
    @SerializedName("high_change_percent")
    @Expose
    private String highChangePercent;
    @SerializedName("range")
    @Expose
    private String range;

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLowChange() {
        return lowChange;
    }

    public void setLowChange(String lowChange) {
        this.lowChange = lowChange;
    }

    public String getHighChange() {
        return highChange;
    }

    public void setHighChange(String highChange) {
        this.highChange = highChange;
    }

    public String getLowChangePercent() {
        return lowChangePercent;
    }

    public void setLowChangePercent(String lowChangePercent) {
        this.lowChangePercent = lowChangePercent;
    }

    public String getHighChangePercent() {
        return highChangePercent;
    }

    public void setHighChangePercent(String highChangePercent) {
        this.highChangePercent = highChangePercent;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

}
