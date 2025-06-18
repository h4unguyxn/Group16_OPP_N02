package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.common.Identifiable;
import java.io.*;
import java.util.*;

public class GenericManager<T extends Identifiable> {
    protected List<T> list = new ArrayList<>();
    private final String filename;

    public GenericManager(String filename) {
        this.filename = filename;
        loadFromFile();
    }

    public void add(T obj) {
        list.add(obj);
        saveToFile();
    }

    public void update(String id, T updatedObj) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                list.set(i, updatedObj);
                saveToFile();
                return;
            }
        }
    }

    public void delete(String id) {
        list.removeIf(obj -> obj.getId().equals(id));
        saveToFile();
    }

    public List<T> getAll() {
        return new ArrayList<>(list);
    }

    public T findById(String id) {
        return list.stream().filter(obj -> obj.getId().equals(id)).findFirst().orElse(null);
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            list = (List<T>) ois.readObject();
        } catch (Exception ignored) {
            list = new ArrayList<>();
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
