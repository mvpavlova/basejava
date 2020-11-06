package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        return runGet(getIndexIfExist(uuid));
    }

    public void delete(String uuid) {
        Object searchKey = getIndexIfExist(uuid);
        runDelete(searchKey);
    }

    public void update(Resume resume) {
        Object searchKey = getIndexIfExist(resume.getUuid());
        runUpdate(resume, searchKey);
    }


    public void save(Resume resume) {
        Object searchKey = getIndexIfNotExist(resume.getUuid());
        runSave(resume, searchKey);
    }

    protected abstract void runDelete(Object searchKey);

    protected abstract void runUpdate(Resume resume, Object searchKey);

    protected abstract void runSave(Resume resume, Object searchKey);

    protected abstract Resume runGet(Object searchKey);

    protected abstract boolean exist(Object searchKey);

    protected abstract Object getIndex(String uuid);

    private Object getIndexIfExist(String uuid) {
        Object searchKey = getIndex(uuid);
        if (!exist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getIndexIfNotExist(String uuid) {
        Object searchKey = getIndex(uuid);
        if (exist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

}
