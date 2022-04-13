package com.grupoxx.simulation;

import com.grupoxx.EnergySupplier.EnergySupplierController;
import com.grupoxx.EnergySupplier.EnergySupplierRepository;
import com.grupoxx.menu.Menu;
import com.grupoxx.smartdevice.SmartDeviceRepository;
import com.grupoxx.smarthouse.SmartHouseRepository;
public class ManualSimulationController {
    private SmartHouseRepository smartHouseRepository;
    private SmartDeviceRepository smartDeviceRepository;
    private EnergySupplierRepository energySupplierRepository;

    public ManualSimulationController() {
        this.energySupplierRepository = new EnergySupplierRepository();
        this.smartDeviceRepository = new SmartDeviceRepository();
        this.smartHouseRepository = new SmartHouseRepository();
        manualSimulation();
    }

    public void manualSimulation() {
        int choice = Menu.menuFasedeConfiguracao();
        System.out.println(choice);
        switch(choice) {
            case 1:
                new EnergySupplierController(energySupplierRepository);

        }
    }


}