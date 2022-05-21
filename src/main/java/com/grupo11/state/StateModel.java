package com.grupo11.state;

import com.grupo11.main.MainModel;

import java.io.*;

public class StateModel {
    private MainModel community;

    public StateModel() {
    }

    public StateModel(MainModel community) {
        this.community = community;
    }

    /**
     * Salva o estado de um objeto MainModel
     *
     * @param filePath o caminho do arquivo onde vai ser guardado
     * @throws IOException
     */

    public void saveState(String filePath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(community);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * Carrega o estado de um objeto MainModel
     *
     * @param filePath o caminho do arquivo que vai ser carregado
     * @return o objeto MainModel, que foi carregado o estado
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public MainModel loadState(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        MainModel communityState = (MainModel) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return communityState;
    }
}
