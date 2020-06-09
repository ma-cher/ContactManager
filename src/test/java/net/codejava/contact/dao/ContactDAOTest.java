package net.codejava.contact.dao;

import net.codejava.contact.model.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactDAOTest {

    /* задание настроек источника данных  помощью jdbc драйвера.
    использование jdbc драйвера для конфигурации компонента источника данных
    класс источник данных из спринг.

    DriverManagerDataSource всегда создает новое соединение для каждого запроса соединения
    * */
    private DriverManagerDataSource dataSource;
    private ContactDAO dao;

    /* С помощью этой аннотации @BeforeEach мы проинициализируем все поля
    до вызова каждого метода
    * */
    @BeforeEach
    void setupBeforeEach() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/contactdb");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        dao = new ContactDAOImpl(dataSource);
    }

    @org.junit.jupiter.api.Test
    void save() {
        Contact contact = new Contact("Bill Gates",
                "bill@windows.com",
                "LA",
                "8593837585038");

        int result = dao.save(contact);

        assertTrue(result > 0);
    }

    @org.junit.jupiter.api.Test
    void update() {
        Contact contact = new Contact(5,"Bill Gates",
                "bill@microsoft.com",
                "NY",
                "8593837585038");


        int result = dao.update(contact);
        assertTrue(result > 0);
    }

    @org.junit.jupiter.api.Test
    void get() {
        Integer id = 1;
        Contact contact = dao.get(id);
        if (contact != null) {
            System.out.println(contact);
        }
        assertNotNull(contact);
    }

    @org.junit.jupiter.api.Test
    void delete() {
        Integer id = 5;

        int result = dao.delete(id);

        assertTrue(result > 0);
    }

    @org.junit.jupiter.api.Test
    void list() {
        List<Contact> contactList = dao.list();
        for (Contact c : contactList) {
            System.out.println(c);
        }
        assertTrue(!contactList.isEmpty());
    }
}