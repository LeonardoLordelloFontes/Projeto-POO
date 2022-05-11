package com.grupo11.datastatus;

import com.grupo11.community.Community;
import com.grupo11.energysupplier.exception.EnergySupplierNotFound;
import com.grupo11.main.MainController;
import com.grupo11.simulation.Invoicer;

import java.util.List;

public class DataStatusController {

    private Community community;
    private DataStatusRepository dataStatus;
    private DataStatusMenu menu;

    public DataStatusController(Community community, List<Invoicer> faturas){
        this.menu = new DataStatusMenu();
        this.dataStatus = new DataStatusRepository(faturas);
        this.community = community;
        dataStatusController();
    }

    private void dataStatusController(){
        switch (menu.MenuEstatistica()) {
            case 1 -> BiggestSpentController();
            case 2 -> MostProfitController();
            case 3 -> invoicerSupplierController();
            case 4 -> BiggestSpentListController();
            case 5 ->{ MainController mainController = new MainController(community); mainController.runMainController();}
            case -1-> dataStatusController();
        }
    }

    private  void  BiggestSpentController(){
        String res = this.dataStatus.BiggestSpent();
        this.menu.MenuResultados(res,1);
        dataStatusController();
    }

    private void MostProfitController(){
        String res = this.dataStatus.MostProfit();
        this.menu.MenuResultados(res,2);
        dataStatusController();
    }

    private void invoicerSupplierController(){
        String nome = menu.MenuListaFaturasDoComercializador();

        if(nome.equals("*")) dataStatusController();

        else {
            try{
                List<Invoicer> nomes = this.dataStatus.invoicerSupplier(nome);
                this.menu.MenuListagemDasFaturas(nomes);
                dataStatusController();
            }
            catch (EnergySupplierNotFound e){System.out.println(e.getMessage());
                dataStatusController();}
        }
    }

    private void BiggestSpentListController(){

        List<String> casas = this.dataStatus.BiggestSpentList();
        this.menu.MenuListagemDosMaioresConsumidores(casas);
        dataStatusController();
    }


}
