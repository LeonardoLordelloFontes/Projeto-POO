package com.grupo11.datastatus;
import com.grupo11.energysupplier.exception.EnergySupplierNotFound;
import com.grupo11.main.MainController;
import com.grupo11.main.MainModel;
import com.grupo11.simulation.Invoicer;

import java.util.List;
import java.util.stream.Collectors;

public class DataStatusController {

    private MainModel community;
    private DataStatusModel dataStatus;
    private DataStatusView menu;

    /**
     *
     * Construtor de data status
     *
     * @param community é o model dos model
     * @param faturas lista de faturas
     */
    public DataStatusController(MainModel community, List<Invoicer> faturas){
        this.menu = new DataStatusView();
        this.dataStatus = new DataStatusModel(faturas);
        this.community = community;
    }

    /**
     * Metodo que executa o controlador
     */
    public void runDataStatusController() {
        dataStatusController();
    }

    /**
     * Controlador do menu principal do DataStatus
     */
    private void dataStatusController(){
        switch (menu.menuDataStatus()) {
            case 1 -> BiggestSpentController();
            case 2 -> MostProfitController();
            case 3 -> invoicerSupplierController();
            case 4 -> BiggestSpentListController();
            case 5 -> {
                MainController mainController = new MainController(community);
                mainController.runMainController();
            }
            case -1-> dataStatusController();
        }
    }


    /**
     * Controlador que chama o metodo que nos dá a casa que mais gastou
     */
    private void BiggestSpentController(){
        String res = this.dataStatus.BiggestSpent();
        this.menu.menuResults(res,1);
        dataStatusController();
    }

    /**
     * Controlador que chama o metodo que nos dá a companhia de eletricidade que mais lucrou
     */
    private void MostProfitController(){
        String res = this.dataStatus.MostProfit();
        this.menu.menuResults(res,2);
        dataStatusController();
    }

    /**
     * Controlador que chama o metodo, que nos dá a lista de faturas de uma companhia de eletricidade especifica
     */
    private void invoicerSupplierController() {
        menu.listAllEnergySuppliers(this.dataStatus.getInvoicers().stream().map(Invoicer::getEnergySupplier).collect(Collectors.toSet()));
        String nome = menu.menuListSupplierInvoicers();

        if(nome.equals("*")) dataStatusController();

        else {
            try{
                List<Invoicer> nomes = this.dataStatus.invoicerSupplier(nome);
                this.menu.menuListAllInvoicers(nomes);
                dataStatusController();
            }
            catch (EnergySupplierNotFound e){System.out.println(e.getMessage());
                dataStatusController();}
        }
    }

    /**
     * Controlador que chama o metodo que nos dá uma lista das casas que gatam mais dinheiro ordenadas por ordem decrescesnte
     */
    private void BiggestSpentListController() {
        List<String> casas = this.dataStatus.BiggestSpentList();
        this.menu.menuListLargestConsumers(casas);
        dataStatusController();
    }
}