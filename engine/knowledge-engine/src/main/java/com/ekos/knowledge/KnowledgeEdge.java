package com.ekos.knowledge;

public class KnowledgeEdge {

    private String source;

    private String target;

    private String relationship;

    public KnowledgeEdge() {
    }

    public KnowledgeEdge(String source,
                         String target,
                         String relationship) {

        this.source = source;
        this.target = target;
        this.relationship = relationship;

    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public String getRelationship() {
        return relationship;
    }

}