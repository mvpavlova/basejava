package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                runDelete(file);
            }
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if (list == null) {
            throw new StorageException("Directory error", null);
        }
        return list.length;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void runUpdate(Resume resume, File file) {
        try {
            doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File update error", resume.getUuid(), e);
        }

    }

    @Override
    protected boolean exist(File file) {
        return file.exists();
    }

    @Override
    protected void runSave(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Create file error" + file.getAbsolutePath(), file.getName(), e);
        }
        runUpdate(resume, file);
    }

    @Override
    protected Resume runGet(File file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Read file error", file.getName(), e);
        }
    }

    @Override
    protected void runDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Error delete", file.getName());
        }
    }

    @Override
    protected List<Resume> runGetAll() {
        File[] files = directory.listFiles();
        if(files == null) {
            throw new StorageException("Get all storage error", null);
        }
        List<Resume> list = new ArrayList<>(files.length);
        for(File file: files) {
            list.add(runGet(file));
        }
        return list;
    }

    protected abstract void doWrite(Resume resume, OutputStream os) throws IOException;
    protected abstract Resume doRead(InputStream is) throws IOException;
}
