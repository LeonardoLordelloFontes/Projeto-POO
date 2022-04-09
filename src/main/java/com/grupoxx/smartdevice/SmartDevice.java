package com.grupoxx.smartdevice;

public abstract class SmartDevice {

    public enum State {
        ON,
        OFF,
    }

    private State state;
    private double instalation_price;
    private String factory_code;
    private double energetic_cost;

    public SmartDevice(){

        this.state = State.OFF;
        this.instalation_price = 0;
        this.factory_code = "1234";
        this.energetic_cost = 0;

    }

    public SmartDevice(State state){

        this.state = state;
        this.instalation_price = 0;
        this.factory_code = "1234";
        this.energetic_cost = 0;

    }

    public SmartDevice(State state, double instalation_price){

        this.state = state;
        this.instalation_price = instalation_price;
        this.factory_code = "1234";
        this.energetic_cost = 0;

    }

    public SmartDevice(State state, double instalation_price, String factory_code){

        this.state = state;
        this.instalation_price = instalation_price;
        this.factory_code = factory_code;
        this.energetic_cost = 0;

    }

    public SmartDevice(State state, double instalation_price, String factory_code, double energetic_cost){

        this.state = state;
        this.instalation_price = instalation_price;
        this.factory_code = factory_code;
        this.energetic_cost = energetic_cost;

    }

    public SmartDevice(SmartDevice sd){

        this.state = sd.getState();
        this.instalation_price = sd.getInstalation_price();
        this.factory_code = sd.getFactory_code();
        this.energetic_cost = sd.getEnergetic_cost();

    }

    public State getState() {

        return this.state;
    }

    public void setState(State state) {

        this.state = state;
    }

    public double getInstalation_price() {

        return this.instalation_price;
    }

    public void setInstalation_price(double instalation_price) {

        this.instalation_price = instalation_price;
    }

    public String getFactory_code() {

        return this.factory_code;
    }

    public void setFactory_code(String factory_code) {

        this.factory_code = factory_code;
    }

    public double getEnergetic_cost() {

        return this.energetic_cost;
    }

    public void setEnergetic_cost(double energetic_cost) {

        this.energetic_cost = energetic_cost;
    }

    public boolean equals(Object o) {

        if (this == o) return (true);
        if (o == null || this.getClass() != o.getClass()) return (false);

        SmartDevice sd = (SmartDevice) o;

        return this.factory_code == sd.getFactory_code();
    }

    public String toString(){

        StringBuffer sb = new StringBuffer("O seu dispositivo inteligente cujo número de fabrica é ");

        sb.append(this.factory_code).append(" teve um custo de instalação de ").append(this.instalation_price)
                .append(" encontra-se ").append( this.state == State.ON ? "ligado":"desligado")
                .append(" e tem um custo dirario fixo de ").append(this.energetic_cost);

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
