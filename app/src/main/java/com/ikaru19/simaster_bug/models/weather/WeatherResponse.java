package com.ikaru19.simaster_bug.models.weather;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse implements Parcelable
{

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("timezone_offset")
    @Expose
    private Integer timezoneOffset;
    @SerializedName("current")
    @Expose
    private Current current;
    public final static Creator<WeatherResponse> CREATOR = new Creator<WeatherResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public WeatherResponse createFromParcel(android.os.Parcel in) {
            return new WeatherResponse(in);
        }

        public WeatherResponse[] newArray(int size) {
            return (new WeatherResponse[size]);
        }

    }
            ;

    protected WeatherResponse(android.os.Parcel in) {
        this.lat = ((Double) in.readValue((Double.class.getClassLoader())));
        this.lon = ((Double) in.readValue((Double.class.getClassLoader())));
        this.timezone = ((String) in.readValue((String.class.getClassLoader())));
        this.timezoneOffset = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.current = ((Current) in.readValue((Current.class.getClassLoader())));
    }

    public WeatherResponse() {
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(Integer timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(lat);
        dest.writeValue(lon);
        dest.writeValue(timezone);
        dest.writeValue(timezoneOffset);
        dest.writeValue(current);
    }

    public int describeContents() {
        return 0;
    }

}
