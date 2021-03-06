/*
 * This file is generated by jOOQ.
 */
package net.stumpwiz.mrradb.model.tables;


import net.stumpwiz.mrradb.model.Keys;
import net.stumpwiz.mrradb.model.Raj;
import net.stumpwiz.mrradb.model.tables.records.PersonRecord;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * A resident, staff member, or any other individual serving in some office at Mercy Ridge.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Person extends TableImpl<PersonRecord>
{

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>raj.person</code>
     */
    public static final Person PERSON = new Person();

    /**
     * The column <code>raj.person.personid</code>.
     */
    public final TableField<PersonRecord, Long> PERSONID =
            createField(DSL.name("personid"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");
    /**
     * The column <code>raj.person.first</code>.
     */
    public final TableField<PersonRecord, String> FIRST =
            createField(DSL.name("first"), SQLDataType.VARCHAR(15), this, "");
    /**
     * The column <code>raj.person.last</code>.
     */
    public final TableField<PersonRecord, String> LAST =
            createField(DSL.name("last"), SQLDataType.VARCHAR(30), this, "");
    /**
     * The column <code>raj.person.email</code>.
     */
    public final TableField<PersonRecord, String> EMAIL =
            createField(DSL.name("email"), SQLDataType.VARCHAR(45), this, "");
    /**
     * The column <code>raj.person.phone</code>.
     */
    public final TableField<PersonRecord, String> PHONE =
            createField(DSL.name("phone"), SQLDataType.VARCHAR(19), this, "");
    /**
     * The column <code>raj.person.personimage</code>.
     */
    public final TableField<PersonRecord, String> PERSONIMAGE =
            createField(DSL.name("personimage"), SQLDataType.VARCHAR(45), this, "");

    /**
     * The column <code>raj.person.apt</code>.
     */
    public final TableField<PersonRecord, String> APT = createField(DSL.name("apt"), SQLDataType.CHAR(4), this, "");

    /**
     * Create an aliased <code>raj.person</code> table reference
     */
    public Person(String alias)
    {
        this(DSL.name(alias), PERSON);
    }

    private Person(Name alias, Table<PersonRecord> aliased)
    {
        this(alias, aliased, null);
    }

    private Person(Name alias, Table<PersonRecord> aliased, Field<?>[] parameters)
    {
        super(alias, null, aliased, parameters,
                DSL.comment("A resident, staff member, or any other individual serving in some office at Mercy Ridge."),
                TableOptions.table());
    }

    /**
     * Create an aliased <code>raj.person</code> table reference
     */
    public Person(Name alias)
    {
        this(alias, PERSON);
    }

    /**
     * Create a <code>raj.person</code> table reference
     */
    public Person()
    {
        this(DSL.name("person"), null);
    }

    public <O extends Record> Person(Table<O> child, ForeignKey<O, PersonRecord> key)
    {
        super(child, key, PERSON);
    }

    @Override
    public Person as(String alias)
    {
        return new Person(DSL.name(alias), this);
    }

    @Override
    public Schema getSchema()
    {
        return aliased() ? null : Raj.RAJ;
    }

    @Override
    public Identity<PersonRecord, Long> getIdentity()
    {
        return (Identity<PersonRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<PersonRecord> getPrimaryKey()
    {
        return Keys.KEY_PERSON_PRIMARY;
    }

    @Override
    public Row7<Long, String, String, String, String, String, String> fieldsRow()
    {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Person as(Name alias)
    {
        return new Person(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Person rename(String name)
    {
        return new Person(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Person rename(Name name)
    {
        return new Person(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PersonRecord> getRecordType()
    {
        return PersonRecord.class;
    }
}
