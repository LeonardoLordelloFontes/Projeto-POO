package com.grupoxx.factory;

import com.grupoxx.main.MainController;
import com.grupoxx.smartdevice.SmartDeviceRepository;

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

            case 6: System.out.println("acabou");
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
    public void SmartDeviceUpdateChoice(){
        int updateChoice = MenuTipoDispositivoUpdate();
        switch (updateChoice){

            case 1: SmartDeviceBulbUpdadeChoice();

            case 2: SmartDeviceSpeakerUpdadeChoice();

            case 3: SmartDeviceCameraUpdadeChoice();

            case 4: SmartDiviceOperationChoice();
        }
    }

    public void SmartDeviceBulbUpdadeChoice(){

        String components[] = MenuSmartBulbUpdate();
        if (components.equals(null)) SmartDeviceUpdateChoice();

        this.repository.SmartDeviceBulbUpdade(
                components[0],
                components[1],
                components[2],
                components[3],
                components[4]);


        SmartDeviceBulbUpdadeChoice();
    }

    public void SmartDeviceSpeakerUpdadeChoice(){
        String components[] = MenuSmartSpeakerUpdate();
        if (components.equals(null)) SmartDeviceUpdateChoice();

        this.repository.SmartDeviceSpeakerUpdate(
                components[0],
                components[1],
                components[2],
                components[3],
                components[4],
                components[5]);


        SmartDeviceBulbUpdadeChoice();

    }
    public void SmartDeviceCameraUpdadeChoice(){
        String components[] = MenuSmartCamaraUpdate();
        if (components.equals(null)) SmartDeviceUpdateChoice();

        this.repository.SmartDeviceCameraUpdate(
                components[0],
                components[1],
                components[2],
                components[3],
                components[4],
                components[5]);


        SmartDeviceBulbUpdadeChoice();


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
        if (factorycode.equals(null)) SmartDeviceStateChoice();

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
       String components[] = MenuSmartBulbEspecificToneChange();
       if(components.equals(null)) SmartBulbToneChangeChoice();

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
        if(volume.equals(null)) SmartSpeakerPropertiesChangeChoice();
        this.repository.SmartDeviceSpeakerProperties(volume,"V");
    }

    public void SmartSpeakerPropertiesRadioStationChangeChoice(){
        String radioStation = MenuSmartSpeakerVolume();
        if(radioStation.equals(null)) SmartSpeakerPropertiesChangeChoice();
        this.repository.SmartDeviceSpeakerProperties(radioStation,"R");
    }



    public void SmartSpeakerPropertiesEspecificChangeChoice(){
        String components[] = MenuSmartSpeakerPropertiesEspecific();
        if (components.equals(null)) SmartDeviceUpdateChoice();
        this.repository.SmartDeviceEspecificSmartSpeakerProperties(components[0],components[1],components[2]);

    }
}




