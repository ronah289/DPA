
package com.example.giantbombapi.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;


@SuppressWarnings("ALL")
@Parcel
public class VideoCategory {

    @SerializedName("api_detail_url")
    @Expose
    private String apiDetailUrl;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("site_detail_url")
    @Expose
    private String siteDetailUrl;

    /**
     * No args constructor for use in serialization
     * 
     */
    public VideoCategory() {
    }

    /**
     * 
     * @param apiDetailUrl
     * @param name
     * @param id
     * @param siteDetailUrl
     */
    public VideoCategory(String apiDetailUrl, Integer id, String name, String siteDetailUrl) {
        super();
        this.apiDetailUrl = apiDetailUrl;
        this.id = id;
        this.name = name;
        this.siteDetailUrl = siteDetailUrl;
    }

    public String getApiDetailUrl() {
        return apiDetailUrl;
    }

    public void setApiDetailUrl(String apiDetailUrl) {
        this.apiDetailUrl = apiDetailUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiteDetailUrl() {
        return siteDetailUrl;
    }

    public void setSiteDetailUrl(String siteDetailUrl) {
        this.siteDetailUrl = siteDetailUrl;
    }

}
