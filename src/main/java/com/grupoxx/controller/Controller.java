package com.grupoxx.controller;

import static com.grupoxx.menu.Menu.MenuFasedeConfiguracao;

public abstract class Controller {

    public static void Inicial(int choice) {
        switch(choice) {
            case 1:
                FasedeConfiguracao();
                break;
            case 2:
                break;
            case 3:
                break;

        }
    }

    public static void FasedeConfiguracao() {
        int choice = MenuFasedeConfiguracao();

    }
    public static void MenuSimulacaoManual() {
        // TODO
    }

}
