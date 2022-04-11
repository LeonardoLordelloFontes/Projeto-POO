package com.grupoxx.Estatistica;

import com.grupoxx.EnergySupplier.EnergySupplier;
import com.grupoxx.smarthouse.SmartHouse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Estatistica {
    // será que posso usar map ?

    private List<SmartHouse> house;
    private Set<EnergySupplier> supplier;

    // lista e não set ordenado por causa dos comparadores utilizados.

    public  Estatistica (Estatistica e){

        this.setHouse(e.getHouse());

    }

    public void setSupplier(Set<EnergySupplier> supplier){
        this.supplier = supplier.stream().map(EnergySupplier::clone).collect(Collectors.toSet());
    }

    public Set<EnergySupplier> getSupplier() {
        return this.supplier.stream().map(EnergySupplier::clone).collect(Collectors.toSet());
    }

    public List<SmartHouse> getHouse() {

        return this.house.stream().map(SmartHouse::clone).collect(Collectors.toList());
    }

    public void setHouse(List<SmartHouse> house){
        this.house = house.stream().map(SmartHouse::clone).collect(Collectors.toList());
    }

    public boolean equals(Object o) {

        if (this == o) return (true);
        if (o == null || this.getClass() != o.getClass()) return (false);

        Estatistica e = (Estatistica) o;

        return this.house.equals(e.getHouse()) && this.supplier.equals(e.supplier);

    }

    public SmartHouse MaiorConsumo(){
        Comparator <SmartHouse> comparator = (sh1,sh2)-> (int) (sh2.ElectricityMeter() - sh1.ElectricityMeter());

        List<SmartHouse> sh = new ArrayList<SmartHouse>(this.house);

        sh.sort(comparator);

        return sh.get(0);
    }

    public List<SmartHouse> ListaConsumidores(){

        Comparator <SmartHouse> comparator = (sh1,sh2)-> (int) (sh1.ElectricityMeter() - sh2.ElectricityMeter());

        List<SmartHouse> sh = new ArrayList<SmartHouse>(this.house);

        sh.sort(comparator);

        return sh;
    }



}
