package com.grupo11.state;

import com.grupo11.main.MainController;

import java.io.*;

public class StateRepository {
    private MainController mainController;

    public StateRepository() {
    }

    public StateRepository(MainController mainController) {
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
