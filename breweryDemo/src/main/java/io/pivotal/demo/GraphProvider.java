package io.pivotal.demo;

import dagger.ObjectGraph;

public class GraphProvider {

    private static GraphProvider graphProvider;
    private ObjectGraph objectGraph;

    public static GraphProvider getInstance() {
        if (graphProvider == null) {
            graphProvider = new GraphProvider();
        }
        return graphProvider;
    }

    private GraphProvider() {
    }

    public void setupGraph(Object... modules) {
        objectGraph = ObjectGraph.create(modules);
    }

    public ObjectGraph getGraph() {
        return objectGraph;
    }
}
