/*
 * This file is generated by jOOQ.
 */
package net.stumpwiz.mrradb.model.tables;


import java.util.Collection;

import net.stumpwiz.mrradb.model.Keys;
import net.stumpwiz.mrradb.model.Raj;
import net.stumpwiz.mrradb.model.tables.Office.OfficePath;
import net.stumpwiz.mrradb.model.tables.records.BodyRecord;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * An organizational unit at Mercy Ridge.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Body extends TableImpl<BodyRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>raj.body</code>
     */
    public static final Body BODY = new Body();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BodyRecord> getRecordType() {
        return BodyRecord.class;
    }

    /**
     * The column <code>raj.body.bodyid</code>.
     */
    public final TableField<BodyRecord, Long> BODYID = createField(DSL.name("bodyid"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>raj.body.bodyimage</code>. Name of the graphic file that
     * represents the body in reports and web pages.
     */
    public final TableField<BodyRecord, String> BODYIMAGE = createField(DSL.name("bodyimage"), SQLDataType.VARCHAR(45), this, "Name of the graphic file that represents the body in reports and web pages.");

    /**
     * The column <code>raj.body.name</code>.
     */
    public final TableField<BodyRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(45).nullable(false), this, "");

    /**
     * The column <code>raj.body.mission</code>.
     */
    public final TableField<BodyRecord, String> MISSION = createField(DSL.name("mission"), SQLDataType.VARCHAR(512), this, "");

    /**
     * The column <code>raj.body.bodyprecedence</code>. Field for ordering in
     * reports and web pages.  Stored as double to allow insertions of new
     * bodies.
     */
    public final TableField<BodyRecord, Double> BODYPRECEDENCE = createField(DSL.name("bodyprecedence"), SQLDataType.DOUBLE.nullable(false), this, "Field for ordering in reports and web pages.  Stored as double to allow insertions of new bodies.");

    private Body(Name alias, Table<BodyRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Body(Name alias, Table<BodyRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment("An organizational unit at Mercy Ridge."), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>raj.body</code> table reference
     */
    public Body(String alias) {
        this(DSL.name(alias), BODY);
    }

    /**
     * Create an aliased <code>raj.body</code> table reference
     */
    public Body(Name alias) {
        this(alias, BODY);
    }

    /**
     * Create a <code>raj.body</code> table reference
     */
    public Body() {
        this(DSL.name("body"), null);
    }

    public <O extends Record> Body(Table<O> path, ForeignKey<O, BodyRecord> childPath, InverseForeignKey<O, BodyRecord> parentPath) {
        super(path, childPath, parentPath, BODY);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class BodyPath extends Body implements Path<BodyRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> BodyPath(Table<O> path, ForeignKey<O, BodyRecord> childPath, InverseForeignKey<O, BodyRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private BodyPath(Name alias, Table<BodyRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public BodyPath as(String alias) {
            return new BodyPath(DSL.name(alias), this);
        }

        @Override
        public BodyPath as(Name alias) {
            return new BodyPath(alias, this);
        }

        @Override
        public BodyPath as(Table<?> alias) {
            return new BodyPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Raj.RAJ;
    }

    @Override
    public Identity<BodyRecord, Long> getIdentity() {
        return (Identity<BodyRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<BodyRecord> getPrimaryKey() {
        return Keys.KEY_BODY_PRIMARY;
    }

    private transient OfficePath _office;

    /**
     * Get the implicit to-many join path to the <code>raj.office</code> table
     */
    public OfficePath office() {
        if (_office == null)
            _office = new OfficePath(this, null, Keys.OFFICE_BODY_FK.getInverseKey());

        return _office;
    }

    @Override
    public Body as(String alias) {
        return new Body(DSL.name(alias), this);
    }

    @Override
    public Body as(Name alias) {
        return new Body(alias, this);
    }

    @Override
    public Body as(Table<?> alias) {
        return new Body(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Body rename(String name) {
        return new Body(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Body rename(Name name) {
        return new Body(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Body rename(Table<?> name) {
        return new Body(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Body where(Condition condition) {
        return new Body(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Body where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Body where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Body where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Body where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Body where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Body where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Body where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Body whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Body whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
