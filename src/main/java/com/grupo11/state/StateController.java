package com.grupo11.state;

import com.grupo11.main.MainModel;
import com.grupo11.main.MainController;

import java.io.IOException;

public class StateController {
    private MainModel community;
    private StateView menu;

    public StateController(MainModel community) {
        this.community = community;
        this.menu = new StateView();
    }

    public void runStateController() {
        stateController();
    }

    private void stateController() {
        switch (menu.stateMenu()) {
            case -1 -> stateController();
            case 1 -> saveStateController();
            case 2 -> loadStateController();
            case 3 -> {
                MainController mainController = new MainController(community);
                mainController.runMainController();
            }
        }
    }

    private void saveStateController() {
        String filePath = menu.saveStateMenu();
        if (filePath == null) stateController();
        else {
            StateModel state = new StateModel(community);
            try {
                state.saveState(filePath);
                stateController();
            } catch (IOException e) {
                System.out.println("Não foi possível salvar, verifique que o caminho que inseriu é válido!");
                saveStateController();
            }
        }
    }

    private void loadStateController() {
        String filePath = menu.loadStateMenu();
        if (filePath == null) stateController();
        else {
            StateModel state = new StateModel();
            try {
                MainController mainController = new MainController(state.loadState(filePath));
                mainController.runMainController();
            } catch (IOException e) {
                System.out.println("Não foi possível salvar, verifique que o caminho que inseriu é válido!");
                loadStateController();
            } catch (ClassNotFoundException e) {
                System.out.println("A classe não foi encontrada");
                MainController mainController = new MainController(community);
                mainController.runMainController();
            }
        }
    }
}
