package com.basejava.webapp.storage;

import com.basejava.webapp.ResumeTestData;
import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected Storage storage;

    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";
    public static final String UUID_4 = "uuid4";

    public static final Resume r1 = ResumeTestData.fillStorage(UUID_1, "Григорий Кислин");
    public static final Resume r2 = ResumeTestData.fillStorage(UUID_2, "Name2");
    public static final Resume r3 = ResumeTestData.fillStorage(UUID_3, "Name3");
    public static final Resume r4 = ResumeTestData.fillStorage(UUID_4, "Name4");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }


    @Test
    public void save() throws Exception {
        storage.save(r4);
        arraySize(4);
        storage.get("uuid4");
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() throws Exception {
        storage.save(r3);
    }

    @Test
    public void size() throws Exception {
        arraySize(3);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> expected = Arrays.asList(r2, r3, r1);
        Assert.assertEquals(3, expected.size());
        Assert.assertEquals(expected, storage.getAllSorted());
    }

    @Test
    public void get() throws Exception {
        arrayGet(r1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        arraySize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        arraySize(0);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        Assert.assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(r4);
    }

    private void arrayGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void arraySize(int size) {
        Assert.assertEquals(size, storage.size());
    }
}