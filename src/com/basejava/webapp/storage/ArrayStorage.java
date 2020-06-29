package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        if(getIndex(resume.getUuid()) >= 0) {
            System.out.println("Resume " + resume + " already in array");
        } else {
            storage[getIndex(resume.getUuid())] = resume;
        }
    }

    public void save(Resume resume) {
        if(getIndex(resume.getUuid()) < 0) {
            if (size < storage.length) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Array is full");
            }
        } else {
            System.out.println("Resume " + resume + " already in array");
        }
    }

    public Resume get(String uuid) {
        if(getIndex(uuid) < 0) {
            System.out.println("Resume " + uuid + " not in array");
            return null;
        }
        return storage[getIndex(uuid)];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
                System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
                size--;
        } else {
            System.out.println("Resume " + uuid + " not in array");
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

    private int getIndex(String uuid) {
        for(int i = 0; i < size; i++) {
            if(uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
