package com.grupoxx.smartdevice;


import com.grupoxx.menu.Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.grupoxx.menu.Menu.*;

public abstract class SmartDeviceController {

    public static void DiviceChoice(){

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
                StringBuilder  sb2 = new StringBuilder();
                sb2.append("Lista de dispositivos criados: ");
                sb2.append(fabrica);
                sb2.append("Intruduza o nome do dispositivo a atualizar ");
                System.out.println(sb2.toString());

                Scanner scanner_1 = new Scanner(System.in);

                SmartDeviceUpdateChoice(scanner_1.next());

            case 4:
                System.out.println("acabou");
        }
    }

    public static void SmartDeviceBulbChoice(){
        String componentes = MenuSmartBulbAdd().trim();

        String des [] = componentes.split(" ");

        if (des.length != 4) {
            System.out.println("Erro por favor espace as quatro inputs por um e só um espaço !");
            SmartDeviceBulbChoice();
        }

        SmartDeviceBulb sb = new SmartDeviceBulb(
                des[0], Double.parseDouble(des[1]),
                Double.parseDouble(des[2]), Double.parseDouble(des[3]) );

        fabrica.put(sb.getFactoryCode(),sb);

    }

    public static void SmartDeviceSpeakerChoice(){
        String componentes = MenuSmartSpeakerAdd().trim();

        String des [] = componentes.split(" ");

        if (des.length != 5) {
            System.out.println("Erro por favor espace os cinco inputs por um e só um espaço !");
            SmartDeviceSpeakerChoice();
        }

        SmartDeviceSpeaker ss = new SmartDeviceSpeaker(
                des[0], Double.parseDouble(des[1]),
                Double.parseDouble(des[2]), des[3], Integer.parseInt(des[4]) );

        fabrica.put(ss.getFactoryCode(),ss);

    }

    public static void SmartDeviceCameraChoice(){
        String componentes = MenuSmartCamaraAdd().trim();

        String des [] = componentes.split(" ");

        if (des.length != 5) {
            System.out.println("Erro por favor espace os cinco inputs por um e só um espaço !");
            SmartDeviceCameraChoice();
        }

        SmartDeviceCamera sc = new SmartDeviceCamera(
                des[0], Double.parseDouble(des[1]),
                Double.parseDouble(des[2]), Integer.parseInt (des[3]), Integer.parseInt(des[4]) );

        fabrica.put(sc.getFactoryCode(),sc);
    }

    public static void SmartDeviceRemoveChoice(){

        StringBuilder  sb = new StringBuilder();
        sb.append("Lista de dispositivos criados: ");
        sb.append(fabrica);
        sb.append("Intruduza o nome do dispositivo a eliminar: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);

        String factoryCode = scanner.next().trim();

        if(fabrica.isEmpty()){
            System.out.println("Não tem nada para remover...");
            DiviceChoice();
        }

        if ( fabrica.containsKey(factoryCode) == false){
            System.out.println("Apenas pode eliminar dispositivos que já foram criados !!! ");
            SmartDeviceRemoveChoice();
        }

        else {
            fabrica.remove(factoryCode);

            StringBuilder sb2 = new StringBuilder();
            sb2.append("Lista de dispositivos criados: ");
            sb2.append(fabrica);
            System.out.println(sb2.toString());
        }

    }

}
