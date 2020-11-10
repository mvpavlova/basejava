package com.basejava.webapp;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MainCollections {

    private static final String UUID_1 = "uuid1";
    private static Resume r1 = new Resume(UUID_1, "Name1");
    private static final String UUID_2 = "uuid2";
    private static Resume r2 = new Resume(UUID_2, "Name2");
    private static final String UUID_3 = "uuid3";
    private static Resume r3 = new Resume(UUID_3, "Name3");
    private static final String UUID_4 = "uuid4";
    private static Resume r4 = new Resume(UUID_4, "Name4");

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(r1);
        collection.add(r2);
        collection.add(r3);

        for (Resume resume : collection) {
            System.out.println(resume);
            if (Objects.equals(resume.getUuid(), UUID_1)) {
//                collection.remove(resume);
            }

        }

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            System.out.println(resume);
            if (Objects.equals(resume.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }

        System.out.println(collection.toString());

        Map<String, Resume> map = new HashMap<String, Resume>() {
            {
                put(UUID_1, r1);
                put(UUID_2, r2);
                put(UUID_3, r3);
            }
        };
        for (String uuid: map.keySet()) {
            System.out.println(map.get(uuid));
        }
        for (Map.Entry<String, Resume> entry: map.entrySet()) {
            System.out.println(entry.getValue());
        }

        List<Resume> resumes = Arrays.asList(r1, r2, r3);
        resumes.remove(1);
        System.out.println(resumes);
    }
}
