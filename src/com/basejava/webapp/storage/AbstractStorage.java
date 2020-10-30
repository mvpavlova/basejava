package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        return runGet(ifExist(uuid));
    }

    public void delete(String uuid) {
        Object index = ifExist(uuid);
        runDelete(index);
    }

    public void update(Resume resume) {
        Object index = ifExist(resume.getUuid());
        runUpdate(resume, index);
    }


    public void save(Resume resume) {
        Object index = ifNotExist(resume.getUuid());
        runSave(resume, index);
    }

    protected abstract void runDelete(Object index);

    protected abstract void runUpdate(Resume resume, Object index);

    protected abstract void runSave(Resume resume, Object index);

    protected abstract Resume runGet(Object index);

    protected abstract boolean exist(Object index);

    protected abstract Object getIndex(String uuid);

    private Object ifExist(String uuid) {
        Object index = getIndex(uuid);
        if (!exist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return uuid;
        }
    }

    private Object ifNotExist(String uuid) {
        Object index = getIndex(uuid);
        if (exist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return uuid;
        }

    }

}
