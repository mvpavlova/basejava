package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();


    @Override
    protected void runDelete(Object searchKey) {
        list.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected void runUpdate(Resume resume, Object searchKey) {
        list.set((Integer) searchKey, resume);
    }

    @Override
    protected void runSave(Resume resume, Object searchKey) {
        list.add(resume);
    }

    @Override
    protected Resume runGet(Object searchKey) {
        return list.get((Integer) searchKey);
    }

    @Override
    protected boolean exist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected List<Resume> runGetAll() {
        return new ArrayList<>(list);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

}
