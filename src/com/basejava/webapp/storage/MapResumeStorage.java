package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private static Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void runDelete(Resume r) {
        storage.remove(r.getUuid());
    }


    @Override
    protected void runUpdate(Resume resume, Resume r) {
        storage.put((resume.getUuid()), resume);
    }

    @Override
    protected void runSave(Resume resume, Resume r) {
        storage.put((resume.getUuid()), resume);
    }

    @Override
    protected Resume runGet(Resume r) {
        return r;
    }

    @Override
    protected boolean exist(Resume resume) {
        return resume !=null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public List<Resume> runGetAll() {
        return new ArrayList<>(storage.values());
    }
}
