package com.grupoxx.datastatus;

import com.grupoxx.energysupplier.exception.EnergySupplierNotFound;
import com.grupoxx.main.MainController;
import com.grupoxx.simulation.Invoicer;

import java.util.List;

public class DataStatusController {

    private MainController mainController;
    private DataStatusRepository estatistica;
    private DataStatusMenu menu;

    public DataStatusController(MainController mainController, List<Invoicer> faturas){
        this.menu = new DataStatusMenu();
        this.estatistica = new DataStatusRepository(faturas);
        this.mainController = mainController;
        dataStatusController();
    }

    private void dataStatusController(){
        switch (menu.MenuEstatistica()) {
            case 1: BiggestSpentController();
            case 2: MostProfitController();
            case 3: invoicerSupplierController();
            case 4: BiggestSpentListController();
            case 5: mainController.mainController();
            case -1: dataStatusController();
        }
    }

    private  void  BiggestSpentController(){
        String res = this.estatistica.BiggestSpent();
        this.menu.MenuResultados(res,1);
        dataStatusController();
    }

    private void MostProfitController(){
        String res = this.estatistica.MostProfit();
        this.menu.MenuResultados(res,2);
        dataStatusController();
    }

    private void invoicerSupplierController(){
        String nome = menu.MenuListaFaturasDoComercializador();

        if(nome.equals("*")) dataStatusController();

        else {
            try{
                List<Invoicer> nomes = this.estatistica.invoicerSupplier(nome);
                this.menu.MenuListagemDasFaturas(nomes);
                dataStatusController();
            }
            catch (EnergySupplierNotFound e){System.out.println(e.getMessage());
                dataStatusController();}
        }
    }

    private void BiggestSpentListController(){

        List<String> casas = this.estatistica.BiggestSpentList();
        this.menu.MenuListagemDosMaioresConsumidores(casas);
        dataStatusController();
    }


}
