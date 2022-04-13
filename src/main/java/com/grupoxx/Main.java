package com.grupoxx;

import com.grupoxx.simulation.AutomaticSimulationController;
import com.grupoxx.simulation.ManualSimulationController;

import static com.grupoxx.menu.Menu.menuInicial;

public class Main {
    public static void main(String[] args) {

        int resultado = menuInicial();
        switch (resultado) {
            case 1:
                new ManualSimulationController();

        }
    }
}

