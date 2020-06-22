package com.basejava.lesson_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size,  null );
        size = 0;
    }

    void save(Resume r) {
        if(size < storage.length) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
       for(int i = 0; i < size; i++) {
           if(storage[i].uuid == uuid) {
               return storage[i];
           }
       }
        return null;
    }

    void delete(String uuid) {
        List<String> newStorage = new ArrayList(Arrays.asList(storage));
        newStorage.remove(uuid);
        storage = newStorage.toArray(new Resume[newStorage.size()]);
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
