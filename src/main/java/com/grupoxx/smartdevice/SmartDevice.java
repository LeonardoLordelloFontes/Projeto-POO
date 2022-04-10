package com.grupoxx.smartdevice;

public abstract class SmartDevice {

    /* Estado dos smart device */

    public enum State {
        ON,
        OFF,
    }

    private State state;
    private double instalationCost;
    private String factoryCode;
    private double energyConsumption;
    private int day; // o dia em que o usuario alterou pela ultima vez o seu estado

    public SmartDevice(){

        this.state = State.OFF;
        this.instalationCost = 0;
        this.factoryCode = "1234";
        this.energyConsumption = 0;
        this.day = -1;
    }

    public SmartDevice(State state){

        this.state = state;
        this.instalationCost = 0;
        this.factoryCode = "1234";
        this.energyConsumption = 0;
        this.day = state == State.ON? 0:-1;

    }

    public SmartDevice(State state, double instalation_price){

        this.state = state;
        this.instalationCost = instalation_price;
        this.factoryCode = "1234";
        this.energyConsumption = 0;
        this.day = state == State.ON? 0:-1;
    }

    public SmartDevice(State state, double instalation_price, String factory_code){

        this.state = state;
        this.instalationCost = instalation_price;
        this.factoryCode = factory_code;
        this.energyConsumption = 0;
        this.day = state == State.ON? 0:-1;

    }

    public SmartDevice(State state, double instalation_price, String factory_code, double energetic_cost){

        this.state = state;
        this.instalationCost = instalation_price;
        this.factoryCode = factory_code;
        this.energyConsumption = energetic_cost;
        this.day = state == State.ON? 0:-1;

    }

    public SmartDevice(State state, double instalation_price, String factory_code, double energetic_cost, int day){

        this.state = state;
        this.instalationCost = instalation_price;
        this.factoryCode = factory_code;
        this.energyConsumption = energetic_cost;
        this.day = state == State.ON? day:-1;

    }

    public SmartDevice(SmartDevice sd){

        this.state = sd.getState();
        this.instalationCost = sd.getInstalationCost();
        this.factoryCode = sd.getFactoryCode();
        this.energyConsumption = sd.getEnergyConsumption();
        this.day = state == State.ON? sd.getDay():-1;

    }

    public State getState() {

        return this.state;
    }

    public void setState(State state) {

        this.state = state;
    }

    public double getInstalationCost() {

        return this.instalationCost;
    }

    public void setInstalationCost(double instalationCost) {

        this.instalationCost = instalationCost;
    }

    public String getFactoryCode() {

        return this.factoryCode;
    }

    public void setFactoryCode(String factoryCode) {

        this.factoryCode = factoryCode;
    }

    public double getEnergyConsumption() {

        return this.energyConsumption;
    }

    public void setEnergyConsumption(double energyConsumption) {

        this.energyConsumption = energyConsumption;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean equals(Object o) {

        if (this == o) return (true);
        if (o == null || this.getClass() != o.getClass()) return (false);

        SmartDevice sd = (SmartDevice) o;

        return this.factoryCode == sd.getFactoryCode();
    }

    public String toString(){

        StringBuilder sb = new StringBuilder("O seu dispositivo inteligente cujo número de fabrica é ");

        sb.append(this.factoryCode).append(".")
                .append("\n Teve um custo de instalação de ").append(this.instalationCost).append(" euros.")
                .append("\n Neste momento encontra-se ").append( this.state == State.ON ? "ligado.":"desligado.")
                .append("\n Tem um custo dirario fixo de ").append(this.energyConsumption).append("KW por hora.");

        return sb.toString();
    }

    public void smart_device_switch(int acessDay){

        if(this.state == State.ON && acessDay > this.day) {
            this.setState(State.OFF);
        }

        else {
            this.setDay(acessDay);
            this.setState(State.ON);

        }
    }// o acessDay é o dia em que o usuario quer mudar o estado dos aparelhos se for pelo menos um dia a cima do ultimo dia em que lhe foi alterado o estado ele permite a alteração.

    public abstract SmartDevice clone();

    public abstract double dailyEnergeticCost();

    public double perSecondEnergeticCost(){

        return this.dailyEnergeticCost()/86400 ;

    };
}
