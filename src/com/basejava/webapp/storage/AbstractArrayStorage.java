package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.fill;

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

    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(index >= 0) {
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

    protected abstract void saveElement(Resume resume, int index);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            size--;
        } else {
            System.out.println("Resume " + uuid + " not in array");
        }
    }

    public Resume[] getAll() {
        return copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);
}
