package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected void saveElement(Resume resume, int index) {
        int newIndex = - index - 1;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, size - newIndex );
        storage[newIndex] = resume;
    }



    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
