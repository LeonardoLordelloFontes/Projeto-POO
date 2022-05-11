package com.grupoxx.factory;

import com.grupoxx.main.MainController;
import com.grupoxx.smartdevice.*;
import com.grupoxx.smartdevice.exception.DeviceAlreadyExist;
import com.grupoxx.smartdevice.exception.DeviceNotFound;


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
            case 1 -> addSmartDeviceController();
            case 2 -> removeSmartDeviceController();
            case 3 -> updateSmartDeviceController();
            case 4 -> listSmartDeviceController();
            case 5 -> this.mainController.mainController();
            case -1 -> smartDeviceController();
        }
    }

    private void addSmartDeviceController() {
        switch (menu.MenuTipoDispositivoAdd()) {
            case 1 -> addSmartBulbController();
            case 2 -> addSmartSpeakerController();
            case 3 -> addSmartCameraController();
            case 4 -> smartDeviceController();
            case -1 -> addSmartDeviceController();
        }
    }

    private void addSmartBulbController() {
        String[] components = menu.MenuSmartBulbAdd();

        switch (components[0]) {
            case "*" -> addSmartDeviceController();
            case "-1" -> addSmartBulbController();
            default -> {
                try {
                    this.repository.SmartDeviceBulbAdd(
                            components[0],
                            Double.parseDouble(components[1]),
                            Double.parseDouble(components[2]),
                            Double.parseDouble(components[3]));

                    this.mainController.getFactory().setDeviceAvailability(components[0], true);
                    addSmartBulbController();
                } catch (DeviceAlreadyExist e) {
                    System.out.println(e.getMessage());
                    addSmartBulbController();
                }
            }
        }
    }
    private void addSmartSpeakerController() {
        String[] components = menu.MenuSmartSpeakerAdd();
        switch (components[0]) {
            case "*" -> addSmartDeviceController();
            case "-1" -> addSmartSpeakerController();
            default -> {
                try {
                    this.repository.SmartDeviceSpeakerAdd(
                            components[0],
                            Double.parseDouble(components[1]),
                            Double.parseDouble(components[2]),
                            components[3],
                            Integer.parseInt(components[4]));

                    this.mainController.getFactory().setDeviceAvailability(components[0], true);
                    addSmartSpeakerController();
                } catch (DeviceAlreadyExist e) {
                    System.out.println(e.getMessage());
                    addSmartSpeakerController();
                }

            }
        }
    }
    private void addSmartCameraController() {
        String[] components = menu.MenuSmartCamaraAdd();
        switch (components[0]) {
            case "*" -> addSmartDeviceController();
            case "-1" -> addSmartCameraController();
            default -> {
                try {
                    this.repository.SmartDeviceCameraAdd(
                            components[0],
                            Double.parseDouble(components[1]),
                            Double.parseDouble(components[2]),
                            Integer.parseInt(components[3]),
                            Integer.parseInt(components[4]));

                    this.mainController.getFactory().setDeviceAvailability(components[0], true);
                    addSmartCameraController();
                } catch (DeviceAlreadyExist e) {
                    System.out.println(e.getMessage());
                    addSmartCameraController();
                }
            }
        }
    }

    private void removeSmartDeviceController() {
        menu.MenuListagem (this.mainController.getFactory().onlyDeviceAvailable());
        String component = menu.MenuTipoDispositivoRemove();
        if (component.equals("*")) smartDeviceController();
        else {
            try {
                this.repository.SmartDeviceRemove(component);
                this.mainController.getFactory().deleteDevice(component);
                removeSmartDeviceController();
            }
           catch (DeviceNotFound e){System.out.println(e.getMessage()); removeSmartDeviceController();}
        }
    }
    private void updateSmartDeviceController() {
        menu.MenuListagem (this.mainController.getFactory().onlyDeviceAvailable());
        String [] components = menu.MenuDiviceUpdate();
        switch (components[0]) {
            case "*" -> smartDeviceController();
            case "-1" -> updateSmartDeviceController();
            default -> {
                SmartDevice sd = this.repository.findSmartDeviceByFactoryCode(components[0]);

                updateSmartDeviceControllerAux(sd,components);
            }
        }
    }

    private void updateSmartDeviceControllerAux(SmartDevice sd, String[] components){

        if (sd instanceof SmartDeviceBulb) updateSmartBulbController(components);
        if (sd instanceof SmartDeviceSpeaker) updateSmartSpeakerController(components);
        if (sd instanceof SmartDeviceCamera) updateSmartCameraController(components);

    }

    private void updateSmartBulbController(String[] components) {
        String[] components1 = menu.MenuSmartBulbUpdate();
        switch (components[0]) {
            case "*" -> updateSmartDeviceController();
            case "-1" -> updateSmartBulbController(components);
            default -> {
                try {
                    this.repository.SmartDeviceBulbUpdade(
                            components[0],
                            components[1],
                            components[2],
                            components[3],
                            components1[0],
                            components1[1],
                            "#");

                    this.mainController.getFactory().updateDevice(components[0], components[1]);
                    updateSmartDeviceController();
                } catch (DeviceNotFound e) {
                    System.out.println(e.getMessage());
                    updateSmartBulbController(components);
                }

            }
        }
    }

    private void updateSmartSpeakerController(String [] components){
        String [] components1 = menu.MenuSmartSpeakerUpdate();
        switch (components[0]) {
            case "*" -> updateSmartDeviceController();
            case "-1" -> updateSmartSpeakerController(components);
            default -> {
                try {
                    this.repository.SmartDeviceSpeakerUpdate(
                            components[0],
                            components[1],
                            components[2],
                            components[3],
                            components1[0],
                            components1[1],
                            components1[2],
                            components1[3],
                            "#");

                    this.mainController.getFactory().updateDevice(components[0], components[1]);
                    updateSmartDeviceController();
                } catch (DeviceNotFound e) {
                    System.out.println(e.getMessage());
                    updateSmartSpeakerController(components);
                }
            }
        }
    }

    private void updateSmartCameraController(String [] components){
        String [] components1 = menu.MenuSmartCamaraUpdate();
        switch (components[0]) {
            case "*" -> updateSmartDeviceController();
            case "-1" -> updateSmartCameraController(components);
            default -> {
                try {
                    this.repository.SmartDeviceCameraUpdate(
                            components[0],
                            components[1],
                            components[2],
                            components[3],
                            components1[0],
                            components1[1],
                            "#");

                    this.mainController.getFactory().updateDevice(components[0], components[1]);
                    updateSmartDeviceController();
                } catch (DeviceNotFound e) {
                    System.out.println(e.getMessage());
                    updateSmartCameraController(components);
                }
            }
        }
    }
    private void listSmartDeviceController(){

        menu.MenuListagem (this.mainController.getFactory().onlyDeviceAvailable());
        smartDeviceController();
    }
}