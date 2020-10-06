package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

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

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }


    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(index < 0) {
          throw new NotExistStorageException(resume.getUuid());
        }
        storage[index] = resume;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(index < 0) {
            if (size < storage.length) {
                saveElement(resume, index);
                size++;
            } else if (size == storage_limit){
               throw new StorageException("Array is full", resume.getUuid());
            } else {
                throw new ExistStorageException(resume.getUuid());
            }
        }
    }
    protected abstract void saveElement(Resume resume, int index);

    protected abstract void deleteElement(int index);

    protected abstract int getIndex(String uuid);
}



