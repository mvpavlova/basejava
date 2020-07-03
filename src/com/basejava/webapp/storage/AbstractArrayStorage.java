package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int storage_limit = 10_000;
    protected Resume[] storage = new Resume[storage_limit];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index < 0) {
            System.out.println("Resume " + uuid + " not in array");
            return null;
        }
        return storage[index];
    }

    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(index < 0) {
            System.out.println("Resume " + resume + " already in array");
        }
        storage[index] = resume;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(index < 0) {
            if (size < storage.length) {
                saveElement(resume, index);
                size++;
            } else {
                System.out.println("Array is full");
            }
        } else {
            System.out.println("Resume " + resume + " already in array");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not in array");
        } else {
            deleteElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume[] getAll() {
        return copyOf(storage, size);
    }

    protected abstract void deleteElement(int index);
    protected abstract void saveElement(Resume resume, int index);
    protected abstract int getIndex(String uuid);
}
