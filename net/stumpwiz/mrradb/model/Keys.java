/*
 * This file is generated by jOOQ.
 */
package net.stumpwiz.mrradb.model;


import net.stumpwiz.mrradb.model.tables.Body;
import net.stumpwiz.mrradb.model.tables.Office;
import net.stumpwiz.mrradb.model.tables.Person;
import net.stumpwiz.mrradb.model.tables.Term;
import net.stumpwiz.mrradb.model.tables.records.BodyRecord;
import net.stumpwiz.mrradb.model.tables.records.OfficeRecord;
import net.stumpwiz.mrradb.model.tables.records.PersonRecord;
import net.stumpwiz.mrradb.model.tables.records.TermRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in raj.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<BodyRecord> KEY_BODY_PRIMARY = Internal.createUniqueKey(Body.BODY, DSL.name("KEY_body_PRIMARY"), new TableField[] { Body.BODY.BODYID }, true);
    public static final UniqueKey<OfficeRecord> KEY_OFFICE_PRIMARY = Internal.createUniqueKey(Office.OFFICE, DSL.name("KEY_office_PRIMARY"), new TableField[] { Office.OFFICE.OFFICEID }, true);
    public static final UniqueKey<PersonRecord> KEY_PERSON_PRIMARY = Internal.createUniqueKey(Person.PERSON, DSL.name("KEY_person_PRIMARY"), new TableField[] { Person.PERSON.PERSONID }, true);
    public static final UniqueKey<TermRecord> KEY_TERM_PRIMARY = Internal.createUniqueKey(Term.TERM, DSL.name("KEY_term_PRIMARY"), new TableField[] { Term.TERM.TERMPERSONID, Term.TERM.TERMOFFICEID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<OfficeRecord, BodyRecord> OFFICE_BODY_FK = Internal.createForeignKey(Office.OFFICE, DSL.name("office_body_fk"), new TableField[] { Office.OFFICE.OFFICEBODYID }, Keys.KEY_BODY_PRIMARY, new TableField[] { Body.BODY.BODYID }, true);
    public static final ForeignKey<TermRecord, OfficeRecord> TERM_OFFICE_FK2 = Internal.createForeignKey(Term.TERM, DSL.name("term_office_fk2"), new TableField[] { Term.TERM.TERMOFFICEID }, Keys.KEY_OFFICE_PRIMARY, new TableField[] { Office.OFFICE.OFFICEID }, true);
    public static final ForeignKey<TermRecord, PersonRecord> TERM_PERSON_FK1 = Internal.createForeignKey(Term.TERM, DSL.name("term_person_fk1"), new TableField[] { Term.TERM.TERMPERSONID }, Keys.KEY_PERSON_PRIMARY, new TableField[] { Person.PERSON.PERSONID }, true);
}