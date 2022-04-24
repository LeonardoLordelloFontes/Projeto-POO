package com.grupoxx.datastatus;

import com.grupoxx.energysupplier.exception.EnergySupplierNotFound;
import com.grupoxx.simulation.Invoicer;

import java.util.List;

public class DataStatusControlar {

    private DataStatusRepository estatistica;
    private DataStatusMenu menu;

    public DataStatusControlar(List<Invoicer> faturas){
        this.menu = new DataStatusMenu();
        this.estatistica = new DataStatusRepository(faturas);

    }

    private void DataStatusMasterController(){
        switch (menu.MenuEstatistica()) {
            case 1: BiggestSpentController();
            case 2: MostProfitController();
            case 3: invoicerSupplierController();
            case 4: BiggestSpentListController();
            case 5: System.out.println("Não sei...");
            case -1: DataStatusMasterController();
        }
    }

    private  void  BiggestSpentController(){
        String res = this.estatistica.BiggestSpent();
        this.menu.MenuResultados(res,1);
        DataStatusMasterController();
    }

    private  void  MostProfitController(){
        String res = this.estatistica.MostProfit();
        this.menu.MenuResultados(res,2);
        DataStatusMasterController();
    }

    private void invoicerSupplierController(){
        String nome = menu.MenuListaFaturasDoComercializador();

        if(nome.equals("*")) DataStatusMasterController();

        else {
            try{
                List<Invoicer> nomes = this.estatistica.invoicerSupplier(nome);
                this.menu.MenuListagemDasFaturas(nomes);
                DataStatusMasterController();
            }
            catch (EnergySupplierNotFound e){System.out.println(e.getMessage());DataStatusMasterController();}
        }
    }

    private void BiggestSpentListController(){

        List<String> casas = this.estatistica.BiggestSpentList();
        this.menu.MenuListagemDosMaioresConsumidores(casas);
        DataStatusMasterController();
    }


}
