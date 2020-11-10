package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    private static Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void runDelete(Object r) {
        storage.remove(((Resume) r).getUuid());
    }


    @Override
    protected void runUpdate(Resume resume, Object r) {
        storage.put((resume.getUuid()), resume);
    }

    @Override
    protected void runSave(Resume resume, Object r) {
        storage.put((resume.getUuid()), resume);
    }

    @Override
    protected Resume runGet(Object r) {
        return (Resume) r;
    }

    @Override
    protected boolean exist(Object resume) {
        return resume !=null;
    }

    @Override
    protected Object getSearchKey(String uuid) {
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
