package com.ikaru19.simaster_bug.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VideoResponse implements Serializable, Parcelable
{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    public final static Creator<VideoResponse> CREATOR = new Creator<VideoResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VideoResponse createFromParcel(android.os.Parcel in) {
            return new VideoResponse(in);
        }

        public VideoResponse[] newArray(int size) {
            return (new VideoResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7692785386876772674L;

    protected VideoResponse(android.os.Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.judul = ((String) in.readValue((String.class.getClassLoader())));
        this.uri = ((String) in.readValue((String.class.getClassLoader())));
        this.thumbnail = ((String) in.readValue((String.class.getClassLoader())));
        this.dateCreated = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VideoResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(judul);
        dest.writeValue(uri);
        dest.writeValue(thumbnail);
        dest.writeValue(dateCreated);
    }

    public int describeContents() {
        return 0;
    }

}
