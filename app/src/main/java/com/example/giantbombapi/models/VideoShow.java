
package com.example.giantbombapi.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;


@SuppressWarnings("ALL")
@Parcel
public class VideoShow {

    @SerializedName("api_detail_url")
    @Expose
    private String apiDetailUrl;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("site_detail_url")
    @Expose
    private String siteDetailUrl;
    @SerializedName("image")
    @Expose
    private com.example.giantbombapi.models.Image__1 image;
    @SerializedName("logo")
    @Expose
    private com.example.giantbombapi.models.Logo logo;

    /**
     * No args constructor for use in serialization
     * 
     */
    public VideoShow() {
    }

    /**
     * 
     * @param apiDetailUrl
     * @param image
     * @param logo
     * @param id
     * @param position
     * @param title
     * @param siteDetailUrl
     */
    public VideoShow(String apiDetailUrl, Integer id, String title, Integer position, String siteDetailUrl, com.example.giantbombapi.models.Image__1 image, com.example.giantbombapi.models.Logo logo) {
        super();
        this.apiDetailUrl = apiDetailUrl;
        this.id = id;
        this.title = title;
        this.position = position;
        this.siteDetailUrl = siteDetailUrl;
        this.image = image;
        this.logo = logo;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getSiteDetailUrl() {
        return siteDetailUrl;
    }

    public void setSiteDetailUrl(String siteDetailUrl) {
        this.siteDetailUrl = siteDetailUrl;
    }

    public com.example.giantbombapi.models.Image__1 getImage() {
        return image;
    }

    public void setImage(com.example.giantbombapi.models.Image__1 image) {
        this.image = image;
    }

    public com.example.giantbombapi.models.Logo getLogo() {
        return logo;
    }

    public void setLogo(com.example.giantbombapi.models.Logo logo) {
        this.logo = logo;
    }

}
