/*
 * This file is generated by jOOQ.
 */
package net.stumpwiz.mrradb.model.tables.records;


import net.stumpwiz.mrradb.model.tables.Body;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * An organizational unit at Mercy Ridge.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class BodyRecord extends UpdatableRecordImpl<BodyRecord> implements Record5<Long, String, String, String, Double>
{

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached BodyRecord
     */
    public BodyRecord()
    {
        super(Body.BODY);
    }

    /**
     * Create a detached, initialised BodyRecord
     */
    public BodyRecord(Long bodyid, String bodyimage, String name, String mission, Double bodyprecedence)
    {
        super(Body.BODY);

        setBodyid(bodyid);
        setBodyimage(bodyimage);
        setName(name);
        setMission(mission);
        setBodyprecedence(bodyprecedence);
    }

    @Override
    public Record1<Long> key()
    {
        return (Record1) super.key();
    }

    @Override
    public Row5<Long, String, String, String, Double> fieldsRow()
    {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Long, String, String, String, Double> valuesRow()
    {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Long> field1()
    {
        return Body.BODY.BODYID;
    }

    @Override
    public Field<String> field2()
    {
        return Body.BODY.BODYIMAGE;
    }

    @Override
    public Field<String> field3()
    {
        return Body.BODY.NAME;
    }

    @Override
    public Field<String> field4()
    {
        return Body.BODY.MISSION;
    }

    @Override
    public Field<Double> field5()
    {
        return Body.BODY.BODYPRECEDENCE;
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Long value1()
    {
        return getBodyid();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public String value2()
    {
        return getBodyimage();
    }

    @Override
    public String value3()
    {
        return getName();
    }

    @Override
    public String value4()
    {
        return getMission();
    }

    @Override
    public Double value5()
    {
        return getBodyprecedence();
    }

    @Override
    public BodyRecord value1(Long value)
    {
        setBodyid(value);
        return this;
    }

    @Override
    public BodyRecord value2(String value)
    {
        setBodyimage(value);
        return this;
    }

    @Override
    public BodyRecord value3(String value)
    {
        setName(value);
        return this;
    }

    @Override
    public BodyRecord value4(String value)
    {
        setMission(value);
        return this;
    }

    @Override
    public BodyRecord value5(Double value)
    {
        setBodyprecedence(value);
        return this;
    }

    @Override
    public BodyRecord values(Long value1, String value2, String value3, String value4, Double value5)
    {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    @Override
    public Long component1()
    {
        return getBodyid();
    }

    /**
     * Getter for <code>raj.body.bodyid</code>.
     */
    public Long getBodyid()
    {
        return (Long) get(0);
    }

    /**
     * Setter for <code>raj.body.bodyid</code>.
     */
    public void setBodyid(Long value)
    {
        set(0, value);
    }

    @Override
    public String component2()
    {
        return getBodyimage();
    }

    /**
     * Getter for <code>raj.body.bodyimage</code>. Name of the graphic file that represents the body in reports and web
     * pages.
     */
    public String getBodyimage()
    {
        return (String) get(1);
    }

    /**
     * Setter for <code>raj.body.bodyimage</code>. Name of the graphic file that represents the body in reports and web
     * pages.
     */
    public void setBodyimage(String value)
    {
        set(1, value);
    }

    @Override
    public String component3()
    {
        return getName();
    }

    /**
     * Getter for <code>raj.body.name</code>.
     */
    public String getName()
    {
        return (String) get(2);
    }

    /**
     * Setter for <code>raj.body.name</code>.
     */
    public void setName(String value)
    {
        set(2, value);
    }

    @Override
    public String component4()
    {
        return getMission();
    }

    /**
     * Getter for <code>raj.body.mission</code>.
     */
    public String getMission()
    {
        return (String) get(3);
    }

    /**
     * Setter for <code>raj.body.mission</code>.
     */
    public void setMission(String value)
    {
        set(3, value);
    }

    @Override
    public Double component5()
    {
        return getBodyprecedence();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>raj.body.bodyprecedence</code>. Field for ordering in reports and web pages.  Stored as double
     * to allow insertions of new bodies.
     */
    public Double getBodyprecedence()
    {
        return (Double) get(4);
    }

    /**
     * Setter for <code>raj.body.bodyprecedence</code>. Field for ordering in
     * reports and web pages.  Stored as double to allow insertions of new
     * bodies.
     */
    public void setBodyprecedence(Double value)
    {
        set(4, value);
    }
}
