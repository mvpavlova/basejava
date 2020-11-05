package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage<R> implements Storage {

    public Resume get(String uuid) {
        return runGet(getIndexIfExist(uuid));
    }

    public void delete(String uuid) {
        Object index = getIndexIfExist(uuid);
        runDelete(index);
    }

    public void update(Resume resume) {
        Object index = getIndexIfExist(resume.getUuid());
        runUpdate(resume, index);
    }


    public void save(Resume resume) {
        Object index = getIndexIfNotExist(resume.getUuid());
        runSave(resume, index);
    }

    protected abstract void runDelete(Object index);

    protected abstract void runUpdate(Resume resume, Object index);

    protected abstract void runSave(Resume resume, Object index);

    protected abstract Resume runGet(Object index);

    protected abstract boolean exist(Object index);

    protected abstract Object getIndex(String uuid);

    private Object getIndexIfExist(String uuid) {
        Object index = getIndex(uuid);
        if (!exist(index)) {
            throw new NotExistStorageException(uuid);
        } else {
            return index;
        }
    }

    private Object getIndexIfNotExist(String uuid) {
        Object index = getIndex(uuid);
        if (exist(index)) {
            throw new ExistStorageException(uuid);
        } else {
            return index;
        }

    }

}
