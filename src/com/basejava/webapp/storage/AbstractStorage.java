package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        return runGet(getSearchKeyIfExist(uuid));
    }

    public void delete(String uuid) {
        Object searchKey = getSearchKeyIfExist(uuid);
        runDelete(searchKey);
    }

    public void update(Resume resume) {
        Object searchKey = getSearchKeyIfExist(resume.getUuid());
        runUpdate(resume, searchKey);
    }


    public void save(Resume resume) {
        Object searchKey = getSearchKeyifNotExist(resume.getUuid());
        runSave(resume, searchKey);
    }

    protected abstract void runDelete(Object searchKey);

    protected abstract void runUpdate(Resume resume, Object searchKey);

    protected abstract void runSave(Resume resume, Object searchKey);

    protected abstract Resume runGet(Object searchKey);

    protected abstract boolean exist(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract List<Resume> runGetAll();

    private Object getSearchKeyIfExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!exist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getSearchKeyifNotExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (exist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = runGetAll();
            Collections.sort(list);
            return list;
    }

}
