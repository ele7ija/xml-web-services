package rs.ac.uns.ftn.tim5.apiorganvlasti.helper;

public class XQueryExpressions {
    public static final String X_QUERY_FIND_ALL_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/apiorganvlasti/model/zahtev\";\n" +
            "for $x in collection(\"/db/sample/zahtevi\")\n" +
            "return $x";

    public static final String X_QUERY_FIND_BY_ID_ZAHTEVA_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/apiorganvlasti/model/zahtev\";\n" +
            "for $x in collection(\"/db/sample/zahtevi\")/Zahtev\n" +
            "where $x/id=%d\n" +
            "return $x";

    public static final String X_UPDATE_UPDATE_BY_ID_ZAHTEVA_EXPRESSION = "xquery version \"3.1\";\n" +
            "xmldb:update(\"/db/sample/zahtevi\",\n" +
            "    <xu:modifications version=\"1.0\"\n" +
            "    \txmlns:xu=\"http://www.xmldb.org/xupdate\"\n" +
            "    \txmlns:ns2=\"http://ftn.uns.ac.rs/tim5/apiorganvlasti/model/util\">\n" +
            "    \t<xu:update select=\"doc('/db/sample/zahtevi/%s')/Zahtev\">\n" +
            "    \t%s" +
            "    \t</xu:update>\n" +
            "    </xu:modifications>)\n";

    public static final String X_UPDATE_REMOVE_BY_ID_ZAHTEVA_EXPRESSION =
            "xquery version \"3.1\";\n" +
            "xmldb:remove('/db/sample/zahtevi', '%s')";
}
