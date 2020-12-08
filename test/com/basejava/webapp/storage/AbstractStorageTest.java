package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static Resume r1;
    private static Resume r2;
    private static Resume r3;
    private static Resume r4;

    static {
        r1 = new Resume(UUID_1, "Name1");
        r2 = new Resume(UUID_2, "Name2");
        r3 = new Resume(UUID_3, "Name3");
        r4 = new Resume(UUID_4, "Name4");
        r1.putSection(SectionType.OBJECTIVE, new TextSection("Obj1"));
        r1.putSection(SectionType.PERSONAL, new TextSection("Per1"));
        r1.putSection(SectionType.ACHIEVEMENT, new ListSection("Ach1","Ach2"));
        r1.putSection(SectionType.QUALIFICATION, new ListSection("qua1", "qua2"));
        r1.putSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Org1", "http://Org1.ru",
                                new Organization.Position(2000, Month.APRIL, "pos1", "cont1"),
                                new Organization.Position(2005, Month.AUGUST,"pos2", "con2"))));
        r1.putSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("inst", null,
                                new Organization.Position(1995,Month.AUGUST, "pos.inst1", "cont.inst1")),
                        new Organization("inst2", null,
                                new Organization.Position(1996, Month.AUGUST, "pos.inst2", "cont.inst2"))));


    }

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