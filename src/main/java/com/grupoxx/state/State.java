package com.grupoxx.state;

import com.grupoxx.main.MainController;

import java.io.*;

public class State implements Serializable {
    private MainController mainController;

    public State() {
    }

    public State(MainController mainController) {
        this.mainController = mainController;
    }

    public void saveState(String filePath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(mainController);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public MainController loadState(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        MainController mainController = (MainController) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return mainController;
    }
}
