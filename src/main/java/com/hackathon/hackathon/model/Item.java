package com.hackathon.hackathon.model;

public class Item {
    private String name;
    private double initialPrice;
    private double highestOffer; // Inicializar la oferta máxima en 0.0
    private Bidder currentBidder; 
    private String type;


    public Item(String name, double initialPrice, Bidder currentBidder, String type) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.highestOffer = 0.0;
        this.currentBidder = currentBidder;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public double getHighestOffer() {
        return highestOffer;
    }

    public void setHighestOffer(double highestOffer) {
        this.highestOffer = highestOffer;
    }

    public Bidder getCurrentBidder() {
        return currentBidder;
    }

    public void setCurrentBidder(Bidder currentBidder) {
        this.currentBidder = currentBidder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
