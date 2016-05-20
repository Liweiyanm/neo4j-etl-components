package org.neo4j.integration.sql.metadata;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class JoinKey
{
    private final Column sourceColumn;
    private final Column targetColumn;

    public JoinKey( Column sourceColumn, Column targetColumn )
    {
        this.sourceColumn = sourceColumn;
        this.targetColumn = targetColumn;
    }

    Column sourceColumn()
    {
        return sourceColumn;
    }

    Column targetColumn()
    {
        return targetColumn;
    }

    Join createJoinForCollocatedPrimaryKey( Column primaryKey )
    {
        return new Join( new JoinKey( primaryKey, primaryKey ), this );
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString( this );
    }
}
