package com.grupoxx.smartdevice;


import com.grupoxx.menu.Menu;
import com.grupoxx.smarthouse.SmartHouseController;
import com.grupoxx.smarthouse.SmartHouseRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.grupoxx.menu.Menu.*;

public class SmartDeviceController {
    private SmartDeviceRepository repository;

    public SmartDeviceController(SmartDeviceRepository repository){
        this.repository = repository;
        DiviceChoice();

    }

    public void DiviceChoice(){

        int choice = Menu.MenuDispositivos();

        switch (choice){

            case 1:
                int deviceChoice = Menu.MenuTipoDispositivo();
                switch (deviceChoice){

                    case 1: SmartDeviceBulbChoice();

                    case 2: SmartDeviceSpeakerChoice();

                    case 3: SmartDeviceCameraChoice();

                    case 4: DiviceChoice();
                }

            case 2:

                SmartDeviceRemoveChoice();

            case 3:

                SmartDeviceUpdateChoice();

            case 4:
                System.out.println("acabou");
        }
    }

    public void SmartDeviceBulbChoice(){
        String[] components = MenuSmartBulbAdd();

    }

    public void SmartDeviceSpeakerChoice(){
        String[] components = MenuSmartSpeakerAdd();

    }

    public static void SmartDeviceCameraChoice(){

        String[] components = MenuSmartCamaraAdd();

    }

    public void SmartDeviceRemoveChoice(){

        }

    }

}
