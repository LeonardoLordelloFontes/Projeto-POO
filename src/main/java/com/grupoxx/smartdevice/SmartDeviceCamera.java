package com.grupoxx.smartdevice;

public class SmartDeviceCamera extends SmartDevice{

    private int fileSize;
    private int resolution;

    SmartDeviceCamera(){
        super();
        this.fileSize = 0;
        this.resolution = 0;
    }

    SmartDeviceCamera(int fileSize){
        super();
        this.fileSize = fileSize;
        this.resolution = 0;
    }

    SmartDeviceCamera (int fileSize, int resulosion){
        super();
        this.fileSize = fileSize;
        this.resolution = resulosion;
    }

    SmartDeviceCamera(SmartDeviceCamera sc){
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
    public double dailyEnergeticCost() {

        return this.resolution * this.fileSize;
    }

    public boolean equals(Object o) {

        if (this == o) return (true);
        if (o == null || this.getClass() != o.getClass()) return (false);

        SmartDeviceCamera sc = (SmartDeviceCamera) o;

        return super.equals(sc) && this.resolution == sc.getResolution();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n O seu dispositivo é um SmartCamara.")
                .append("\n O tamanho do ficheiro em que guardam os eventos registados é igual a ").append(this.fileSize).append(".")
                .append("\n A sua resuloção é igual a ").append(this.resolution).append(".");

        return sb.toString();
    }

    @Override
    public SmartDevice clone() {

        return new SmartDeviceCamera(this);
    }
}
