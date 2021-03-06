/*
 * This file is generated by jOOQ.
 */
package net.stumpwiz.mrradb.model.tables;


import net.stumpwiz.mrradb.model.Keys;
import net.stumpwiz.mrradb.model.Raj;
import net.stumpwiz.mrradb.model.tables.records.OfficeRecord;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Office extends TableImpl<OfficeRecord>
{

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>raj.office</code>
     */
    public static final Office OFFICE = new Office();

    /**
     * The column <code>raj.office.officeid</code>.
     */
    public final TableField<OfficeRecord, Long> OFFICEID =
            createField(DSL.name("officeid"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");
    /**
     * The column <code>raj.office.title</code>.
     */
    public final TableField<OfficeRecord, String> TITLE =
            createField(DSL.name("title"), SQLDataType.VARCHAR(45), this, "");
    /**
     * The column <code>raj.office.officeprecedence</code>.
     */
    public final TableField<OfficeRecord, Double> OFFICEPRECEDENCE =
            createField(DSL.name("officeprecedence"), SQLDataType.DOUBLE, this, "");
    /**
     * The column <code>raj.office.officebodyid</code>.
     */
    public final TableField<OfficeRecord, Long> OFFICEBODYID =
            createField(DSL.name("officebodyid"), SQLDataType.BIGINT, this, "");
    private transient Body _body;

    /**
     * Create an aliased <code>raj.office</code> table reference
     */
    public Office(String alias)
    {
        this(DSL.name(alias), OFFICE);
    }

    private Office(Name alias, Table<OfficeRecord> aliased)
    {
        this(alias, aliased, null);
    }

    private Office(Name alias, Table<OfficeRecord> aliased, Field<?>[] parameters)
    {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>raj.office</code> table reference
     */
    public Office(Name alias)
    {
        this(alias, OFFICE);
    }

    /**
     * Create a <code>raj.office</code> table reference
     */
    public Office()
    {
        this(DSL.name("office"), null);
    }

    public <O extends Record> Office(Table<O> child, ForeignKey<O, OfficeRecord> key)
    {
        super(child, key, OFFICE);
    }

    /**
     * Get the implicit join path to the <code>raj.body</code> table.
     */
    public Body body()
    {
        if (_body == null)
            _body = new Body(this, Keys.OFFICE_BODY_FK);

        return _body;
    }

    @Override
    public Office as(String alias)
    {
        return new Office(DSL.name(alias), this);
    }

    @Override
    public Schema getSchema()
    {
        return aliased() ? null : Raj.RAJ;
    }

    @Override
    public Identity<OfficeRecord, Long> getIdentity()
    {
        return (Identity<OfficeRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<OfficeRecord> getPrimaryKey()
    {
        return Keys.KEY_OFFICE_PRIMARY;
    }

    @Override
    public List<ForeignKey<OfficeRecord, ?>> getReferences()
    {
        return Arrays.asList(Keys.OFFICE_BODY_FK);
    }

    @Override
    public Row4<Long, String, Double, Long> fieldsRow()
    {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Office as(Name alias)
    {
        return new Office(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Office rename(String name)
    {
        return new Office(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Office rename(Name name)
    {
        return new Office(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OfficeRecord> getRecordType()
    {
        return OfficeRecord.class;
    }
}
