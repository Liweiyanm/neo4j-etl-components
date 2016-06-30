package org.neo4j.integration.provisioning.scripts;

import java.io.IOException;

import com.amazonaws.util.IOUtils;
import org.stringtemplate.v4.ST;

import org.neo4j.integration.mysql.MySqlClient;
import org.neo4j.integration.provisioning.Script;

public class MySqlScripts
{

    public static Script startupScript()
    {
        return createScript( "/scripts/mysql-startup.sh" );
    }

    public static Script bigPerformanceStartupScript()
    {
        return createScript( "/scripts/bigperformance-startup.sh" );
    }

    public static Script musicBrainzPerformanceStartupScript()
    {
        return createScript( "/scripts/musicbrainzperformance-startup.sh" );
    }

    public static Script setupDatabaseScript()
    {
        return createScript( "/scripts/setup-db.sql" );
    }

    public static Script northwindScript()
    {
        return createScript( "/scripts/northwind.sql" );
    }

    public static Script exclusionScript()
    {
        return createScript( "/scripts/exclusion.sql" );
    }

    private static Script createScript( String path )
    {
        return new Script()
        {
            @Override
            public String value() throws IOException
            {
                String script = IOUtils.toString( getClass().getResourceAsStream( path ) );

                ST template = new ST( script );

                for ( MySqlClient.Parameters parameter : MySqlClient.Parameters.values() )
                {
                    template.add( parameter.name(), parameter.value() );
                }

                return template.render();
            }
        };
    }

}
