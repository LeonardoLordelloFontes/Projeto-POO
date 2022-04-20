package com.grupoxx.factory;

import com.grupoxx.main.MainController;
import com.grupoxx.smartdevice.*;
import com.grupoxx.smartdevice.exception.DeviceNotFound;


public class FactoryController {
    private SmartDeviceRepository repository;
    private MainController mainController;
    private final FactoryMenu menu;

    public FactoryController(MainController mainController){
        this.mainController = mainController;
        this.repository = mainController.getFactory().getSmartDeviceRepository();
        this.menu = new FactoryMenu();
        SmartDiviceOperationChoice();
    }

    private void SmartDiviceOperationChoice(){

        int choice = menu.MenuTipoDispositivoOperacoes();

        switch (choice){

            case 1: SmartDeviceAddChoice();

            case 2: SmartDeviceRemoveChoice();

            case 3: SmartDeviceUpdateChoice();

            case 4: SmartDeviceListagemChoice();

            case 5: this.mainController.mainController();

            case -1: SmartDiviceOperationChoice();
        }
    }

    private void SmartDeviceAddChoice(){

        int deviceChoice = menu.MenuTipoDispositivoAdd();
        switch (deviceChoice){

            case 1: SmartDeviceBulbChoice();

            case 2: SmartDeviceSpeakerChoice();

            case 3: SmartDeviceCameraChoice();

            case 4: SmartDiviceOperationChoice();

            case -1: SmartDeviceAddChoice();
        }

    }

    private void SmartDeviceBulbChoice(){
        String[] components = menu.MenuSmartBulbAdd();
        if (components == null) SmartDeviceAddChoice();
        else {
            if( components.equals(this.menu.getError()) ) SmartDeviceSpeakerChoice();
            else{
                this.repository.SmartDeviceBulbAdd(
                        components[0],
                        Double.parseDouble(components[1]),
                        Double.parseDouble(components[2]),
                        Double.parseDouble(components[3]));

                this.mainController.getFactory().setDeviceAvailability(components[0], true);
                SmartDeviceBulbChoice();
            }
        }
    }

    private void SmartDeviceSpeakerChoice() {
        String[] components = menu.MenuSmartSpeakerAdd();
        if (components == null) SmartDeviceAddChoice();
        else{
            if( components.equals(this.menu.getError()) ) SmartDeviceSpeakerChoice();

            else {
                this.repository.SmartDeviceSpeakerAdd(
                        components[0],
                        Double.parseDouble(components[1]),
                        Double.parseDouble(components[2]),
                        components[3],
                        Integer.parseInt(components[4]));


                this.mainController.getFactory().setDeviceAvailability(components[0], true);
                SmartDeviceSpeakerChoice();
            }
        }
    }

    private void SmartDeviceCameraChoice(){

        String[] components = menu.MenuSmartCamaraAdd();
        if ( components == null ) SmartDeviceAddChoice();
        else {
            if ( components.equals(this.menu.getError()) ) SmartDeviceCameraChoice();

            else{
                this.repository.SmartDeviceCameraAdd(
                        components[0],
                        Double.parseDouble(components[1]),
                        Double.parseDouble(components[2]),
                        Integer.parseInt(components[3]),
                        Integer.parseInt(components[4]));

                this.mainController.getFactory().setDeviceAvailability(components[0], true);
                SmartDeviceCameraChoice();
                }
        }
    }

    private void SmartDeviceRemoveChoice() {

        String component = menu.MenuTipoDispositivoRemove();
        if (component == null) SmartDiviceOperationChoice();

        else {
            this.repository.SmartDeviceRemove(component);

            this.mainController.getFactory().deleteDevice(component);

            SmartDeviceRemoveChoice();
        }
    }
    private void SmartDeviceUpdateChoice()throws DeviceNotFound {
        System.out.println(this.repository.getFactory());

        String [] components = menu.MenuDiviceUpdate();
        if (components == null) SmartDiviceOperationChoice();

        else {

            if( components.equals(this.menu.getError()) ) SmartDeviceUpdateChoice();

            else{

                SmartDevice sd = this.repository.findSmartDeviceByFactoryCode(components[0]);

                if (sd instanceof SmartDeviceBulb) SmartDeviceBulbUpdateChoice(components);

                if (sd instanceof SmartDeviceSpeaker) SmartDeviceSpeakerUpdateChoice(components);

                if (sd instanceof SmartDeviceCamera) SmartDeviceCameraUpdateChoice(components);

                }
        }
    }

    private void SmartDeviceBulbUpdateChoice(String[] components) {

        String[] components1 = menu.MenuSmartBulbUpdate();
        if (components1 == null) SmartDeviceUpdateChoice();

        else {
            if( components1.equals(this.menu.getError())) SmartDeviceBulbUpdateChoice(components);
            else{
                this.repository.SmartDeviceBulbUpdade(
                        components[0],
                        components[1],
                        components[2],
                        components[3],
                        components1[0],
                        components1[1]);
                SmartDeviceUpdateChoice();
                }
        }
    }

    private void SmartDeviceSpeakerUpdateChoice(String [] components){
        String [] components1 = menu.MenuSmartSpeakerUpdate();
        if (components1 == null) SmartDeviceUpdateChoice();

        else{
            if( components.equals(this.menu.getError()) ) SmartDeviceSpeakerUpdateChoice(components);

            else{
                this.repository.SmartDeviceSpeakerUpdate(
                        components[0],
                        components[1],
                        components[2],
                        components[3],
                        components1[0],
                        components1[1],
                        components1[2],
                        components1[3]);

                SmartDeviceUpdateChoice();
                }
        }
    }

    private void SmartDeviceCameraUpdateChoice(String [] components){
        String [] components1 = menu.MenuSmartCamaraUpdate();
        if (components1 == null) SmartDeviceUpdateChoice();

        else {
            if( components.equals(this.menu.getError()) ) SmartDeviceCameraUpdateChoice(components);
            else{
                this.repository.SmartDeviceCameraUpdate(
                        components[0],
                        components[1],
                        components[2],
                        components[3],
                        components1[0],
                        components1[1]);
                SmartDeviceUpdateChoice();
                }
        }
    }

    private void SmartDeviceListagemChoice(){

        this.repository.listagem();

        SmartDiviceOperationChoice();

    }

}