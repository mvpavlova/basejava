package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList();

    @Override
    protected void runDelete(Object index) {
        list.remove(index);
    }

    @Override
    protected void runUpdate(Resume resume, Object index) {
        list.set((Integer) index, resume);
    }

    @Override
    protected void runSave(Resume resume, Object index) {
        list.set((Integer) index, resume);
    }

    @Override
    protected Resume runGet(Object index) {
        return list.get((Integer) index);
    }

    @Override
    protected boolean exist(Object index) {
        return index != null;
    }

    @Override
    protected Object getIndex(String uuid) {
        return list.indexOf(uuid);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) list.toArray();
    }

    @Override
    public int size() {
        return list.size();
    }

}
