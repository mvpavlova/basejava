package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int Storage_Limit = 10_000;
    protected Resume[] storage = new Resume[Storage_Limit];
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

    protected abstract int getIndex(String uuid);
}
