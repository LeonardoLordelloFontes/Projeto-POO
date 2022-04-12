package com.grupoxx.Estatistica;

import com.grupoxx.EnergySupplier.EnergySupplier;
import com.grupoxx.smarthouse.SmartHouse;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Estatistica {
    // será que posso usar map ?

    private List<SmartHouse> house;

    public  Estatistica (Estatistica e){

        this.setHouse(e.getHouse());

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

        return this.house.equals(e.getHouse());

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

    public String MakeFatura(int DaysToPay,SmartHouse house) throws ScriptException {

        StringBuilder sb = new StringBuilder();
        sb
                .append("Nif : ").append(house.getOwner().getNif())
                .append("\n Contribuinte: ").append(house.getOwner().getNome())
                .append("\n Morada: ").append(house.getAddress())
                .append("\n Fornecedor de eletricidade: ").append(.getName())
                .append("\n Quantidade de energia gasta: ").append(this.ElectricityMeter() * DaysToPay)
                .append("\n Valor a pagar: ").append( this.supplier.totalCostCal(formula));

        return sb.toString();
    }
}
