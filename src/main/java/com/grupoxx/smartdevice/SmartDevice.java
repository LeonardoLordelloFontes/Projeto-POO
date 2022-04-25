package com.grupoxx.smartdevice;

import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public abstract class SmartDevice implements Serializable {

    /* Estado dos smart device */

    public enum State {
        ON,
        OFF,
    }

    private State state;
    private double installationCost;
    private String factoryCode;
    private double energyConsumption;
    private LocalDateTime lastStateChange; // o dia em que o usuario alterou pela ultima vez o seu estado

    public SmartDevice(){

        this.state = State.OFF;
        this.installationCost = 0;
        this.factoryCode = "1234";
        this.energyConsumption = 0;
        this.lastStateChange = null;
    }

    public SmartDevice(String factoryCode) {
        this.state = State.OFF;
        this.installationCost = 0;
        this.factoryCode = factoryCode;
        this.energyConsumption = 0;
        this.lastStateChange = null;
    }

    public SmartDevice(String factoryCode, double installationCost,double energyConsumption) {
        this.state = State.OFF;
        this.installationCost = installationCost;
        this.factoryCode = factoryCode;
        this.energyConsumption = energyConsumption;
        this.lastStateChange = null;
    }

    public SmartDevice(State state){

        this.state = state;
        this.installationCost = 0;
        this.factoryCode = "1234";
        this.energyConsumption = 0;
        this.lastStateChange = null;

    }

    public SmartDevice(State state, double instalation_price){

        this.state = state;
        this.installationCost = instalation_price;
        this.factoryCode = "1234";
        this.energyConsumption = 0;
        this.lastStateChange = null;
    }

    public SmartDevice(State state, double instalation_price, String factory_code){

        this.state = state;
        this.installationCost = instalation_price;
        this.factoryCode = factory_code;
        this.energyConsumption = 0;
        this.lastStateChange = null;

    }

    public SmartDevice(State state, double instalation_price, String factory_code, double energetic_cost){

        this.state = state;
        this.installationCost = instalation_price;
        this.factoryCode = factory_code;
        this.energyConsumption = energetic_cost;
        this.lastStateChange = null;

    }

    public SmartDevice(State state, double instalation_price, String factory_code, double energetic_cost, LocalDateTime day){

        this.state = state;
        this.installationCost = instalation_price;
        this.factoryCode = factory_code;
        this.energyConsumption = energetic_cost;
        this.lastStateChange = day;

    }

    public SmartDevice(SmartDevice sd){

        this.state = sd.getState();
        this.installationCost = sd.getInstallationCost();
        this.factoryCode = sd.getFactoryCode();
        this.energyConsumption = sd.getEnergyConsumption();
        this.lastStateChange = sd.getLastStateChange();

    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public double getInstallationCost() {
        return this.installationCost;
    }

    public void setInstallationCost(double installationCost) {
        this.installationCost = installationCost;
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

    public LocalDateTime getLastStateChange() {
        return lastStateChange;
    }

    public void setLastStateChange(LocalDateTime lastStateChange) {
        this.lastStateChange = lastStateChange;
    }

    public boolean equals(Object o) {

        if (this == o) return (true);
        if (o == null || this.getClass() != o.getClass()) return (false);

        SmartDevice sd = (SmartDevice) o;

        return this.factoryCode.equals(sd.getFactoryCode());
    }

    public String toString(){

        StringBuilder sb = new StringBuilder("Código de Fábrica: ");

        sb.append(this.factoryCode)
                .append("\n\t\tCusto de Instalação: ").append(this.installationCost)
                .append("\n\t\tEstado: ").append( this.state == State.ON ? "Ligado":"Desligado")
                .append("\n\t\tCusto de Energia: ").append(this.energyConsumption);

        return sb.toString();
    }

    public void switchConnection(LocalDateTime acessDay, State state) {
        long daysBetween = 1;
        if (lastStateChange != null)
            daysBetween = DAYS.between(this.lastStateChange, acessDay);
        if(Math.abs(daysBetween) >= 1) {
            this.setState(state);
            this.setLastStateChange(acessDay);
        }
    }// o acessDay é o dia em que o usuario quer mudar o estado dos aparelhos se for pelo menos um dia a cima do ultimo dia em que lhe foi alterado o estado ele permite a alteração.

    public abstract SmartDevice clone();

    public abstract double energyConsumptionPerDay();
}
