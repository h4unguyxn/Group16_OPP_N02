package com.example.demo.manager;

import com.example.demo.common.Identifiable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GenericManager<T extends Identifiable> {
    private List<T> items = new ArrayList<>();
    private final String filename;

    public GenericManager(String filename) {
        this.filename = filename;
        loadFromFile();
    }

    public void add(T item) {   //Thêm một đối tượng mới vào danh sách và lưu danh sách xuống file nhị phân.
        items.add(item);    //thêm đối tượng vào danh sách.
        saveToFile();   //ghi danh sách mới xuống file để đảm bảo dữ liệu được lưu trữ vĩnh viễn.
    }

    public boolean update(String id, T newItem) {  //Cập nhật đối tượng đã tồn tại trong danh sách theo ID.
        for (int i = 0; i < items.size(); i++) {    //Duyệt danh sách, tìm đối tượng có id phù hợp.
            if (items.get(i).getId().equals(id)) {
                items.set(i, newItem);  // Thay thế đối tượng cũ
                saveToFile();   // Ghi lại file
                return true;
            }
        }
        return false;
    }

    public void delete(String id) { //Xóa đối tượng khỏi danh sách dựa theo ID.
        items.removeIf(item -> item.getId().equals(id));    //lọc và xóa phần tử có id phù hợp.
        saveToFile();   //ghi lại vào file.
    }

    public List<T> getAll() {
        return items;
    }

    public T findById(String id) {
        for (T item : items) {
            if (item.getId().equals(id)) return item;
        }
        return null;
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            items = (List<T>) ois.readObject();
        } catch (Exception e) {
            items = new ArrayList<>();
        }
    }
}
