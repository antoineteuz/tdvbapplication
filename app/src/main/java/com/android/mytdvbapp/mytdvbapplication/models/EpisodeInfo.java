package com.android.mytdvbapp.mytdvbapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by antoinepelletier on 17/12/2017.
 */

public class EpisodeInfo {

    @SerializedName("absoluteNumber")
    @Expose
    private int absoluteNumber;
    @SerializedName("airedEpisodeNumber")
    @Expose
    private int airedEpisodeNumber;
    @SerializedName("airedSeason")
    @Expose
    private int airedSeason;
    @SerializedName("airedSeasonID")
    @Expose
    private int airedSeasonID;
    @SerializedName("dvdEpisodeNumber")
    @Expose
    private int dvdEpisodeNumber;
    @SerializedName("dvdSeason")
    @Expose
    private int dvdSeason;
    @SerializedName("episodeName")
    @Expose
    private String episodeName;
    @SerializedName("firstAired")
    @Expose
    private String firstAired;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("language")
    @Expose
    private Language language;
    @SerializedName("lastUpdated")
    @Expose
    private int lastUpdated;
    @SerializedName("overview")
    @Expose
    private String overview;

    public int getAbsoluteNumber() {
        return absoluteNumber;
    }

    public void setAbsoluteNumber(int absoluteNumber) {
        this.absoluteNumber = absoluteNumber;
    }

    public int getAiredEpisodeNumber() {
        return airedEpisodeNumber;
    }

    public void setAiredEpisodeNumber(int airedEpisodeNumber) {
        this.airedEpisodeNumber = airedEpisodeNumber;
    }

    public int getAiredSeason() {
        return airedSeason;
    }

    public void setAiredSeason(int airedSeason) {
        this.airedSeason = airedSeason;
    }

    public int getAiredSeasonID() {
        return airedSeasonID;
    }

    public void setAiredSeasonID(int airedSeasonID) {
        this.airedSeasonID = airedSeasonID;
    }

    public int getDvdEpisodeNumber() {
        return dvdEpisodeNumber;
    }

    public void setDvdEpisodeNumber(int dvdEpisodeNumber) {
        this.dvdEpisodeNumber = dvdEpisodeNumber;
    }

    public int getDvdSeason() {
        return dvdSeason;
    }

    public void setDvdSeason(int dvdSeason) {
        this.dvdSeason = dvdSeason;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(int lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
