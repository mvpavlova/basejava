package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
    private static Map<String, Resume> map = new HashMap<>();

    @Override
    protected void runDelete(Object uuid) {
        map.remove(uuid);
    }

    @Override
    protected void runUpdate(Resume resume, Object uuid) {
        map.put((String) uuid, resume);
    }

    @Override
    protected void runSave(Resume resume, Object uuid) {
        map.put((String) uuid, resume);
    }

    @Override
    protected Resume runGet(Object uuid) {
        return map.get((String) uuid);
    }

    @Override
    protected boolean exist(Object uuid) {
        return map.containsKey((String) uuid);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public List<Resume> runGetAll() {
        return new ArrayList<>(map.values());
    }
}
