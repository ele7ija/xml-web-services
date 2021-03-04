package rs.ac.uns.ftn.tim5.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rdfdb.conn")
public class RDFDBConnectionProperties {

    private String endpoint;
    private String dataset;

    private String query;
    private String update;
    private String data;

    public String getQueryEndpoint() {
        return String.join("/", endpoint, dataset, query.trim());
    }

    public String getUpdateEndpoint() {
        return String.join("/", endpoint, dataset, update.trim());
    }

    public String getDataEndpoint() {
        return String.join("/", endpoint, dataset, data.trim());
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDataset() {
        return this.dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUpdate() {
        return this.update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public RDFDBConnectionProperties endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public RDFDBConnectionProperties dataset(String dataset) {
        this.dataset = dataset;
        return this;
    }

    public RDFDBConnectionProperties query(String query) {
        this.query = query;
        return this;
    }

    public RDFDBConnectionProperties update(String update) {
        this.update = update;
        return this;
    }

    public RDFDBConnectionProperties data(String data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " endpoint='" + getEndpoint() + "'" +
                ", dataset='" + getDataset() + "'" +
                ", query='" + getQuery() + "'" +
                ", update='" + getUpdate() + "'" +
                ", data='" + getData() + "'" +
                "}";
    }

}
