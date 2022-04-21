package com.grupoxx.factory;

import com.grupoxx.main.MainController;
import com.grupoxx.smartdevice.*;


public class FactoryController {
    private final SmartDeviceRepository repository;
    private final MainController mainController;
    private final FactoryMenu menu;

    public FactoryController(MainController mainController){
        this.mainController = mainController;
        this.repository = mainController.getFactory().getSmartDeviceRepository();
        this.menu = new FactoryMenu();
        smartDeviceController();
    }

    private void smartDeviceController(){
        switch (menu.MenuTipoDispositivoOperacoes()) {
            case 1: addSmartDeviceController();
            case 2: removeSmartDeviceController();
            case 3: updateSmartDeviceController();
            case 4: listSmartDeviceController();
            case 5: this.mainController.mainController();
            case -1: smartDeviceController();
        }
    }

    private void addSmartDeviceController() {
        switch (menu.MenuTipoDispositivoAdd()) {
            case 1: addSmartBulbController();
            case 2: addSmartSpeakerController();
            case 3: addSmartCameraController();
            case 4: smartDeviceController();
            case -1: addSmartDeviceController();
        }
    }

    private void addSmartBulbController() {
        String[] components = menu.MenuSmartBulbAdd();
        switch (components[0]) {
            case "*" : addSmartDeviceController();
            case "-1": addSmartBulbController();
            default:{
                this.repository.SmartDeviceBulbAdd(
                        components[0],
                        Double.parseDouble(components[1]),
                        Double.parseDouble(components[2]),
                        Double.parseDouble(components[3]));

                this.mainController.getFactory().setDeviceAvailability(components[0], true);
                addSmartBulbController();}
        }
    }

    private void addSmartSpeakerController() {
        String[] components = menu.MenuSmartSpeakerAdd();
        switch (components[0]) {
            case "*": addSmartDeviceController();
            case "-1": addSmartSpeakerController();
            default:{
                this.repository.SmartDeviceSpeakerAdd(
                    components[0],
                    Double.parseDouble(components[1]),
                    Double.parseDouble(components[2]),
                    components[3],
                    Integer.parseInt(components[4]));

                this.mainController.getFactory().setDeviceAvailability(components[0], true);
                addSmartSpeakerController();}

        }
    }

    private void addSmartCameraController() {
        String[] components = menu.MenuSmartCamaraAdd();
        switch (components[0]) {
            case "*": addSmartDeviceController();
            case "-1": addSmartCameraController();
            default:{
                this.repository.SmartDeviceCameraAdd(
                    components[0],
                    Double.parseDouble(components[1]),
                    Double.parseDouble(components[2]),
                    Integer.parseInt(components[3]),
                    Integer.parseInt(components[4]));

                this.mainController.getFactory().setDeviceAvailability(components[0], true);
                addSmartCameraController();}
        }
    }

    private void removeSmartDeviceController() {
        this.repository.listagem();
        String component = menu.MenuTipoDispositivoRemove();
        if (component.equals("*")) smartDeviceController();
        else {
            this.repository.SmartDeviceRemove(component);
            this.mainController.getFactory().deleteDevice(component);
            removeSmartDeviceController();
        }
    }
    private void updateSmartDeviceController() {
        this.repository.listagem();
        String [] components = menu.MenuDiviceUpdate();
        switch (components[0]) {
            case "*" : smartDeviceController();
            case "-1": updateSmartDeviceController();
            default:{
                SmartDevice sd = this.repository.findSmartDeviceByFactoryCode(components[0]);
                if (sd instanceof SmartDeviceBulb) updateSmartBulbController(components);
                if (sd instanceof SmartDeviceSpeaker) updateSmartSpeakerController(components);
                if (sd instanceof SmartDeviceCamera) updateSmartCameraController(components); }
        }
    }

    private void updateSmartBulbController(String[] components) {
        String[] components1 = menu.MenuSmartBulbUpdate();
        switch (components[0]) {
            case "*" : updateSmartDeviceController();
            case "-1": updateSmartBulbController(components);
            default:{
                this.repository.SmartDeviceBulbUpdade(
                        components[0],
                        components[1],
                        components[2],
                        components[3],
                        components1[0],
                        components1[1]);

                this.mainController.getFactory().updateDevice(components[0],components[1]);
                updateSmartDeviceController();

            }
        }
    }

    private void updateSmartSpeakerController(String [] components){
        String [] components1 = menu.MenuSmartSpeakerUpdate();
        switch (components[0]) {
            case "*" : updateSmartDeviceController();
            case "-1": updateSmartSpeakerController(components);
            default:{
                this.repository.SmartDeviceSpeakerUpdate(
                        components[0],
                        components[1],
                        components[2],
                        components[3],
                        components1[0],
                        components1[1],
                        components1[2],
                        components1[3]);

                this.mainController.getFactory().updateDevice(components[0],components[1]);
                updateSmartDeviceController();
            }
        }
    }

    private void updateSmartCameraController(String [] components){
        String [] components1 = menu.MenuSmartCamaraUpdate();
        switch (components[0]) {
            case "*" : updateSmartDeviceController();
            case "-1": updateSmartCameraController(components);
            default:{
                this.repository.SmartDeviceCameraUpdate(
                        components[0],
                        components[1],
                        components[2],
                        components[3],
                        components1[0],
                        components1[1]);

                this.mainController.getFactory().updateDevice(components[0],components[1]);
                updateSmartDeviceController();
            }
        }
    }

    private void listSmartDeviceController(){
        this.repository.listagem();
        smartDeviceController();
    }
}