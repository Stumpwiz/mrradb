/*
 * This file is generated by jOOQ.
 */
package net.stumpwiz.mrradb.model.tables.records;


import java.time.LocalDate;

import net.stumpwiz.mrradb.model.tables.Term;

import org.jooq.Record2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class TermRecord extends UpdatableRecordImpl<TermRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>raj.term.start</code>.
     */
    public void setStart(LocalDate value) {
        set(0, value);
    }

    /**
     * Getter for <code>raj.term.start</code>.
     */
    public LocalDate getStart() {
        return (LocalDate) get(0);
    }

    /**
     * Setter for <code>raj.term.end</code>.
     */
    public void setEnd(LocalDate value) {
        set(1, value);
    }

    /**
     * Getter for <code>raj.term.end</code>.
     */
    public LocalDate getEnd() {
        return (LocalDate) get(1);
    }

    /**
     * Setter for <code>raj.term.ordinal</code>.
     */
    public void setOrdinal(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>raj.term.ordinal</code>.
     */
    public String getOrdinal() {
        return (String) get(2);
    }

    /**
     * Setter for <code>raj.term.termpersonid</code>.
     */
    public void setTermpersonid(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>raj.term.termpersonid</code>.
     */
    public Long getTermpersonid() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>raj.term.termofficeid</code>.
     */
    public void setTermofficeid(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>raj.term.termofficeid</code>.
     */
    public Long getTermofficeid() {
        return (Long) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Long, Long> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TermRecord
     */
    public TermRecord() {
        super(Term.TERM);
    }

    /**
     * Create a detached, initialised TermRecord
     */
    public TermRecord(LocalDate start, LocalDate end, String ordinal, Long termpersonid, Long termofficeid) {
        super(Term.TERM);

        setStart(start);
        setEnd(end);
        setOrdinal(ordinal);
        setTermpersonid(termpersonid);
        setTermofficeid(termofficeid);
        resetChangedOnNotNull();
    }
}