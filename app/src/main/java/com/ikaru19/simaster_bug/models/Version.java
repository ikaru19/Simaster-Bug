package com.ikaru19.simaster_bug.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Version {
    @SerializedName("version")
    @Expose
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
