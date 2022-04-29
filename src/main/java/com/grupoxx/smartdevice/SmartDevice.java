package com.grupoxx.smartdevice;

import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public abstract class SmartDevice implements Serializable {

    /**
     * Propriedades comuns a todos o SmartDevice

     /**
     * O seu estado ligado ou desligado.
     */
    public enum State {
        ON,
        OFF,
    }

    private State state;
    /**
     * O custo de instalação.
     */
    private double installationCost;
    /**
     * O seu código de fabrica único.
     */
    private String factoryCode;
    /**
     * O fator energético comum defenido pela fábrica
     */
    private double energyConsumption;
    /**
     * Finalmente o dia em que o usuario alterou pela ultima vez o seu estado
     */
    private LocalDateTime lastStateChange;

    /**
     * Diversos construtores de SmartDevice
     */
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

    /**
     * @return estado de um dispositivo
     */
    public State getState() {
        return this.state;
    }

    /**
     * @param state de um SmartDevice
     */
    public void setState(State state) {
        this.state = state;
    }
    /**
     * @return o custo de instalação de um dispositivo
     */
    public double getInstallationCost() {
        return this.installationCost;
    }

    /**
     * @param installationCost de um dispositivo
     */
    public void setInstallationCost(double installationCost) {
        this.installationCost = installationCost;
    }
    /**
     * @return o código de fábrica de um dispositivo
     */
    public String getFactoryCode() {
        return this.factoryCode;
    }

    /**
     * @param factoryCode de um dispositivo
     */
    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }
    /**
     * @return o fator energético comum de um dispositivo
     */
    public double getEnergyConsumption() {
        return this.energyConsumption;
    }

    /**
     * @param energyConsumption de um dispositivo
     */
    public void setEnergyConsumption(double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }
    /**
     * @return o ultimo dia em que o estado de um dispositivo foi alterado
     */
    public LocalDateTime getLastStateChange() {
        return lastStateChange;
    }

    /**
     * @param lastStateChange de um dispositivo
     */
    public void setLastStateChange(LocalDateTime lastStateChange) {
        this.lastStateChange = lastStateChange;
    }

    /**
     * @return se dois dispositivos são iguais
     */
    public boolean equals(Object o) {

        if (this == o) return (true);
        if (o == null || this.getClass() != o.getClass()) return (false);

        SmartDevice sd = (SmartDevice) o;

        return this.factoryCode.equals(sd.getFactoryCode());
    }

    /**
     *
     * @return a versão de uma dispositivo em string
     */
    public String toString(){

        StringBuilder sb = new StringBuilder("Código de Fábrica: ");

        sb.append(this.factoryCode)
                .append("\n\t\tCusto de Instalação: ").append(this.installationCost)
                .append("\n\t\tEstado: ").append( this.state == State.ON ? "Ligado":"Desligado")
                .append("\n\t\tCusto de Energia: ").append(this.energyConsumption);

        return sb.toString();
    }

    /**
     * Metodo que apenas mudo o estado de um dispositivo se tiver passado pelo menos um dia
     */
    public void switchConnection(LocalDateTime acessDay, State state) {
        long daysBetween = 1;
        if (lastStateChange != null)
            daysBetween = DAYS.between(this.lastStateChange, acessDay);
        if(Math.abs(daysBetween) >= 1) {
            this.setState(state);
            this.setLastStateChange(acessDay);
        }
    }

    /**
     * Contrato onde alguma das suas subclasses não absestrata tem de seguir
     * @return um clone de um dispositivo
     */
    public abstract SmartDevice clone();

    /**
     *  Contrato onde alguma das suas subclasses não absestrata tem de seguir
     * @return a energia gasta por dia por um dispositivo especifico depois de lhe ser aplicada a formula de gasto diário
     */
    public abstract double energyConsumptionPerDay();
}
