package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
    private static Map<String, Resume> map = new HashMap<>();

    @Override
    protected void runDelete(String uuid) {
        map.remove(uuid);
    }

    @Override
    protected void runUpdate(Resume resume, String uuid) {
        map.put(uuid, resume);
    }

    @Override
    protected void runSave(Resume resume, String uuid) {
        map.put(uuid, resume);
    }

    @Override
    protected Resume runGet(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean exist(String uuid) {
        return map.containsKey(uuid);
    }

    @Override
    protected String getSearchKey(String uuid) {
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
