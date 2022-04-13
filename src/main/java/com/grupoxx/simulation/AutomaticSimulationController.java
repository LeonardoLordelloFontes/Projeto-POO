package com.grupoxx.simulation;

import com.grupoxx.EnergySupplier.EnergySupplierRepository;
import com.grupoxx.smartdevice.SmartDeviceRepository;
import com.grupoxx.smarthouse.SmartHouseRepository;

import static com.grupoxx.menu.Menu.menuFasedeConfiguracao;

public class AutomaticSimulationController {
    private SmartHouseRepository smartHouseRepository;
    private SmartDeviceRepository smartDeviceRepository;
    private EnergySupplierRepository energySupplierRepository;
    public AutomaticSimulationController(int choice) {
        this.energySupplierRepository = new EnergySupplierRepository();
        this.smartDeviceRepository = new SmartDeviceRepository();
        this.smartHouseRepository = new SmartHouseRepository();
        mainSimulation(choice);
    }

    public static void mainSimulation(int choice) {
        int resultado = menuFasedeConfiguracao();
        switch(resultado) {

        }
    }

    public static void manualSimulation() {

    }

    public static void automaticSimulation() {

    }
}
