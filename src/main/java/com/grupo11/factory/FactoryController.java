package com.grupo11.factory;

import com.grupo11.community.Community;
import com.grupo11.main.MainController;
import com.grupo11.smartdevice.*;
import com.grupo11.smartdevice.exception.DeviceAlreadyExist;
import com.grupo11.smartdevice.exception.DeviceNotFound;

public class FactoryController {

    /**
     * O "armazem" de todos os repositórios
     */
    private Community community;
    /**
     * Menus de fábrica
     */
    private FactoryMenu menu;

    private FactoryController() {
    }

    /**
     * Contrutor do controlador
     *
     * @param community
     */
    public FactoryController(Community community) {
        this.community = community;
        this.menu = new FactoryMenu();
    }

    /**
     * Metodo que executa o controlador
     */
    public void runFactoryController() {
        smartDeviceController();
    }

    /**
     *
     * Controlador do menu das operações dos dispositivos
     *
     */
    private void smartDeviceController(){
        switch (menu.MenuTipoDispositivoOperacoes()) {
            case 1 -> addSmartDeviceController();
            case 2 -> removeSmartDeviceController();
            case 3 -> updateSmartDeviceController();
            case 4 -> listSmartDeviceController();
            case 5 -> { MainController mainController = new MainController(community); mainController.runMainController();}
            case -1 -> smartDeviceController();
        }
    }

    /**
     * Controlador do menu de adicionar dispositivos
     */
    private void addSmartDeviceController() {
        switch (menu.MenuTipoDispositivoAdd()) {
            case 1 -> addSmartBulbController();
            case 2 -> addSmartSpeakerController();
            case 3 -> addSmartCameraController();
            case 4 -> smartDeviceController();
            case -1 -> addSmartDeviceController();
        }
    }

    /**
     * Controlador do menu de adicionar lampâmdas
     */
    private void addSmartBulbController() {
        String[] components = menu.MenuSmartBulbAdd();

        switch (components[0]) {
            case "*" -> addSmartDeviceController();
            case "-1" -> addSmartBulbController();
            default -> {
                try {
                    this.community.getFactory().getSmartDeviceRepository().SmartDeviceBulbAdd(
                            components[0],
                            Double.parseDouble(components[1]),
                            Double.parseDouble(components[2]),
                            Double.parseDouble(components[3]));

                    this.community.getFactory().setDeviceAvailability(components[0], true);
                    addSmartBulbController();
                } catch (DeviceAlreadyExist e) {
                    System.out.println(e.getMessage());
                    addSmartBulbController();
                }
            }
        }
    }

    /**
     * Controlador do menu de adicionar colunas
     */
    private void addSmartSpeakerController() {
        String[] components = menu.MenuSmartSpeakerAdd();
        switch (components[0]) {
            case "*" -> addSmartDeviceController();
            case "-1" -> addSmartSpeakerController();
            default -> {
                try {
                    this.community.getFactory().getSmartDeviceRepository().SmartDeviceSpeakerAdd(
                            components[0],
                            Double.parseDouble(components[1]),
                            Double.parseDouble(components[2]),
                            components[3],
                            Integer.parseInt(components[4]));

                    this.community.getFactory().setDeviceAvailability(components[0], true);
                    addSmartSpeakerController();
                } catch (DeviceAlreadyExist e) {
                    System.out.println(e.getMessage());
                    addSmartSpeakerController();
                }
            }
        }
    }

    /**
     * Controlador do menu de adicionar camaras
     */
    private void addSmartCameraController() {
        String[] components = menu.MenuSmartCamaraAdd();
        switch (components[0]) {
            case "*" -> addSmartDeviceController();
            case "-1" -> addSmartCameraController();
            default -> {
                try {
                    this.community.getFactory().getSmartDeviceRepository().SmartDeviceCameraAdd(
                            components[0],
                            Double.parseDouble(components[1]),
                            Double.parseDouble(components[2]),
                            Integer.parseInt(components[3]),
                            Integer.parseInt(components[4]));

                    this.community.getFactory().setDeviceAvailability(components[0], true);
                    addSmartCameraController();
                } catch (DeviceAlreadyExist e) {
                    System.out.println(e.getMessage());
                    addSmartCameraController();
                }
            }
        }
    }

