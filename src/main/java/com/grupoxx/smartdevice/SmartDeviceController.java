package com.grupoxx.smartdevice;


import static com.grupoxx.smartdevice.SmartDeviceMenu.*;

public class SmartDeviceController {
    private SmartDeviceRepository repository;

    public SmartDeviceController(SmartDeviceRepository repository){
        this.repository = repository;
        SmartDiviceOperationChoice();

    }

    public void SmartDiviceOperationChoice(){
        System.out.println(this.repository.getFactory());
        int choice = MenuTipoDispositivoOperacoes();

        switch (choice){

            case 1: SmartDeviceAddChoice();

            case 2: SmartDeviceRemoveChoice();

            case 3: SmartDeviceUpdateChoice();

            case 4: System.out.println("acabou");
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
}
