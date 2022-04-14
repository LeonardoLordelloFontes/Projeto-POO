package com.grupoxx.smartdevice;


import com.grupoxx.menu.Menu;

import static com.grupoxx.menu.Menu.*;

public class SmartDeviceController {
    private SmartDeviceRepository repository;

    public SmartDeviceController(SmartDeviceRepository repository){
        this.repository = repository;
        SmartDiviceOperationChoice();

    }

    public void SmartDiviceOperationChoice(){
        System.out.println(this.repository.getFactory());
        int choice = Menu.MenuTipoDispositivoOperacoes();

        switch (choice){

            case 1: SmartDeviceAddChoice();

            case 2: SmartDeviceRemoveChoice();

            case 3: SmartDeviceUpdateChoice();

            case 4: System.out.println("acabou");
        }
    }

    public void SmartDeviceAddChoice(){

        int deviceChoice = Menu.MenuTipoDispositivoAdd();
        switch (deviceChoice){

            case 1: SmartDeviceBulbChoice();

            case 2: SmartDeviceSpeakerChoice();

            case 3: SmartDeviceCameraChoice();

            case 4: SmartDiviceOperationChoice();
        }
    }

    public void SmartDeviceBulbChoice(){
        String[] components = MenuSmartBulbAdd();
        if ( components.equals(null) ) SmartDeviceAddChoice();
        this.repository.SmartDeviceBulbAdd(
                        components[0],
                        Double.parseDouble(components[1]),
                        Double.parseDouble(components[2]),
                        Double.parseDouble(components[3]));

        SmartDeviceBulbChoice();

    }

    public void SmartDeviceSpeakerChoice(){
        String[] components = MenuSmartSpeakerAdd();
        if ( components.equals(null) ) SmartDeviceAddChoice();
        this.repository.SmartDeviceSpeakerAdd(
                        components[0],
                        Double.parseDouble(components[1]),
                        Double.parseDouble(components[2]),
                        components[3],
                        Integer.parseInt(components[4]));
        SmartDeviceSpeakerChoice();
    }

    public void SmartDeviceCameraChoice(){

        String[] components = MenuSmartCamaraAdd();
        if ( components.equals(null) ) SmartDeviceAddChoice();

        this.repository.SmartDeviceCameraAdd(
                        components[0],
                        Double.parseDouble(components[1]),
                        Double.parseDouble(components[2]),
                        Integer.parseInt(components[3]),
                        Integer.parseInt(components[4]));
        SmartDeviceCameraChoice();

    }

    public void SmartDeviceRemoveChoice(){
        String component = MenuTipoDispositivoRemove();
        if (component.equals(null)) SmartDiviceOperationChoice();

        this.repository.SmartDeviceRemove(component);

        SmartDeviceRemoveChoice();
    }
    public void SmartDeviceUpdateChoice(){}

}
