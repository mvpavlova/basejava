package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private Resume R1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private Resume R2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private Resume R3 = new Resume(UUID_3);


    private void arraySize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void size() throws Exception {
        arraySize(3);
    }

    @Test
    public void getAll() throws Exception  {
        Resume [] array = storage.getAll();
        Assert.assertEquals(R1, array[0]);
        Assert.assertEquals(R2, array[1]);
        Assert.assertEquals(R3, array[2]);
    }

    @Test
    public void get() throws Exception  {
        storage.get(UUID_1);
    }

    @Test
    public void getNotExist() throws Exception  {
        storage.get("dummy");
    }

    @Test
    public void delete() throws Exception  {
        storage.delete(UUID_1);
        arraySize(2);
        storage.get(UUID_1);
    }

    @Test
    public void clear() throws Exception  {
        storage.clear();
        arraySize(0);
    }

    @Test
    public void update() throws Exception  {
        storage.update(R1);
        Assert.assertEquals(R1, "uuid2");
    }

    @Test
    public void save() throws Exception  {
        String UUID4 = "uuid4";
        Resume R4 = new Resume(UUID4);
        storage.save(R4);
        arraySize(4);
    }
}