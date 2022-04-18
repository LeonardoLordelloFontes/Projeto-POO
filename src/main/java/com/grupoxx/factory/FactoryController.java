package com.grupoxx.factory;

import com.grupoxx.main.MainController;
import com.grupoxx.smartdevice.*;

import static com.grupoxx.factory.FactoryMenu.*;

public class FactoryController {
    private SmartDeviceRepository repository;
    private MainController mainController;

    public FactoryController(MainController mainController){
        this.repository = mainController.getFactory().getSmartDeviceRepository();
        SmartDiviceOperationChoice();

    }

    public void SmartDiviceOperationChoice(){
        System.out.println(this.repository.getFactory());
        int choice = MenuTipoDispositivoOperacoes();

        switch (choice){

            case 1: SmartDeviceAddChoice();

            case 2: SmartDeviceRemoveChoice();

            case 3: SmartDeviceUpdateChoice();

            case 4: SmartDeviceStateChoice();

            case 5: SmartDevicePropertiesChoice();

            case 6: this.mainController.mainController();
        }
    }

    public void SmartDeviceAddChoice(){

        int deviceChoice = MenuTipoDispositivoAdd();
        switch (deviceChoice){

            case 1: SmartDeviceBulbChoice();

            case 2: SmartDeviceSpeakerChoice();

            case 3: SmartDeviceCameraChoice();

            case 4: SmartDiviceOperationChoice();
        }

    }

    public void SmartDeviceBulbChoice(){
        String[] components = MenuSmartBulbAdd();
        if (components == null) SmartDeviceAddChoice();
        this.repository.SmartDeviceBulbAdd(
                        components[0],
                        Double.parseDouble(components[1]),
                        Double.parseDouble(components[2]),
                        Double.parseDouble(components[3]));

        this.mainController.getFactory().setDeviceAvailability(components[0],true);
        SmartDeviceBulbChoice();

    }

    public void SmartDeviceSpeakerChoice(){
        String[] components = MenuSmartSpeakerAdd();
        if ( components == null ) SmartDeviceAddChoice();
        this.repository.SmartDeviceSpeakerAdd(
                        components[0],
                        Double.parseDouble(components[1]),
                        Double.parseDouble(components[2]),
                        components[3],
                        Integer.parseInt(components[4]));

        this.mainController.getFactory().setDeviceAvailability(components[0],true);
        SmartDeviceSpeakerChoice();
    }

    public void SmartDeviceCameraChoice(){

        String[] components = MenuSmartCamaraAdd();
        if ( components == null ) SmartDeviceAddChoice();

        this.repository.SmartDeviceCameraAdd(
                        components[0],
                        Double.parseDouble(components[1]),
                        Double.parseDouble(components[2]),
                        Integer.parseInt(components[3]),
                        Integer.parseInt(components[4]));

        this.mainController.getFactory().setDeviceAvailability(components[0],true);
        SmartDeviceCameraChoice();

    }

    public void SmartDeviceRemoveChoice(){
        System.out.println(this.repository.getFactory());

        String component = MenuTipoDispositivoRemove();
        if (component == null) SmartDiviceOperationChoice();

        this.repository.SmartDeviceRemove(component);

        this.mainController.getFactory().deleteDevice(component);

        SmartDeviceRemoveChoice();
    }

    public void SmartDeviceUpdateChoice(){
        System.out.println(this.repository.getFactory());

        String [] components = MenuDiviceUpdate();
        if (components == null) SmartDiviceOperationChoice();

        SmartDevice sd = this.repository.getDevice(components[0]);

            if (sd instanceof SmartDeviceBulb) SmartDeviceBulbUpdateChoice(components);

            if (sd instanceof SmartDeviceSpeaker) SmartDeviceSpeakerUpdateChoice(components);

            if (sd instanceof SmartDeviceCamera) SmartDeviceCameraUpdateChoice(components);

        }

    public void SmartDeviceBulbUpdateChoice(String[] components){

        String component = MenuSmartBulbUpdate();
        if (component == null) SmartDeviceUpdateChoice();

        this.repository.SmartDeviceBulbUpdade(
                components[0],
                components[1],
                components[2],
                components[3],
                component);


        SmartDeviceUpdateChoice();
    }

    public void SmartDeviceSpeakerUpdateChoice(String [] components){
        String [] components1 = MenuSmartSpeakerUpdate();
        if (components1 == null) SmartDeviceUpdateChoice();

        this.repository.SmartDeviceSpeakerUpdate(
                components[0],
                components[1],
                components[2],
                components[3],
                components1[4],
                components1[5]);


        SmartDeviceUpdateChoice();

    }
    public void SmartDeviceCameraUpdateChoice(String [] components){
        String [] components1 = MenuSmartCamaraUpdate();
        if (components1 == null) SmartDeviceUpdateChoice();

        this.repository.SmartDeviceCameraUpdate(
                components[0],
                components[1],
                components[2],
                components[3],
                components1[4],
                components1[5]);


        SmartDeviceUpdateChoice();


    }

