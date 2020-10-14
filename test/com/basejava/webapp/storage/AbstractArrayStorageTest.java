package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
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

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void save() throws Exception  {
        String UUID4 = "uuid4";
        Resume R4 = new Resume(UUID4);
        storage.save(R4);
        arraySize(4);
    }

    @Test (expected = ExistStorageException.class)
    public void alreadyInStorage() throws Exception  {
        String UUID4 = "uuid3";
        Resume R4 = new Resume(UUID4);
        storage.save(R4);
    }

    @Test(expected = StorageException.class)
    public void fullArray() throws Exception  {
        for(int i = 3; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
    }

    @Test
    public void size() throws Exception {
        arraySize(3);
    }

    @Test
    public void getAll() throws Exception  {
        Resume [] actualResumes = storage.getAll();
        Assert.assertEquals(R1, actualResumes[0]);
        Assert.assertEquals(R2, actualResumes[1]);
        Assert.assertEquals(R3, actualResumes[2]);
    }

    @Test
    public void get() throws Exception  {
        arrayGet(R1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception  {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception  {
        storage.delete(UUID_1);
        arraySize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception  {
        String UUID4 = "uuid4";
        Resume R4 = new Resume(UUID4);
        storage.delete(UUID4);
    }

    @Test
    public void clear() throws Exception  {
        storage.clear();
        arraySize(0);
    }

    @Test
    public void update() throws Exception  {
        Resume newResume = new Resume(UUID_1);
        storage.save(newResume);
        Assert.assertTrue(newResume == storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception  {
        String UUID4 = "uuid4";
        Resume R4 = new Resume(UUID4);
        storage.update(R4);
    }

    private void arrayGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
    private void arraySize(int size) {
        Assert.assertEquals(size, storage.size());
    }
    private void arrayResume(String uuid) {

    }

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }
}