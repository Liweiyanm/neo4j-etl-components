package org.neo4j.integration.commands.mysql;

public interface GenerateMetadataMappingEvents
{
    GenerateMetadataMappingEvents EMPTY = new GenerateMetadataMappingEvents()
    {
        @Override
        public void onGeneratingMetadataMapping()
        {
            // Do nothing
        }

        @Override
        public void onMetadataMappingGenerated()
        {
            // Do nothing
        }
    };

    void onGeneratingMetadataMapping();

    void onMetadataMappingGenerated();
}
