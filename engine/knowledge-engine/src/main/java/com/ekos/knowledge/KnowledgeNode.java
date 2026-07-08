package com.ekos.knowledge;

public class KnowledgeNode {

    private String id;

    private String type;

    private String label;

    public KnowledgeNode() {
    }

    public KnowledgeNode(String id,
                         String type,
                         String label) {
        this.id = id;
        this.type = type;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

}