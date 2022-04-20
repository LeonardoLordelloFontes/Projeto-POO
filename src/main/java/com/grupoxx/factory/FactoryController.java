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
        System.out.println(this.repository.getFactory());
        int choice = menu.MenuTipoDispositivoOperacoes();

        switch (choice){

            case 1: SmartDeviceAddChoice();

            case 2: SmartDeviceRemoveChoice();

            case 3: SmartDeviceUpdateChoice();

            case 4: SmartDeviceStateChoice();

            case 5: SmartDevicePropertiesChoice();

            case 6: this.mainController.mainController();

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
            this.repository.SmartDeviceBulbAdd(
                    components[0],
                    Double.parseDouble(components[1]),
                    Double.parseDouble(components[2]),
                    Double.parseDouble(components[3]));

            this.mainController.getFactory().setDeviceAvailability(components[0], true);
            SmartDeviceBulbChoice();
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
        System.out.println(this.repository.getFactory());

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

                SmartDevice sd = this.repository.getDevice(components[0]);
                if(sd == null) throw new DeviceNotFound("O dispositivo de código de fábrica "+components[0]+ "não foi encontrado!!");

                if (sd instanceof SmartDeviceBulb) SmartDeviceBulbUpdateChoice(components);

                if (sd instanceof SmartDeviceSpeaker) SmartDeviceSpeakerUpdateChoice(components);

                if (sd instanceof SmartDeviceCamera) SmartDeviceCameraUpdateChoice(components);

                }
        }
    }

    private void SmartDeviceBulbUpdateChoice(String[] components) {

        String component = menu.MenuSmartBulbUpdate();
        if (component == null) SmartDeviceUpdateChoice();

        else {
            if( component.equals(this.menu.getError()[0])) SmartDeviceBulbUpdateChoice(components);
            else{
                this.repository.SmartDeviceBulbUpdade(
                        components[0],
                        components[1],
                        components[2],
                        components[3],
                        component);
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
                        components1[4],
                        components1[5]);
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
                        components1[4],
                        components1[5]);
                SmartDeviceUpdateChoice();
                }
        }
    }

    private void SmartDeviceStateChoice(){
        int stateChoice = menu.MenuSmartDeviceState();

        switch (stateChoice){

            case 1: SmartDeviceBulbStateChoice();

            case 2: SmartDeviceSpeakerStateChoice();

            case 3: SmartDeviceCameraStateChoice();

            case 4: SmartDiviceAllStateChoice();

            case 5: SmartEspecificDiviceStateChoice();

            case 6: SmartDiviceOperationChoice();

            case -1: SmartDeviceStateChoice();
        }
    }

    private void SmartDeviceBulbStateChoice(){
        int d = menu.MenuSmartDeviceOffAndOn();

        switch (d){

            case 1: this.repository.SmartDeviceState("sb", SmartDevice.State.ON);

            case 2: this.repository.SmartDeviceState("sb", SmartDevice.State.OFF);

            case 3: SmartDeviceStateChoice();
        }
        SmartDeviceStateChoice();
    }

    private void SmartDeviceSpeakerStateChoice(){
        int d = menu.MenuSmartDeviceOffAndOn();

        switch (d){

            case 1: this.repository.SmartDeviceState("ss", SmartDevice.State.ON);

            case 2: this.repository.SmartDeviceState("ss", SmartDevice.State.OFF);

            case 3: SmartDeviceStateChoice();
        }
        SmartDeviceStateChoice();
    }

    private void SmartDeviceCameraStateChoice(){
        int d = menu.MenuSmartDeviceOffAndOn();

        switch (d){

            case 1: this.repository.SmartDeviceState("sc", SmartDevice.State.ON);

            case 2: this.repository.SmartDeviceState("sc", SmartDevice.State.OFF);

            case 3: SmartDeviceStateChoice();
        }
        SmartDeviceStateChoice();
    }

    private void SmartDiviceAllStateChoice(){
        int d = menu.MenuSmartDeviceOffAndOn();

        switch (d){

            case 1: this.repository.SmartDeviceState(" ",SmartDevice.State.ON);

            case 2: this.repository.SmartDeviceState(" ",SmartDevice.State.OFF);

            case 3: SmartDeviceStateChoice();
        }
        SmartDeviceStateChoice();
    }

    private void SmartEspecificDiviceStateChoice(){
        int d = menu.MenuSmartDeviceOffAndOn();

        if (d == 3) SmartDeviceStateChoice();

        String factorycode = menu.SmartDeviceEspecificOffAndOn();
        if (factorycode == null) SmartDeviceStateChoice();

        switch (d){

            case 1: this.repository.SmartEpecificDiviceState(factorycode, SmartDevice.State.ON);

            case 2: this.repository.SmartEpecificDiviceState(factorycode, SmartDevice.State.OFF);
        }
        SmartEspecificDiviceStateChoice();
    }

    private void SmartDevicePropertiesChoice() {
        int choice = menu.MenuSmartDiviceProperties();

        switch (choice) {

            case 1: SmartBulbToneChangeChoice();

            case 2: SmartSpeakerPropertiesChangeChoice();

            case 3: SmartDiviceOperationChoice();

            case -1: SmartDevicePropertiesChoice();
        }
    }

    private void SmartBulbToneChangeChoice(){
        int choice = menu.MenuSmartBulbToneChange();

        switch (choice){

            case 1: SmartBulbToneChangeNeutralChoice();

            case 2: SmartBulbToneChangeWarmChoice();

            case 3: SmartBulbToneChangeColdChoice();

            case 4: SmartBulbToneChangeChoiceEspecific();

            case 5: SmartDevicePropertiesChoice();

            case -1: SmartBulbToneChangeChoice();
        }

    }

    private void SmartBulbToneChangeNeutralChoice(){
        this.repository.SmartDeviceTone("N");
        SmartBulbToneChangeChoice();
    }

    private void SmartBulbToneChangeWarmChoice(){
        this.repository.SmartDeviceTone("W");
        SmartBulbToneChangeChoice();
    }

    private void SmartBulbToneChangeColdChoice(){
        this.repository.SmartDeviceTone("C");
        SmartBulbToneChangeChoice();
    }

    private void SmartBulbToneChangeChoiceEspecific() {
        String[] components = menu.MenuSmartBulbEspecificToneChange();
        if (components == null) SmartBulbToneChangeChoice();

        else {
            switch (components[0]) {

                case "1":
                    components[0] = "N";
                case "2":
                    components[0] = "W";
                case "3":
                    components[0] = "C";
            }

            this.repository.SmartDeviceEspecificTone(components[0], components[1]);

            SmartBulbToneChangeChoiceEspecific();

        }
    }

    private void SmartSpeakerPropertiesChangeChoice() {
        int choice = menu.MenuSmartSpeakerProperties();

        switch (choice) {

            case 1: SmartSpeakerPropertiesVolumeChangeChoice();

            case 2: SmartSpeakerPropertiesRadioStationChangeChoice();

            case 3: SmartSpeakerPropertiesEspecificChangeChoice();

            case 4: SmartDevicePropertiesChoice();

            case -1: SmartSpeakerPropertiesChangeChoice();
        }
    }

    private void SmartSpeakerPropertiesVolumeChangeChoice(){
        String volume = menu.MenuSmartSpeakerVolume();
        if(volume == null) SmartSpeakerPropertiesChangeChoice();
        else this.repository.SmartDeviceSpeakerProperties(volume,"V");
    }

    private void SmartSpeakerPropertiesRadioStationChangeChoice(){
        String radioStation = menu.MenuSmartSpeakerRadioStation();
        if(radioStation == null) SmartSpeakerPropertiesChangeChoice();
        else this.repository.SmartDeviceSpeakerProperties(radioStation,"R");
    }



    private void SmartSpeakerPropertiesEspecificChangeChoice(){
        String [] components = menu.MenuSmartSpeakerPropertiesEspecific();
        if (components == null) SmartDeviceUpdateChoice();
        else this.repository.SmartDeviceEspecificSmartSpeakerProperties(components[0],components[1],components[2]);

    }
}