    public void SmartDeviceStateChoice(){
        int stateChoice = MenuSmartDeviceState();

        switch (stateChoice){

            case 1: SmartDeviceBulbStateChoice();

            case 2: SmartDeviceSpeakerStateChoice();

            case 3: SmartDeviceCameraStateChoice();

            case 4: SmartDiviceAllStateChoice();

            case 5: SmartEspecificDiviceStateChoice();

            case 6: SmartDiviceOperationChoice();
        }
    }

    public void SmartDeviceBulbStateChoice(){
        int d = MenuSmartDeviceOffAndOn();

        switch (d){

            case 1: this.repository.SmartDeviceState("sb",1);

            case 2: this.repository.SmartDeviceState("sb",2);

            case 3: SmartDeviceStateChoice();
        }
        SmartDeviceStateChoice();
    }

    public void SmartDeviceSpeakerStateChoice(){
        int d = MenuSmartDeviceOffAndOn();

        switch (d){

            case 1: this.repository.SmartDeviceState("ss",1);

            case 2: this.repository.SmartDeviceState("ss",2);

            case 3: SmartDeviceStateChoice();
        }
        SmartDeviceStateChoice();
    }

    public void SmartDeviceCameraStateChoice(){
        int d = MenuSmartDeviceOffAndOn();

        switch (d){

            case 1: this.repository.SmartDeviceState("sc",1);

            case 2: this.repository.SmartDeviceState("sc",2);

            case 3: SmartDeviceStateChoice();
        }
        SmartDeviceStateChoice();
    }

    public void SmartDiviceAllStateChoice(){
        int d = MenuSmartDeviceOffAndOn();

        switch (d){

            case 1: this.repository.SmartDeviceState(" ",1);

            case 2: this.repository.SmartDeviceState(" ",2);

            case 3: SmartDeviceStateChoice();
        }
        SmartDeviceStateChoice();
    }

    public void SmartEspecificDiviceStateChoice(){
        int d = MenuSmartDeviceOffAndOn();

        if (d == 3) SmartDeviceStateChoice();

        String factorycode = SmartDeviceEspecificOffAndOn();
        if (factorycode == null) SmartDeviceStateChoice();

        switch (d){

            case 1: this.repository.SmartEpecificDiviceState(factorycode,1);

            case 2: this.repository.SmartEpecificDiviceState(factorycode,2);
        }
        SmartEspecificDiviceStateChoice();
    }

    public void SmartDevicePropertiesChoice() {
        int choice = MenuSmartDiviceProperties();

        switch (choice) {

            case 1: SmartBulbToneChangeChoice();

            case 2: SmartSpeakerPropertiesChangeChoice();

            case 3: SmartDiviceOperationChoice();
        }
    }

    public void SmartBulbToneChangeChoice(){
        int choice = MenuSmartBulbToneChange();

        switch (choice){

            case 1: SmartBulbToneChangeNeutralChoice();

            case 2: SmartBulbToneChangeWarmChoice();

            case 3: SmartBulbToneChangeColdChoice();

            case 4: SmartBulbToneChangeChoiceEspecific();

            case 5: SmartDevicePropertiesChoice();
        }

    }

    public void SmartBulbToneChangeNeutralChoice(){
        this.repository.SmartDeviceTone("N");
        SmartBulbToneChangeChoice();
    }

    public void SmartBulbToneChangeWarmChoice(){
        this.repository.SmartDeviceTone("W");
        SmartBulbToneChangeChoice();
    }

    public void SmartBulbToneChangeColdChoice(){
        this.repository.SmartDeviceTone("C");
        SmartBulbToneChangeChoice();
    }

    public void SmartBulbToneChangeChoiceEspecific(){
       String [] components = MenuSmartBulbEspecificToneChange();
       if(components == null) SmartBulbToneChangeChoice();

       switch (components[0]){

           case "1": components[0] ="N";
           case "2": components[0] = "W";
           case "3": components[0] = "C";
       }

       this.repository.SmartDeviceEspecificTone(components[0],components[1]);

        SmartBulbToneChangeChoiceEspecific();

    }


    public void SmartSpeakerPropertiesChangeChoice() {
        int choice = MenuSmartSpeakerProperties();

        switch (choice) {

            case 1: SmartSpeakerPropertiesVolumeChangeChoice();

            case 2: SmartSpeakerPropertiesRadioStationChangeChoice();

            case 3: SmartSpeakerPropertiesEspecificChangeChoice();

            case 4: SmartDevicePropertiesChoice();
        }
    }

    public void SmartSpeakerPropertiesVolumeChangeChoice(){
        String volume = MenuSmartSpeakerVolume();
        if(volume == null) SmartSpeakerPropertiesChangeChoice();
        this.repository.SmartDeviceSpeakerProperties(volume,"V");
    }

    public void SmartSpeakerPropertiesRadioStationChangeChoice(){
        String radioStation = MenuSmartSpeakerRadioStation();
        if(radioStation == null) SmartSpeakerPropertiesChangeChoice();
        this.repository.SmartDeviceSpeakerProperties(radioStation,"R");
    }



    public void SmartSpeakerPropertiesEspecificChangeChoice(){
        String [] components = MenuSmartSpeakerPropertiesEspecific();
        if (components == null) SmartDeviceUpdateChoice();
        this.repository.SmartDeviceEspecificSmartSpeakerProperties(components[0],components[1],components[2]);

    }
}




