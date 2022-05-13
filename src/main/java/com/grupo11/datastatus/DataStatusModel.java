package com.grupo11.datastatus;

import com.grupo11.energysupplier.exception.EnergySupplierNotFound;
import com.grupo11.simulation.Invoicer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DataStatusModel {
    /**
     * Lista de faturas das casas
     */
    private List<Invoicer> invoicers;

    /**
     * Contrutor de DataStatusRepository
     *
     * @param fac Lista de faturas das casas
     */
    public DataStatusModel(List<Invoicer> fac) {
        this.setInvoicers(fac);
    }

    /**
     * Metodo de indica qual é a lista de faturas
     *
     * @return lista de faturas
     */
    public List<Invoicer> getInvoicers() {
        return invoicers.stream().map(Invoicer::clone).collect(Collectors.toList());
    }

    /**
     * Metodo de alterar a lista de faturas
     *
     * @param invoicers nova lista de faturas
     */
    public void setInvoicers(List<Invoicer> invoicers) {
        this.invoicers = invoicers.stream().map(Invoicer::clone).collect(Collectors.toList());
    }

    /**
     * Metodo que nos dá o endereço da casa que mais gastou dinheiro
     *
     * @return o endereço da casa que mais gastou
     */
    public String BiggestSpent(){
        Comparator <Invoicer> comparator = (f1,f2)-> (int) (f2.getTotalCost() - f1.getTotalCost());
        List<Invoicer> invoicers = this.getInvoicers();
        invoicers.sort(comparator);
        return invoicers.get(0).getHouseAddress();
    }

    /**
     * Metodo auxiliar que calcula o ganho de uma companhia de eletricidade
     *
     * @param energySupplierName nome da companhia de eletricidade
     *
     * @return o seu lucro total
     */

    private double MostProfitAux(String energySupplierName) {
        return this.invoicers.stream().filter(x->x.getEnergySupplier().equals(energySupplierName)).mapToDouble(Invoicer::getTotalCost).sum();

    }

    /**
     * Metodo que indica a companhia de eletricidade que mais ganhou dinheiro
     *
     * @return a companhia de eletricidade que mais ganhou dinheiro
     */

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

    /**
     * Metodo que lista as faturas de uma determinada companhia de eletricidade
     *
     * @param supplier a companhia de eletricidade a faturar
     *
     * @return uma lista de todas as faturas da companhia
     *
     * @throws EnergySupplierNotFound se a companhia de eletricidade não existir
     */

    public List<Invoicer> invoicerSupplier(String supplier)throws EnergySupplierNotFound {

        List <Invoicer> res = this.getInvoicers()
                .stream()
                .filter(x->x.getEnergySupplier().equals(supplier))
                .collect(Collectors.toList());

        if(res.isEmpty())  throw new EnergySupplierNotFound("O Fornecedor de energia " + supplier+ " não existe");

        return res;
    }

    /**
     * Metodo que nos dá a lista de endereços das casas que gatam mais dinheiro
     *
     * @return a lista de endereços das casas que gatam mais dinheiro
     */
    public List<String> BiggestSpentList() {
        Comparator <Invoicer> comparator = (f1,f2)-> (int) (f2.getTotalCost() - f1.getTotalCost());
        List<Invoicer> invoicers = this.getInvoicers();
        invoicers.sort(comparator);
        return invoicers.stream().map(Invoicer::getHouseAddress).collect(Collectors.toList());
    }
}