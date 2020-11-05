package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{
    private  static Map<String, Resume> map = new HashMap<>();

    @Override
    protected void runDelete(Object index) {
        map.remove(((Resume) index).getUuid());
    }

    @Override
    protected void runUpdate(Resume resume, Object index) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void runSave(Resume resume, Object index) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume runGet(Object index) {
        return (Resume) index;
    }

    @Override
    protected boolean exist(Object index) {
        return index != null;
    }

    @Override
    protected Object getIndex(String uuid) {
        return map.get(uuid);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return map.size();
    }
}
