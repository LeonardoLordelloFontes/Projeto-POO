package com.grupoxx.smartdevice;

public abstract class SmartDevice {

    public enum State {
        ON,
        OFF,
    }

    private State state;
    private double instalationPrice;
    private String factoryCode;
    private double energeticCost;
    private int day;

    public SmartDevice(){

        this.state = State.OFF;
        this.instalationPrice = 0;
        this.factoryCode = "1234";
        this.energeticCost = 0;

    }

    public SmartDevice(State state){

        this.state = state;
        this.instalationPrice = 0;
        this.factoryCode = "1234";
        this.energeticCost = 0;

    }

    public SmartDevice(State state, double instalation_price){

        this.state = state;
        this.instalationPrice = instalation_price;
        this.factoryCode = "1234";
        this.energeticCost = 0;

    }

    public SmartDevice(State state, double instalation_price, String factory_code){

        this.state = state;
        this.instalationPrice = instalation_price;
        this.factoryCode = factory_code;
        this.energeticCost = 0;

    }

    public SmartDevice(State state, double instalation_price, String factory_code, double energetic_cost){

        this.state = state;
        this.instalationPrice = instalation_price;
        this.factoryCode = factory_code;
        this.energeticCost = energetic_cost;

    }

    public SmartDevice(SmartDevice sd){

        this.state = sd.getState();
        this.instalationPrice = sd.getInstalationPrice();
        this.factoryCode = sd.getFactoryCode();
        this.energeticCost = sd.getEnergeticCost();

    }

    public State getState() {

        return this.state;
    }

    public void setState(State state) {

        this.state = state;
    }

    public double getInstalationPrice() {

        return this.instalationPrice;
    }

    public void setInstalationPrice(double instalationPrice) {

        this.instalationPrice = instalationPrice;
    }

    public String getFactoryCode() {

        return this.factoryCode;
    }

    public void setFactoryCode(String factoryCode) {

        this.factoryCode = factoryCode;
    }

    public double getEnergeticCost() {

        return this.energeticCost;
    }

    public void setEnergeticCost(double energeticCost) {

        this.energeticCost = energeticCost;
    }

    public boolean equals(Object o) {

        if (this == o) return (true);
        if (o == null || this.getClass() != o.getClass()) return (false);

        SmartDevice sd = (SmartDevice) o;

        return this.factoryCode == sd.getFactoryCode();
    }

    public String toString(){

        StringBuffer sb = new StringBuffer("O seu dispositivo inteligente cujo número de fabrica é ");

        sb.append(this.factoryCode).append(" teve um custo de instalação de ").append(this.instalationPrice)
                .append(" encontra-se ").append( this.state == State.ON ? "ligado":"desligado")
                .append(" e tem um custo dirario fixo de ").append(this.energeticCost);

        return sb.toString();
    }

    public void smart_device_switch(SmartDevice sd){

        if(sd.getState() == State.ON) sd.setState(State.OFF);

        else {
            sd.setState(State.ON);

        }
    }

    public abstract SmartDevice clone();

    public abstract double daily_energetic_cost();

}
