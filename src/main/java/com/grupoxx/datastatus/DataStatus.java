package com.grupoxx.datastatus;

import com.grupoxx.simulation.Invoicer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DataStatus {
    private List<Invoicer> invoicers;
    private DataStatus(List<Invoicer> fac) {
        this.setInvoicers(fac);
    }

    public List<Invoicer> getInvoicers() {
        return invoicers.stream().map(Invoicer::clone).collect(Collectors.toList());
    }

    public void setInvoicers(List<Invoicer> invoicers) {
        this.invoicers = invoicers.stream().map(Invoicer::clone).collect(Collectors.toList());
    }

    public String BiggestSpent(){
        Comparator <Invoicer> comparator = (f1,f2)-> (int) (f2.getTotalCost() - f1.getTotalCost());
        List<Invoicer> invoicers = this.getInvoicers();
        invoicers.sort(comparator);
        return invoicers.get(0).getHouseAddress();
    }

    private double MostProfitAux(String energySupplierName){
        return this.invoicers.stream().filter(x->x.getEnergySupplier().equals(energySupplierName)).mapToDouble(Invoicer::getTotalCost).sum();

    }

    public String MostProfit() {
        double maxProfit = 0;
        String supplier = "";
        List<String> energySupplierNames = new ArrayList<>();

        energySupplierNames = this.invoicers.stream().map(Invoicer::getEnergySupplier).collect(Collectors.toList());

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
        return this.getInvoicers().stream().filter(x->x.getEnergySupplier().equals(supplier)).collect(Collectors.toList());

    }

    public List<String> BiggestSpentList() {
        Comparator <Invoicer> comparator = (f1,f2)-> (int) (f2.getTotalCost() - f1.getTotalCost());
        List<Invoicer> invoicers = this.getInvoicers();
        invoicers.sort(comparator);
        return invoicers.stream().map(Invoicer::getHouseAddress).collect(Collectors.toList());
    }


}


