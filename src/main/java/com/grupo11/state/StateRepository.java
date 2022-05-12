package com.grupo11.state;

import com.grupo11.main.MainModel;

import java.io.*;

public class StateRepository {
    private MainModel community;

    public StateRepository() {
    }

    public StateRepository(MainModel community) {
        this.community = community;
    }

    public void saveState(String filePath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(community);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public MainModel loadState(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        MainModel communityState = (MainModel) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return communityState;
    }
}
