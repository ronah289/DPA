
package com.example.giantbombapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;


@SuppressWarnings("ALL")
@Parcel
public class Result {

    @SerializedName("api_detail_url")
    @Expose
    private String apiDetailUrl;
    @SerializedName("deck")
    @Expose
    private String deck;
    @SerializedName("embed_player")
    @Expose
    private String embedPlayer;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("length_seconds")
    @Expose
    private Integer lengthSeconds;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("premium")
    @Expose
    private Boolean premium;
    @SerializedName("publish_date")
    @Expose
    private String publishDate;
    @SerializedName("site_detail_url")
    @Expose
    private String siteDetailUrl;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("video_type")
    @Expose
    private String videoType;
    @SerializedName("video_categories")
    @Expose
    private List<VideoCategory> videoCategories = null;
    @SerializedName("low_url")
    @Expose
    private String lowUrl;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     *
     * @param embedPlayer
     * @param image
     * @param lowUrl
     * @param videoType
     * @param deck
     * @param publishDate
     * @param lengthSeconds
     * @param url
     * @param apiDetailUrl
     * @param premium
     * @param videoCategories
     * @param name
     * @param guid
     * @param id
     * @param siteDetailUrl
     * @param user
     */
    public Result(String apiDetailUrl, String deck, String embedPlayer, String guid, Integer id, Integer lengthSeconds, String name, Boolean premium, String publishDate, String siteDetailUrl, Image image, String user, String videoType, List<VideoCategory> videoCategories, String lowUrl, String url) {
        super();
        this.apiDetailUrl = apiDetailUrl;
        this.deck = deck;
        this.embedPlayer = embedPlayer;
        this.guid = guid;
        this.id = id;
        this.lengthSeconds = lengthSeconds;
        this.name = name;
        this.premium = premium;
        this.publishDate = publishDate;
        this.siteDetailUrl = siteDetailUrl;
        this.image = image;
        this.user = user;
        this.videoType = videoType;
        this.videoCategories = videoCategories;
        this.lowUrl = lowUrl;
        this.url = url;
    }

    public String getApiDetailUrl() {
        return apiDetailUrl;
    }

    public void setApiDetailUrl(String apiDetailUrl) {
        this.apiDetailUrl = apiDetailUrl;
    }


    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public String getEmbedPlayer() {
        return embedPlayer;
    }

    public void setEmbedPlayer(String embedPlayer) {
        this.embedPlayer = embedPlayer;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLengthSeconds() {
        return lengthSeconds;
    }

    public void setLengthSeconds(Integer lengthSeconds) {
        this.lengthSeconds = lengthSeconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getSiteDetailUrl() {
        return siteDetailUrl;
    }

    public void setSiteDetailUrl(String siteDetailUrl) {
        this.siteDetailUrl = siteDetailUrl;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }



    public List<VideoCategory> getVideoCategories() {
        return videoCategories;
    }

    public void setVideoCategories(List<VideoCategory> videoCategories) {
        this.videoCategories = videoCategories;
    }



    public String getLowUrl() {
        return lowUrl;
    }

    public void setLowUrl(String lowUrl) {
        this.lowUrl = lowUrl;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
