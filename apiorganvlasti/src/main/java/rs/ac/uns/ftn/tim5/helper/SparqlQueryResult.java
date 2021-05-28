package rs.ac.uns.ftn.tim5.helper;

import org.apache.jena.rdf.model.RDFNode;

public class SparqlQueryResult {

    private String varName;

    private RDFNode varValue;

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public RDFNode getVarValue() {
        return varValue;
    }

    public void setVarValue(RDFNode varValue) {
        this.varValue = varValue;
    }
}
