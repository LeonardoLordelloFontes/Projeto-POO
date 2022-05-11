package com.grupo11.state;

import com.grupo11.main.MainController;

public class StateController {
    private MainController mainController;
    private StateMenu menu;

    /*

    public StateController(MainController mainController) {
        this.mainController = mainController;
        this.menu = new StateMenu();
        stateController();
    }

    private void stateController() {
        switch (menu.stateMenu()) {
            case -1 -> stateController();
            case 1 -> saveStateController();
            case 2 -> loadStateController();
            case 3 -> this.mainController.mainController();
        }
    }

    private void saveStateController() {
        String filePath = menu.saveStateMenu();
        if (filePath == null) stateController();
        else {
            StateRepository state = new StateRepository(mainController);
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
            StateRepository state = new StateRepository();
            try {
                new MainController(state.loadState(filePath));
            } catch (IOException e) {
                System.out.println("Não foi possível salvar, verifique que o caminho que inseriu é válido!");
                loadStateController();
            } catch (ClassNotFoundException e) {
                System.out.println("A classe não foi encontrada");
                mainController.mainController();
            }
        }
    }*/
}
