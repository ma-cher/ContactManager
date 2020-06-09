package net.codejava.contact.dao;

import net.codejava.contact.model.Contact;

import java.util.List;

public interface ContactDAO {
    public abstract int save(Contact contact);

    public abstract int update(Contact contact);

    public abstract Contact get(Integer id);

    public abstract int delete(Integer id);

    public abstract List<Contact> list();
}
