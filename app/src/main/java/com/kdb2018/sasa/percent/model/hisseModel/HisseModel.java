package com.kdb2018.sasa.percent.model.hisseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kdb2018.sasa.percent.model.hisseModel.FiftyTwoWeek;

import java.io.Serializable;

public class HisseModel implements Serializable {
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("exchange")
    @Expose
    private String exchange;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("open")
    @Expose
    private String open;
    @SerializedName("high")
    @Expose
    private String high;
    @SerializedName("low")
    @Expose
    private String low;
    @SerializedName("close")
    @Expose
    private String close;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("previous_close")
    @Expose
    private String previousClose;
    @SerializedName("change")
    @Expose
    private String change;
    @SerializedName("percent_change")
    @Expose
    private String percentChange;
    @SerializedName("average_volume")
    @Expose
    private String averageVolume;
    @SerializedName("fifty_two_week")
    @Expose
    private FiftyTwoWeek fiftyTwoWeek;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(String previousClose) {
        this.previousClose = previousClose;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(String percentChange) {
        this.percentChange = percentChange;
    }

    public String getAverageVolume() {
        return averageVolume;
    }

    public void setAverageVolume(String averageVolume) {
        this.averageVolume = averageVolume;
    }

    public FiftyTwoWeek getFiftyTwoWeek() {
        return fiftyTwoWeek;
    }

    public void setFiftyTwoWeek(FiftyTwoWeek fiftyTwoWeek) {
        this.fiftyTwoWeek = fiftyTwoWeek;
    }
}
