package com.basejava.webapp;

import com.basejava.webapp.model.*;
import com.basejava.webapp.model.Organization.Position;

import java.time.LocalDate;
import java.util.Arrays;

public class ResumeTestData {
    private static final String UUID = "uuid1";
    private static final String FULLNAME = "name1";


    public static void main(String[] args) {
        System.out.println(fillStorage(UUID, FULLNAME));
    }

    public static Resume fillStorage(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "LinkedIn profile");
        resume.addContact(ContactType.GITHUB, "GitHub profile");
        resume.addContact(ContactType.STACKOVERFLOW, "StackOverFlow profile");
        resume.addContact(ContactType.HOMEPAGE, "Home Page");

        TextSection textSection1 = new TextSection("Ведущий стажировок и корпоративного обучения\" +\n" +
                "\"Java Web и Enterprise технологиям");
        TextSection textSection2 = new TextSection("Аналитический склад ума, сильная логика," +
                "креативность, инициативностью Пурист кода и архитектурыю");
        resume.addSection(SectionType.OBJECTIVE, textSection1);
        resume.addSection(SectionType.PERSONAL, textSection2);

        ListSection listSection1 = new ListSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение" +
                "проектов. Более 1000 выпускников.");
        ListSection listSection2 = new ListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        resume.addSection(SectionType.ACHIEVEMENT, listSection1);
        resume.addSection(SectionType.QUALIFICATION, listSection2);

        OrganizationSection organizationExperience = new OrganizationSection(
                new Organization(new Link("Java Online Project", "http://javaops.ru/"),
                        Arrays.asList(new Position(LocalDate.of(2013, 10, 01),
                                LocalDate.now(), "Автор проекта",
                                "Создание и проведение онлайн занятий и стажировок"))));
        new Organization(new Link("Wrike", "https://www.wrike.com/"),
                Arrays.asList(new Position(LocalDate.of(2014, 10, 01),
                        LocalDate.of(2016, 01, 01),
                        "Старший разработчик (backend)",
                        "Проектирование и разработка онлайн платформы управления проектами Wrike" +
                                " (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
                                " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")));

        resume.addSection(SectionType.EXPERIENCE, organizationExperience);

        OrganizationSection organizationEducation = new OrganizationSection(
                new Organization(new Link("Coursera", "https://www.coursera.org/"),
                        Arrays.asList(new Position(LocalDate.of(2013, 03, 01),
                                LocalDate.of(2013, 05, 01),
                                "\"Functional Programming Principles in Scala\" by Martin Odersky",
                                null))),
                new Organization(new Link("Luxoft", "https://www.luxoft-training.ru/"),
                        Arrays.asList(new Position(LocalDate.of(2011, 03, 01),
                                LocalDate.of(2011, 04, 01),
                                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                                null))));

        resume.addSection(SectionType.EDUCATION, organizationEducation);

        return resume;
    }
}

