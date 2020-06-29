package com.basejava.lesson_1;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    private int reviewIndex(String uuid) {
        for(int i = 0; i < size; i++) {
            if(storage[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }

    public void update(Resume r) {
        if(reviewIndex(r.getUuid()) >= 0) {
            System.out.println("Resume already in array");
        } else {
            save(r);
        }
    }

    public void save(Resume r) {
        if(reviewIndex(r.getUuid()) < 0) {
            if (size < storage.length) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Array is full");
            }
        } else {
            System.out.println("Resume already in array");
        }
    }

    public Resume get(String uuid) {
        if(reviewIndex(uuid) < 0) {
            System.out.println("Resume not in array");
        } else {
            return storage[reviewIndex(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = reviewIndex(uuid);
        if (index >= 0) {
                System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
                size--;
        } else {
            System.out.println("Resume not in array");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
