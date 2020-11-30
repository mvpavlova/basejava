package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    @Override
    public List<Resume> runGetAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void runSave(Resume resume, Integer index){
        if(size == STORAGE_LIMIT) {
            throw new StorageException("Array is full", resume.getUuid());
        } else {
            saveElement(resume, index);
            size++;
        }
    }

    @Override
    protected void runUpdate(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    protected Resume runGet(Integer index) {
        return storage[index];
    }

    @Override
    protected void runDelete(Integer index) {
        deleteElement(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean exist(Integer index) {
        return index >= 0;
    }

    protected abstract void saveElement(Resume resume, int index);

    protected abstract void deleteElement(int index);

    protected abstract Integer getSearchKey(String uuid);
}



