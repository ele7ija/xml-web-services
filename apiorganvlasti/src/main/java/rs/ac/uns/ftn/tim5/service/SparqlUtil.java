package rs.ac.uns.ftn.tim5.service;

import org.springframework.stereotype.Service;

@Service
public class SparqlUtil {

	/* The following operation causes all of the triples in all of the graphs to be deleted */
	private final String DROP_ALL = "DROP ALL";
	
	/* Removes all of the triples from a named graphed */
	private final String DROP_GRAPH_TEMPLATE = "DROP GRAPH <%s>";

	/**
	 * A template for creating SPARUL (SPARQL Update) query can be found here:
	 * https://www.w3.org/TR/sparql11-update/
	 */
	/* Insert RDF data into the default graph */
	private final String UPDATE_TEMPLATE = "INSERT DATA { %s }";
	
	/* Insert RDF data to an arbitrary named graph */
	private final String UPDATE_TEMPLATE_NAMED_GRAPH = "INSERT DATA { GRAPH <%1$s> { %2$s } }";
	

	/* Simple SPARQL query on a named graph */
	private final String SELECT_NAMED_GRAPH_TEMPLATE = "SELECT * FROM <%1$s> WHERE { %2$s }";

	private final String SELECT_OBJECT_ONLY_NAMED_GRAPH_TEMPLATE = "SELECT ?o FROM <%1$s> WHERE { %2$s }";

	private final String SELECT_PREDICATE_AND_OBJECT_NAMED_GRAPH_TEMPLATE = "SELECT ?p ?o FROM <%1$s> WHERE { %2$s }";

	private static final String DESCRIBE_NAMED_GRAPH_TEMPLATE = "DESCRIBE <%1$s> FROM <%2$s> WHERE { %3$s }";
	
	/* Plain text RDF serialization format */
	public final String NTRIPLES = "N-TRIPLES";

	/* An XML serialization format for RDF data */
	public final String RDF_XML = "RDF/XML";
	
	public String dropAll() {
		return DROP_ALL;
	}
	
	public String dropGraph(String graphURI) {
		return String.format(DROP_GRAPH_TEMPLATE, graphURI);
	}
	
	/* Inserts data to the default graph */
	public String insertData(String ntriples) {
		return String.format(UPDATE_TEMPLATE, ntriples);
	}
	
	public String insertData(String graphURI, String ntriples) {
		return String.format(UPDATE_TEMPLATE_NAMED_GRAPH, graphURI, ntriples);
	}
	
	public String selectData(String graphURI, String sparqlCondition) {
		return String.format(SELECT_NAMED_GRAPH_TEMPLATE, graphURI, sparqlCondition);
	}

	public String selectObjectOnly(String graphURI, String sparqlCondition) {
		return String.format(SELECT_OBJECT_ONLY_NAMED_GRAPH_TEMPLATE, graphURI, sparqlCondition);
	}

	public String selectPredicateAndObject(String graphURI, String sparqlCondition) {
		return String.format(SELECT_PREDICATE_AND_OBJECT_NAMED_GRAPH_TEMPLATE, graphURI, sparqlCondition);
	}

	public String describe(String type, String graphURI, String sparqlCondition) {
		return String.format(DESCRIBE_NAMED_GRAPH_TEMPLATE, type, graphURI, sparqlCondition);
	}
}
