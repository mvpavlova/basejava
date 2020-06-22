package com.basejava.lesson_1;

import java.util.Arrays;

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
        int count = 0;
        for(int i = 0; i < size ;i++) {
            if(storage[i].uuid == uuid) {
                count = i;
                break;
            }
        }
        System.arraycopy(storage, count + 1, storage, count, storage.length -1 - count);
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
