package org.neo4j.etl.neo4j;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Statement
{
    @JsonProperty("statement")
    public String value;

    public Statement( String value )
    {
        this.value = value;
    }
}
