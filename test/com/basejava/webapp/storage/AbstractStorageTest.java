package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.basejava.webapp.ResumeTestData.*;

public abstract class AbstractStorageTest {
    protected Storage storage;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeClass
    public static void openFillStorage() {
        fillStorage();
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
        List<Resume> expected = Arrays.asList(r1, r2, r3);
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