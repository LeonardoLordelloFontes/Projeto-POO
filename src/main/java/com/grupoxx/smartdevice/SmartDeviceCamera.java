package com.grupoxx.smartdevice;

import java.io.Serializable;

public class SmartDeviceCamera extends SmartDevice implements Serializable {

    /**
     * Tamanho do ficheiro
     */
    private int fileSize;
    /**
     * Resolução da camara
     */
    private int resolution;
    /**
     * Construtor de SmartDeviceCamera
     */
    public SmartDeviceCamera(){
        super();
        this.fileSize = 0;
        this.resolution = 0;
    }
    /**
     * Construtor de SmartDeviceCamera
     */
    public SmartDeviceCamera (String factoryCode,double instalacionCost, double energyConsumption,int resulosion,int fileSize){
        super(factoryCode,instalacionCost,energyConsumption);
        this.fileSize = fileSize;
        this.resolution = resulosion;

    }
    /**
     * Construtor de SmartDeviceCamera
     */
    public SmartDeviceCamera(String factoryCode){
        super(factoryCode);
        this.fileSize = 0;
        this.resolution = 0;
    }
    /**
     * Construtor de SmartDeviceCamera
     */
    public SmartDeviceCamera(int fileSize){
        super();
        this.fileSize = fileSize;
        this.resolution = 0;
    }
    /**
     * Construtor de SmartDeviceCamera
     */
    public SmartDeviceCamera (int fileSize, int resulosion){
        super();
        this.fileSize = fileSize;
        this.resolution = resulosion;
    }
    /**
     * Construtor de SmartDeviceCamera
     */
    public SmartDeviceCamera(SmartDeviceCamera sc){
        super(sc);
        this.resolution = sc.getResolution();
        this.fileSize = sc.getFileSize();
    }

    /**
     * Metodo indica qual é a resolução de uma camara
     *
     * @return a resolução de uma camara
     */
    public int getResolution() {

        return this.resolution;
    }

    /**
     * Metodo que muda a resolução de uma camara
     *
     * @param resolution que é a nova resolução da camara
     */
    public void setResolution(int resolution) {

        this.resolution = resolution;
    }

    /**
     * Metodo indica qual é o tamanho do ficheiro de uma camara
     *
     * @return o tamanho do ficheiro de uma camara
     */
    public int getFileSize() {

        return this.fileSize;
    }

    /**
     * Metodo muda o tamanho do ficheiro de uma camara
     *
     * @param fileSize que é o novo tamanho do ficheiro da camara
     */
    public void setFileSize(int fileSize) {

        this.fileSize = fileSize;
    }
    /**
     *
     * Método que calcula o consumo energético diário da camara
     *
     * @return o consumo energético diário da camara
     */
    @Override
    public double energyConsumptionPerDay() {

        return this.getEnergyConsumption() + (this.resolution * this.fileSize);
    }

    /**
     * Médoto que nos diz se um objeto é igual a uma camara
     *
     *
     * @param o um objeto da classe  Object
     * @return true se um objeto é igual a uma camara e false se não for
     */
    public boolean equals(Object o) {

        if (this == o) return (true);
        if (o == null || this.getClass() != o.getClass()) return (false);

        SmartDeviceCamera sc = (SmartDeviceCamera) o;

        return super.equals(sc) && this.resolution == sc.getResolution();
    }
    /**
     * Metodo que converte um objeto da classe SmartDeviceCamera em uma string
     *
     * @return SmartDeviceCamera na sua versão string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n\t\tTIPO: SmartCamara.")
                .append("\n\t\tTamanho do Ficheiro: ").append(this.fileSize)
                .append("\n\t\tResolução: ").append(this.resolution);

        return sb.toString();
    }
    /**
     * Método que clona camaras
     *
     * @return um clone de uma determinada camara
     */
    @Override
    public SmartDevice clone() {

        return new SmartDeviceCamera(this);
    }
}
