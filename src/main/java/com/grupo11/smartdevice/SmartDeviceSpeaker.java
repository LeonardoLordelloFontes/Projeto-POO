package com.grupo11.smartdevice;

import java.io.Serializable;

public class SmartDeviceSpeaker extends SmartDevice implements Serializable {

    /**
     * Marca da coluna
     */
    private String brand;
    /**
     * Volume da coluna
     */
    private int volume;
    /**
     * Estação de rádio da coluna
     */
    private String radio;
    /**
     * Volume máximo da coluna
     */
    private int volumeMax;

    /**
     * Construtor de SmartDeviceSpeaker
     */
    public SmartDeviceSpeaker(){
        super();
        this.brand = "Sony";
        this.volume = 0;
        this.radio = "Nada";
        this.volumeMax = 10;
    }

    /**
     * Construtor de SmartDeviceSpeaker
     */
    public SmartDeviceSpeaker(String factoryCode) {
        super(factoryCode);
        this.brand = "Sony";
        this.volume = 0;
        this.radio = "Nada";
        this.volumeMax = 10;
    }

    /**
     * Construtor de SmartDeviceSpeaker
     */
    public SmartDeviceSpeaker(String factoryCode,double instalacionCost, double energyConsumption,String brand ,int volumeMax){
        super(factoryCode,instalacionCost,energyConsumption);
        this.brand = brand;
        this.volume = 0;
        this.radio = "Nada";
        this.volumeMax = volumeMax;
    }

    /**
     * Construtor de SmartDeviceSpeaker
     */
    public SmartDeviceSpeaker(String brand, int volume){
        super();
        this.brand = brand;
        this.volume = volume;
        this.radio = "Nada";
        this.volumeMax = 10;
    }

    /**
     * Construtor de SmartDeviceSpeaker
     */
    public SmartDeviceSpeaker(String brand, int volume, String radio){
        super();
        this.brand = brand;
        this.volume = volume;
        this.radio = radio;
        this.volumeMax = 10;
    }

    /**
     * Construtor de SmartDeviceSpeaker
     */
    public SmartDeviceSpeaker(String brand, int volume,int volumeMax,String radio){
        super();
        this.brand = brand;
        this.volume = volume;
        this.radio = radio;
        this.volumeMax = volumeMax;
    }

    /**
     * Construtor de SmartDeviceSpeaker
     */
    public SmartDeviceSpeaker(SmartDeviceSpeaker ss){
        super(ss);
        this.brand = ss.getBrand();
        this.volume = ss.getVolume();
        this.radio = ss.getRadio();
    }

    /**
     * Metodo que indica qual a marqua da coluna
     *
     * @return a marqua da coluna
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * Metodo que muda a marqua da coluna
     *
     * @param brand nova marqua da coluna
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
    /**
     * Metodo que indica qual o volume da coluna
     *
     * @return o volume da coluna
     */
    public int getVolume() {
        return this.volume;
    }

    /**
     * Metodo que muda o volume da coluna
     *
     * @param volume novo volume da coluna
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Metodo que indica qual a estação de rádio da coluna
     *
     * @return a estação de rádio da coluna
     */
    public String getRadio() {
        return this.radio;
    }

    /**
     * Metodo que muda a estação de rádio da coluna
     *
     * @param radio nova estação de rádio da coluna
     */
    public void setRadio(String radio) {
        this.radio = radio;
    }

    /**
     * Metodo que indica qual o volume maximo da coluna
     *
     * @return o volume maximo da coluna da coluna
     */
    public int getVolumeMax() {
        return this.volumeMax;
    }
    /**
     * Metodo que muda o volume maximo da coluna
     *
     * @param volumeMax novo volume maximo da coluna
     */
    public void setVolumeMax(int volumeMax) {
        this.volumeMax = volumeMax;
    }
    /**
     *
     * Método que calcula o consumo energético diário da coluna
     *
     * @return o consumo energético diário da coluna
     */
    @Override
    public double energyConsumptionPerDay() {

        return this.getEnergyConsumption() + (this.volume * 2);
    }

    /**
     * Médoto que nos diz se um objeto é igual a uma coluna
     *
     *
     * @param o um objeto da classe  Object
     * @return true se um objeto é igual a uma coluna e false se não for
     */
    public boolean equals(Object o) {

        if (this == o) return (true);
        if (o == null || this.getClass() != o.getClass()) return (false);

        SmartDeviceSpeaker ss = (SmartDeviceSpeaker) o;

        return super.equals(ss) && this.brand == ss.getBrand();
    }

    /**
     * Metodo que converte um objeto da classe SmartDeviceSpeaker em uma string
     *
     * @return SmartDeviceSpeaker na sua versão string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n\t\tTIPO: SmartSpeaker.")
                .append("\n\t\tMarca: ").append(this.brand)
                .append("\n\t\tVolume Atual: ").append(this.volume)
                .append("\n\t\tVolume Máximo: ").append(this.volumeMax)
                .append("\n\t\tEstação Rádio: ").append(this.radio);

        return sb.toString();
    }

    /**
     * Método que clona colunas
     *
     * @return um clone de uma determinada colunas
     */
    @Override
    public SmartDevice clone() {
        return new SmartDeviceSpeaker(this);
    }


}


