package com.grupoxx.smartdevice;

public class SmartDeviceSpeaker extends SmartDevice{

    private String brand;
    private int volume;
    private String radio;

    SmartDeviceSpeaker(){
        super();
        this.brand = "Sony";
        this.volume = 0;
        this.radio = "Nada";
    }

    SmartDeviceSpeaker(String brand){
        super();
        this.brand = brand;
        this.volume = 0;
        this.radio = "Nada";
    }

    SmartDeviceSpeaker(String brand, int volume){
        super();
        this.brand = brand;
        this.volume = volume;
        this.radio = "Nada";
    }

    SmartDeviceSpeaker(String brand, int volume, String radio){
        super();
        this.brand = brand;
        this.volume = volume;
        this.radio = radio;
    }

    SmartDeviceSpeaker(SmartDeviceSpeaker ss){
        super(ss);
        this.brand = ss.getBrand();
        this.volume = ss.getVolume();
        this.radio = ss.getRadio();
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getRadio() {
        return this.radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    @Override
    public double EnergeticConsumptionPerDay() {

        return this.getEnergyConsumption() + (this.volume * 2);
    }

    public boolean equals(Object o) {

        if (this == o) return (true);
        if (o == null || this.getClass() != o.getClass()) return (false);

        SmartDeviceSpeaker ss = (SmartDeviceSpeaker) o;

        return super.equals(ss) && this.brand == ss.getBrand();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n O seu dispositivo é um SmartSpeaker.")
                .append("\n A sua marca é ").append(this.brand).append(".")
                .append("\n Neste momento o seu volume é ").append(this.volume).append(".")
                .append("\n A rádio online a ser ouvida neste momento é a ").append(this.radio).append(".");

        return sb.toString();
    }

    @Override
    public SmartDevice clone() {
        return new SmartDeviceSpeaker(this);
    }
}


