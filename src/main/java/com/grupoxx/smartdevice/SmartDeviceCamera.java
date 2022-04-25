package com.grupoxx.smartdevice;

import java.io.Serializable;

public class SmartDeviceCamera extends SmartDevice implements Serializable {

    private int fileSize;
    private int resolution;

    public SmartDeviceCamera(){
        super();
        this.fileSize = 0;
        this.resolution = 0;
    }

    public SmartDeviceCamera (String factoryCode,double instalacionCost, double energyConsumption,int resulosion,int fileSize){
        super(factoryCode,instalacionCost,energyConsumption);
        this.fileSize = fileSize;
        this.resolution = resulosion;

    }


    public SmartDeviceCamera(String factoryCode){
        super(factoryCode);
        this.fileSize = 0;
        this.resolution = 0;
    }

    public SmartDeviceCamera(int fileSize){
        super();
        this.fileSize = fileSize;
        this.resolution = 0;
    }

    public SmartDeviceCamera (int fileSize, int resulosion){
        super();
        this.fileSize = fileSize;
        this.resolution = resulosion;
    }

    public SmartDeviceCamera(SmartDeviceCamera sc){
        super(sc);
        this.resolution = sc.getResolution();
        this.fileSize = sc.getFileSize();
    }

    public int getResolution() {

        return this.resolution;
    }

    public void setResolution(int resolution) {

        this.resolution = resolution;
    }

    public int getFileSize() {

        return this.fileSize;
    }

    public void setFileSize(int fileSize) {

        this.fileSize = fileSize;
    }

    @Override
    public double energyConsumptionPerDay() {

        return this.getEnergyConsumption() + (this.resolution * this.fileSize);
    }

    public boolean equals(Object o) {

        if (this == o) return (true);
        if (o == null || this.getClass() != o.getClass()) return (false);

        SmartDeviceCamera sc = (SmartDeviceCamera) o;

        return super.equals(sc) && this.resolution == sc.getResolution();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n\t\tTIPO: SmartCamara.")
                .append("\n\t\tTamanho do Ficheiro: ").append(this.fileSize)
                .append("\n\t\tResolução: ").append(this.resolution);

        return sb.toString();
    }

    @Override
    public SmartDevice clone() {

        return new SmartDeviceCamera(this);
    }
}
