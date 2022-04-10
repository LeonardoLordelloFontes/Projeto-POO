package com.grupoxx.smartdevice;

public class SmartDeviceBulb extends SmartDevice {

    public enum Tone {
        Neutral,
        Warm,
        Cold,
    }

    private Tone tone;
    private double dimension;

    SmartDeviceBulb() {
        super();
        this.tone = Tone.Neutral;
        this.dimension = 2;
    }

    SmartDeviceBulb(Tone tone) {
        super();
        this.tone = tone;
        this.dimension = 2;
    }

    SmartDeviceBulb(Tone tone, double dimension) {
        super();
        this.tone = tone;
        this.dimension = dimension;
    }

    SmartDeviceBulb(Tone tone, double dimension, State state) {
        super(state);
        this.tone = tone;
        this.dimension = dimension;
    }

    SmartDeviceBulb(Tone tone, double dimension, State state, double instalation_price) {
        super(state, instalation_price);
        this.tone = tone;
        this.dimension = dimension;
    }

    SmartDeviceBulb(Tone tone, double dimension, State state, double instalation_price, String factory_code) {
        super(state, instalation_price, factory_code);
        this.tone = tone;
        this.dimension = dimension;
    }

    SmartDeviceBulb(Tone tone, double dimension, State state, double instalation_price, String factory_code, double energetic_cost) {
        super(state, instalation_price, factory_code, energetic_cost);
        this.tone = tone;
        this.dimension = dimension;
    }

    SmartDeviceBulb(SmartDeviceBulb sb) {
        super(sb);
        this.tone = sb.getTone();
        this.dimension = sb.dimension;
    }

    SmartDeviceBulb(SmartDevice sd, Tone tone, double dimension) {
        super(sd);
        this.tone = tone;
        this.dimension = dimension;
    }

    public Tone getTone() {
        return this.tone;
    }

    public void setTone(Tone tone) {
        this.tone = tone;
    }

    public double getDimension() {
        return this.dimension;
    }

    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    @Override
    public double daily_energetic_cost() {

        double val = 25;
        if (this.tone == Tone.Warm) val = 50;
        if (this.tone == Tone.Cold) val = 10;

        return this.getEnergeticCost() + val;
    }

    public boolean equals(Object o) {

        if (this == o) return (true);
        if (o == null || this.getClass() != o.getClass()) return (false);

        SmartDeviceBulb sb = (SmartDeviceBulb) o;

        return super.equals(sb) && this.dimension == sb.getDimension();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n O seu dispositivo é um SmartBulb.")
                .append("\n Neste a sua tonalidade é ").append(this.tone).append(".")
                .append("\n A sua dimenção é ").append(this.dimension).append(" cm.");

        return sb.toString();
    }

    @Override
    public SmartDevice clone() {

        return new SmartDeviceBulb(this);
    }

}

