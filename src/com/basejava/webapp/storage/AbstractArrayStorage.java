package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage extends AbstractStorage<Resume> {
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
    protected void runSave(Resume resume, Object index){
        if(size == STORAGE_LIMIT) {
            throw new StorageException("Array is full", resume.getUuid());
        } else {
            saveElement(resume, (Integer) index);
            size++;
        }
    }

    @Override
    protected void runUpdate(Resume resume, Object index) {
        storage[(Integer)index] = resume;
    }

    @Override
    protected Resume runGet(Object index) {
        return storage[(int) index];
    }

    @Override
    protected void runDelete(Object index) {
        deleteElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean exist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract void saveElement(Resume resume, int index);

    protected abstract void deleteElement(int index);

    protected abstract Object getIndex(String uuid);
}



