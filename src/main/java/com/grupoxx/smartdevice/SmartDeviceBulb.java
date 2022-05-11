package com.grupoxx.smartdevice;

import java.io.Serializable;

public class SmartDeviceBulb extends SmartDevice implements Serializable {
    /**
     * Tonalidades da lâmpada
     */
    public enum Tone {
        Neutral,
        Warm,
        Cold,
    }
    /**
     * Tonalidade da lâmpada
     */
    private Tone tone;
    /**
     * Dimenção da lâmpada
     */
    private double dimension;

    /**
     * Construtor de SmartDeviceBulb
     */
    public SmartDeviceBulb() {
        super();
        this.tone = Tone.Neutral;
        this.dimension = 2;
    }
    /**
     * Construtor de SmartDeviceBulb
     */
    public SmartDeviceBulb(String factoryCode) {
        super(factoryCode);
        this.tone = Tone.Neutral;
        this.dimension = 2;
    }
    /**
     * Construtor de SmartDeviceBulb
     */
    public SmartDeviceBulb(String factoryCode, double dimension, double instalacionCost, double energyConsumption){
        super(factoryCode,instalacionCost,energyConsumption);
        this.tone = Tone.Neutral;
        this.dimension = dimension;
    }
    /**
     * Construtor de SmartDeviceBulb
     */
    public SmartDeviceBulb(Tone tone) {
        super();
        this.tone = tone;
        this.dimension = 2;
    }
    /**
     * Construtor de SmartDeviceBulb
     */
    public SmartDeviceBulb(Tone tone, double dimension) {
        super();
        this.tone = tone;
        this.dimension = dimension;
    }
    /**
     * Construtor de SmartDeviceBulb
     */
    public SmartDeviceBulb(Tone tone, double dimension, State state) {
        super(state);
        this.tone = tone;
        this.dimension = dimension;
    }
    /**
     * Construtor de SmartDeviceBulb
     */
    public SmartDeviceBulb(Tone tone, double dimension, State state, double instalation_price) {
        super(state, instalation_price);
        this.tone = tone;
        this.dimension = dimension;
    }
    /**
     * Construtor de SmartDeviceBulb
     */
    public SmartDeviceBulb(Tone tone, double dimension, State state, double instalation_price, String factory_code) {
        super(state, instalation_price, factory_code);
        this.tone = tone;
        this.dimension = dimension;
    }
    /**
     * Construtor de SmartDeviceBulb
     */
    public SmartDeviceBulb(Tone tone, double dimension, State state, double instalation_price, String factory_code, double energetic_cost) {
        super(state, instalation_price, factory_code, energetic_cost);
        this.tone = tone;
        this.dimension = dimension;
    }
    /**
     * Construtor de SmartDeviceBulb
     */
    public SmartDeviceBulb(SmartDeviceBulb sb) {
        super(sb);
        this.tone = sb.getTone();
        this.dimension = sb.dimension;
    }
    /**
     * Construtor de SmartDeviceBulb
     */
    public SmartDeviceBulb(SmartDevice sd, Tone tone, double dimension) {
        super(sd);
        this.tone = tone;
        this.dimension = dimension;
    }

    /**
     * Metodo que indica qual é a tonalidade atual da lâmpada
     *
     * @return a tonalidade do lampâda
     */
    public Tone getTone() {
        return this.tone;
    }

    /**
     * Metodo que muda a tonalidade atual da lâmpada
     *
     * @param tone é a nova tonalidade da lâmpada
     */
    public void setTone(Tone tone) {
        this.tone = tone;
    }
    /**
     * Metodo que indica qual é a dimenção da lâmpada
     *
     * @return a dimençaõ do lampâda
     */
    public double getDimension() {
        return this.dimension;
    }

    /**
     * Metodo que muda a dimenção da lâmpada
     *
     *   @param dimension é a dimenção do lampâda
     */
    public void setDimension(double dimension) {
        this.dimension = dimension;
    }

    /**
     *
     * Método que calcula o consumo energético diário da lampâda
     *
     * @return o consumo energético diário da lampâda
     */
    @Override
    public double energyConsumptionPerDay() {

        double val = -1;

        switch (this.tone) {
            case Neutral -> val = 25;
            case Cold -> val = 10;
            case Warm -> val = 50;
        }

        return this.getEnergyConsumption() + val;
    }

    /**
     * Médoto que nos diz se um objeto é igual a uma lâmpada
     *
     *
     * @param o um objeto da classe  Object
     * @return true se um objeto é igual a uma lâmpada e false se não for
     */
    public boolean equals(Object o) {

        if (this == o) return (true);
        if (o == null || this.getClass() != o.getClass()) return (false);

        SmartDeviceBulb sb = (SmartDeviceBulb) o;

        return super.equals(sb) && this.dimension == sb.getDimension();
    }

    /**
     * Metodo que converte um objeto da classe SmartDeviceBulb em uma string
     *
     * @return SmartDeviceBulb na sua versão string
     */
    public String toString(){
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n\t\tTIPO: SmartBulb")
                .append("\n\t\tTonalidade: ").append(this.tone)
                .append("\n\t\tA sua dimenção é ").append(this.dimension);

        return sb.toString();
    }

    /**
     * Método que clona lâmpadas
     *
     * @return um clone de uma determinada lâmpada
     */
    @Override
    public SmartDevice clone() {

        return new SmartDeviceBulb(this);
    }

}

