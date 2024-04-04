package com.hackathon.hackathon.model;

/**
 * Para el desarrollo de la prueba:
 * - Se debe incluir la clase Art como hija de la clase Item
 * - Se debe incluir la implementación del constructor public Art(String name, double initialPrice, Bidder currentBidder, String type, String artist, int yearCreated)
 * - Se deben incluir los métodos getter y setter de la clase Art
 */
public class Art extends Item{

    private String artist;
    private int yearCreated;

    public Art(String name, double initialPrice, Bidder currentBidder, String type, String artist, int yearCreated) {
        super(name, initialPrice, currentBidder, type);
        this.artist = artist;
        this.yearCreated = yearCreated;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }
}
