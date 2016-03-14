package org.neo4j.integration.neo4j.importcsv.config;

import java.nio.file.Path;
import java.util.Collection;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.neo4j.integration.process.Commands;
import org.neo4j.integration.process.CommandsSupplier;
import org.neo4j.integration.util.Preconditions;

import static java.lang.String.format;

import static org.neo4j.integration.util.StringListBuilder.stringList;

public class NodeConfig implements CommandsSupplier
{
    public static Builder.SetInputFiles builder()
    {
        return new NodeConfigBuilder();
    }

    private final Collection<Path> files;
    private final Collection<String> labels;

    NodeConfig( NodeConfigBuilder builder )
    {
        this.files = Preconditions.requireNonEmptyCollection( builder.files, "Files" );
        this.labels = builder.labels;
    }

    @Override
    public void addCommandsTo( Commands.Builder.SetCommands commands )
    {
        commands.addCommand( labels.isEmpty() ? "--nodes" : format( "--nodes:%s", stringList( labels, ":" ) ) );
        commands.addCommand( format( "%s", stringList( files, ",", item -> item.toAbsolutePath().toString() ) ) );
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals( Object o )
    {
        return EqualsBuilder.reflectionEquals( this, o );
    }

    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode( this );
    }

    public interface Builder
    {
        interface SetInputFiles
        {
            Builder addInputFile( Path file );

            Builder addInputFiles( Collection<Path> files );
        }

        Builder addInputFile( Path file );

        Builder addInputFiles( Collection<Path> files );

        Builder addLabel( String label );

        NodeConfig build();
    }
}
