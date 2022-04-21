package com.grupoxx.datastatus;

import com.grupoxx.simulation.Invoicer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DataStatus {
    // será que posso usar map ?

    private DataStatus(List<Invoicer> fac){
        this.setFaturas(fac);

    }

    private List<Invoicer> faturas = new ArrayList<>();

    public List<Invoicer> getFaturas() {
        List<Invoicer> fac = new ArrayList<>();

        fac = faturas.stream().map(Invoicer::clone).collect(Collectors.toList());

        return fac;
    }

    public void setFaturas(List<Invoicer> faturas) {
        this.faturas = faturas.stream().map(Invoicer::clone).collect(Collectors.toList());
    }

    public String BiggestSpent(){
        Comparator <Invoicer> comparator = (f1,f2)-> (int) (f2.getTotalCost() - f1.getTotalCost());

        List<Invoicer> invoicers = this.getFaturas();

        invoicers.sort(comparator);

        return invoicers.get(0).getHouseAddress();
    }

    private double MostProfitAux(String energySupplierName){

        return this.faturas.stream().filter(x->x.getEnergySupplier().equals(energySupplierName)).mapToDouble(Invoicer::getTotalCost).sum();

    }

    public String MostProfit() {
        double maxProfit = 0;
        String supplier = "";
        List<String> energySupplierNames = new ArrayList<>();

        energySupplierNames = this.faturas.stream().map(Invoicer::getEnergySupplier).collect(Collectors.toList());

        for (String s : energySupplierNames) {

            double Profit = MostProfitAux(s);

            if (Profit > maxProfit) {
                maxProfit = Profit;
                supplier = s;
            }
        }

        return supplier;
    }

    public List<Invoicer> invoicerSupplier(String supplier){

        return this.getFaturas().stream().filter(x->x.getEnergySupplier().equals(supplier)).collect(Collectors.toList());

    }
}


