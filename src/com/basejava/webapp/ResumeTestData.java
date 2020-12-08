package com.basejava.webapp;

import com.basejava.webapp.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Grigoriy Kislin");

        Map<ContactType, String> contacts = new EnumMap<ContactType, String>(ContactType.class) {{
            put(ContactType.PHONE, "+7(921) 855-0482");
            put(ContactType.SKYPE, "grigory.kislin");
            put(ContactType.MAIL, "gkislin@yandex.ru");
            put(ContactType.LINKEDIN, "LinkedIn profile");
            put(ContactType.GITHUB, "GitHub profile");
            put(ContactType.STACKOVERFLOW, "StackOverFlow profile");
            put(ContactType.HOMEPAGE, "Home Page");
        }};

        TextSection objective = new TextSection("Ведущий стажировок и корпоративного обучения" +
                "Java Web и Enterprise технологиям");

        TextSection personal = new TextSection("Аналитический склад ума, сильная логика," +
                "креативность, инициативностью Пурист кода и архитектурыю");

        AbstractSection achievements = new ListSection<>(Arrays.asList(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                        "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                        "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение" +
                        "проектов. Более 1000 выпускников."));

        AbstractSection qualifications = new ListSection<>(Arrays.asList(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"));

        OrganizationSection experience = new OrganizationSection(Arrays.asList(
                new Organization(new Link("Java Online Project", "http://javaops.ru/"),
                        Arrays.asList(new Organization.Position(LocalDate.of(2013, 10, 01),
                                null, "Автор проекта",
                                "Создание и проведение онлайн занятий и стажировок"))),
                new Organization(new Link("Wrike", "https://www.wrike.com/"),
                        Arrays.asList(new Organization.Position(LocalDate.of(2014, 10, 01),
                                LocalDate.of(2016, 01, 01),
                                "Старший разработчик (backend)",
                                "Проектирование и разработка онлайн платформы управления проектами Wrike" +
                                        " (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis)." +
                                        " Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")))));

        OrganizationSection educations = new OrganizationSection(Arrays.asList(
                new Organization(new Link("Coursera", "https://www.coursera.org/"),
                        Arrays.asList(new Organization.Position(LocalDate.of(2013, 03, 01),
                                LocalDate.of(2013, 05, 01),
                                "\"Functional Programming Principles in Scala\" by Martin Odersky",
                                null))),
                new Organization(new Link("Luxoft", "https://www.luxoft-training.ru/"),
                        Arrays.asList(new Organization.Position(LocalDate.of(2011, 03, 01),
                                LocalDate.of(2011, 04, 01),
                                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                                null)))));

        Map<SectionType, AbstractSection> sections = new EnumMap<SectionType, AbstractSection>(SectionType.class) {{
            put(SectionType.OBJECTIVE, objective);
            put(SectionType.PERSONAL, personal);
            put(SectionType.ACHIEVEMENT, achievements);
            put(SectionType.QUALIFICATION, qualifications);
            put(SectionType.EXPERIENCE, experience);
            put(SectionType.EDUCATION, educations);
        }};

        resume.setContacts(contacts);
        resume.setSections(sections);

        System.out.println(resume.toString());
    }
}

