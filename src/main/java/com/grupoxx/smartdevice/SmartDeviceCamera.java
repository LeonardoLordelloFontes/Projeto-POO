package com.grupoxx.smartdevice;

public class SmartDeviceCamera extends SmartDevice{

    private double fileSize;
    private double resulosion;

    SmartDeviceCamera(){
        super();
        this.fileSize = 0;
        this.resulosion = 0;
    }

    SmartDeviceCamera(double fileSize){
        super();
        this.fileSize = fileSize;
        this.resulosion = 0;
    }

    SmartDeviceCamera (double fileSize, double resulosion){
        super();
        this.fileSize = fileSize;
        this.resulosion = resulosion;
    }

    SmartDeviceCamera(SmartDeviceCamera sc){
        super(sc);
        this.resulosion = sc.getResulosion();
        this.fileSize = sc.getFileSize();
    }

    public double getResulosion() {

        return this.resulosion;
    }

    public void setResulosion(double resulosion) {

        this.resulosion = resulosion;
    }

    public double getFileSize() {

        return this.fileSize;
    }

    public void setFileSize(double fileSize) {

        this.fileSize = fileSize;
    }

    @Override
    public double daily_energetic_cost() {

        return this.resulosion * this.fileSize;
    }

    public boolean equals(Object o) {

        if (this == o) return (true);
        if (o == null || this.getClass() != o.getClass()) return (false);

        SmartDeviceCamera sc = (SmartDeviceCamera) o;

        return super.equals(sc) && this.resulosion == sc.getResulosion();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());

        sb.append("\n O seu dispositivo é um SmartCamara.")
                .append("\n O tamanho do ficheiro em que guardam os eventos registados é igual a ").append(this.fileSize).append(".")
                .append("\n A sua resuloção é igual a ").append(this.resulosion).append(".");

        return sb.toString();
    }

    @Override
    public SmartDevice clone() {

        return new SmartDeviceCamera(this);
    }
}