    /**
     * Controlador do menu de remoção de dispositivos da fábrica
     */
    private void removeSmartDeviceController() {
        menu.MenuListagem (this.community.getFactory().onlyDeviceAvailable());
        String component = menu.MenuTipoDispositivoRemove();
        if (component.equals("*")) smartDeviceController();
        else {
            try {
                this.community.getFactory().getSmartDeviceRepository().SmartDeviceRemove(component);
                this.community.getFactory().deleteDevice(component);
                removeSmartDeviceController();
            }
           catch (DeviceNotFound e){System.out.println(e.getMessage()); removeSmartDeviceController();}
        }
    }

    /**
     * Controlador do menu de atualização de dispositivos
     */
    private void updateSmartDeviceController() {
        menu.MenuListagem (this.community.getFactory().onlyDeviceAvailable());
        String [] components = menu.MenuDiviceUpdate();
        switch (components[0]) {
            case "*" -> smartDeviceController();
            case "-1" -> updateSmartDeviceController();
            default -> {
                SmartDevice sd = this.community.getFactory().getSmartDeviceRepository().findSmartDeviceByFactoryCode(components[0]);

                updateSmartDeviceControllerAux(sd,components);
            }
        }
    }

    private void updateSmartDeviceControllerAux(SmartDevice sd, String[] components){
        if (sd instanceof SmartDeviceBulb) updateSmartBulbController(components);
        else if (sd instanceof SmartDeviceSpeaker) updateSmartSpeakerController(components);
        else if (sd instanceof SmartDeviceCamera) updateSmartCameraController(components);
    }

    /**
     * Controlador do menu de atualização de lâmpadas
     *
     * @param components componentes do dispositivo a alterar
     */
    private void updateSmartBulbController(String[] components) {
        String[] components1 = menu.MenuSmartBulbUpdate();
        switch (components[0]) {
            case "*" -> updateSmartDeviceController();
            case "-1" -> updateSmartBulbController(components);
            default -> {
                try {
                    this.community.getFactory().getSmartDeviceRepository().SmartDeviceBulbUpdade(
                            components[0],
                            components[1],
                            components[2],
                            components[3],
                            components1[0],
                            components1[1],
                            "#");

                    this.community.getFactory().updateDevice(components[0], components[1]);
                    updateSmartDeviceController();
                } catch (DeviceNotFound e) {
                    System.out.println(e.getMessage());
                    updateSmartBulbController(components);
                }
            }
        }
    }

    /**
     * Controlador do menu de atualização de colunas
     *
     * @param components componentes do dispositivo a alterar
     */
    private void updateSmartSpeakerController(String [] components){
        String [] components1 = menu.MenuSmartSpeakerUpdate();
        switch (components[0]) {
            case "*" -> updateSmartDeviceController();
            case "-1" -> updateSmartSpeakerController(components);
            default -> {
                try {
                    this.community.getFactory().getSmartDeviceRepository().SmartDeviceSpeakerUpdate(
                            components[0],
                            components[1],
                            components[2],
                            components[3],
                            components1[0],
                            components1[1],
                            components1[2],
                            components1[3],
                            "#");

                    this.community.getFactory().updateDevice(components[0], components[1]);
                    updateSmartDeviceController();
                } catch (DeviceNotFound e) {
                    System.out.println(e.getMessage());
                    updateSmartSpeakerController(components);
                }
            }
        }
    }

    /**
     * Controlador do menu de atualização de camaras
     *
     * @param components componentes do dispositivo a alterar
     */
    private void updateSmartCameraController(String [] components){
        String [] components1 = menu.MenuSmartCamaraUpdate();
        switch (components[0]) {
            case "*" -> updateSmartDeviceController();
            case "-1" -> updateSmartCameraController(components);
            default -> {
                try {
                    this.community.getFactory().getSmartDeviceRepository().SmartDeviceCameraUpdate(
                            components[0],
                            components[1],
                            components[2],
                            components[3],
                            components1[0],
                            components1[1],
                            "#");

                    this.community.getFactory().updateDevice(components[0], components[1]);
                    updateSmartDeviceController();
                } catch (DeviceNotFound e) {
                    System.out.println(e.getMessage());
                    updateSmartCameraController(components);
                }
            }
        }
    }

    /**
     * Controlador do menu de listagem
     */
    private void listSmartDeviceController() {
        menu.MenuListagem (this.community.getFactory().onlyDeviceAvailable());
        smartDeviceController();
    }
}