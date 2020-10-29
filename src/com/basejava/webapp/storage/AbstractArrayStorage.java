package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void runSave(Resume resume, java.lang.Object index) {
        if(size == STORAGE_LIMIT) {
            throw new SecurityException("Array is full");
        } else {
            saveElement(resume, (Integer) index);
            size++;
        }
    }

    @Override
    protected void runUpdate(Resume resume, java.lang.Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected Resume runGet(java.lang.Object index) {
        return storage[(int) index];
    }

    @Override
    protected void runDelete(java.lang.Object index) {
        deleteElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void saveElement(Resume resume, int index);

    protected abstract void deleteElement(int index);

    protected abstract Integer getIndex(String uuid);
}



