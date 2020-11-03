package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();


    @Override
    protected void runDelete(Object index) {
        list.remove(((Integer) index).intValue());
    }

    @Override
    protected void runUpdate(Resume resume, Object index) {
        list.set((Integer) index, resume);
    }

    @Override
    protected void runSave(Resume resume, Object index) {
        list.add(resume);
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
    protected Integer getIndex(String uuid) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public int size() {
        return list.size();
    }

}
