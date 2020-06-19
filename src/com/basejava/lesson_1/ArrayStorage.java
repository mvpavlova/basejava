package com.basejava.lesson_1;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null );
    }

    void save(Resume r) {
        int count = 0;
        while (count < storage.length) {
            if(storage[count] == null){
                storage[count] = r;
                break;
            }
            count++;
        }
    }

    String get(String uuid) {
        return uuid;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid == uuid) {
                storage[i] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int count = 0;
        while (count < storage.length) {
            if(storage[count] == null){
                break;
            }
            count++;
        }

        return count;
    }
}
