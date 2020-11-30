package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public Resume get(String uuid) {
        LOG.info("Get" + uuid);
        return runGet(getSearchKeyIfExist(uuid));
    }

    public void delete(String uuid) {
        LOG.info("Delete" + uuid);
        SK searchKey = getSearchKeyIfExist(uuid);
        runDelete(searchKey);
    }

    public void update(Resume resume) {
        LOG.info("Update" + resume);
        SK searchKey = getSearchKeyIfExist(resume.getUuid());
        runUpdate(resume, searchKey);
    }


    public void save(Resume resume) {
        LOG.info("Save" + resume);
        SK searchKey = getSearchKeyifNotExist(resume.getUuid());
        runSave(resume, searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("Get all Sorted");
        List<Resume> list = runGetAll();
        Collections.sort(list);
        return list;
    }

    protected abstract void runDelete(SK searchKey);

    protected abstract void runUpdate(Resume resume, SK searchKey);

    protected abstract void runSave(Resume resume, SK searchKey);

    protected abstract Resume runGet(SK searchKey);

    protected abstract boolean exist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract List<Resume> runGetAll();

    private SK getSearchKeyIfExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!exist(searchKey)) {
            LOG.warning("Resume" + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getSearchKeyifNotExist(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (exist(searchKey)) {
            LOG.warning("Resume" + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
