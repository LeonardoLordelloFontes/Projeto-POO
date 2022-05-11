package com.grupo11.state;

import com.grupo11.community.Community;
import com.grupo11.main.MainController;

import java.io.*;

public class State {
    private Community community;

    public State() {
    }

    public State(Community community) {
        this.community = community;
    }

    public void saveState(String filePath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(community);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public Community loadState(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Community communityState = (Community) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return communityState;
    }
}
