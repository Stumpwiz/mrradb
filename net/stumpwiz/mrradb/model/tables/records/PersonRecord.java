/*
 * This file is generated by jOOQ.
 */
package net.stumpwiz.mrradb.model.tables.records;


import net.stumpwiz.mrradb.model.tables.Person;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 *         
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class PersonRecord extends UpdatableRecordImpl<PersonRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>raj.person.personid</code>.
     */
    public void setPersonid(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>raj.person.personid</code>.
     */
    public Long getPersonid() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>raj.person.first</code>.
     */
    public void setFirst(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>raj.person.first</code>.
     */
    public String getFirst() {
        return (String) get(1);
    }

    /**
     * Setter for <code>raj.person.last</code>.
     */
    public void setLast(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>raj.person.last</code>.
     */
    public String getLast() {
        return (String) get(2);
    }

    /**
     * Setter for <code>raj.person.email</code>.
     */
    public void setEmail(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>raj.person.email</code>.
     */
    public String getEmail() {
        return (String) get(3);
    }

    /**
     * Setter for <code>raj.person.phone</code>.
     */
    public void setPhone(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>raj.person.phone</code>.
     */
    public String getPhone() {
        return (String) get(4);
    }

    /**
     * Setter for <code>raj.person.apt</code>.
     */
    public void setApt(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>raj.person.apt</code>.
     */
    public String getApt() {
        return (String) get(5);
    }

    /**
     * Setter for <code>raj.person.personimage</code>.
     */
    public void setPersonimage(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>raj.person.personimage</code>.
     */
    public String getPersonimage() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PersonRecord
     */
    public PersonRecord() {
        super(Person.PERSON);
    }

    /**
     * Create a detached, initialised PersonRecord
     */
    public PersonRecord(Long personid, String first, String last, String email, String phone, String apt, String personimage) {
        super(Person.PERSON);

        setPersonid(personid);
        setFirst(first);
        setLast(last);
        setEmail(email);
        setPhone(phone);
        setApt(apt);
        setPersonimage(personimage);
        resetChangedOnNotNull();
    }
}