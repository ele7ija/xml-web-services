package rs.ac.uns.ftn.tim5.apipoverenik.helper;

public class XQueryExpressions {


    //XQUERY I XUPDATE ZA ZALBU NA ODLUKU

    public static final String X_QUERY_FIND_ALL_ZALBE_NA_ODLUKU_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/apipoverenik/model/zalba_na_odluku\";\n" +
            "for $x in collection(\"/db/sample/zalbe_na_odluku\")\n" +
            "return $x";

    public static final String X_QUERY_FIND_ZALBA_NA_ODLUKU_BY_ID_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/apipoverenik/model/zalba_na_odluku\";\n" +
            "for $x in collection(\"/db/sample/zalbe_na_odluku\")/Zalba_na_odluku\n" +
            "where $x/id=%d\n" +
            "return $x";

    public static final String X_UPDATE_UPDATE_ZALBA_NA_ODLUKU_BY_ID_EXPRESSION = "xquery version \"3.1\";\n" +
            "xmldb:update(\"/db/sample/zalbe_na_odluku\",\n" +
            "    <xu:modifications version=\"1.0\"\n" +
            "    \txmlns:xu=\"http://www.xmldb.org/xupdate\"\n" +
            "    \txmlns=\"http://ftn.uns.ac.rs/tim5/apipoverenik/model/zalba_na_odluku\"\n" +
            "    \txmlns:ns2=\"http://ftn.uns.ac.rs/tim5/apipoverenik/model/util\">\n" +
            "    \t<xu:update select=\"doc('/db/sample/zalbe_na_odluku/%s')/Zalba_na_odluku\">\n" +
            "    \t%s" +
            "    \t</xu:update>\n" +
            "    </xu:modifications>)\n";

    public static final String X_UPDATE_REMOVE_ZALBA_NA_ODLUKU_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
            "xmldb:remove('/db/sample/zalbe_na_odluku', '%s')";



    //XQUERY I XUPDATE ZA ZALBU NA CUTANJE

    public static final String X_QUERY_FIND_ALL_ZALBE_CUTANJA_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/apipoverenik/model/zalbe_cutanja\";\n" +
            "for $x in collection(\"/db/sample/zalbe_cutanja\")\n" +
            "return $x";

    public static final String X_QUERY_FIND_ZALBA_CUTANJA_BY_ID_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/apipoverenik/model/zalbe_cutanja\";\n" +
            "for $x in collection(\"/db/sample/zalbe_cutanja\")/zalba_cutanja\n" +
            "where $x/id=%d\n" +
            "return $x";

    public static final String X_UPDATE_UPDATE_ZALBA_CUTANJA_BY_ID_EXPRESSION = "xquery version \"3.1\";\n" +
            "xmldb:update(\"/db/sample/zalbe_cutanja\",\n" +
            "    <xu:modifications version=\"1.0\"\n" +
            "    \txmlns:xu=\"http://www.xmldb.org/xupdate\"\n" +
            "    \txmlns=\"http://ftn.uns.ac.rs/tim5/apipoverenik/model/zalbe_cutanja\"\n" +
            "    \txmlns:ns2=\"http://ftn.uns.ac.rs/tim5/apipoverenik/model/util\">\n" +
            "    \t<xu:update select=\"doc('/db/sample/zalbe_cutanja/%s')/zalba_cutanja\">\n" +
            "    \t%s" +
            "    \t</xu:update>\n" +
            "    </xu:modifications>)\n";

    public static final String X_UPDATE_REMOVE_ZALBA_CUTANJA_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/zalbe_cutanja', '%s')";



    //XQUERY I XUPDATE ZA RESENJE

    public static final String X_QUERY_FIND_ALL_RESENJA_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/apipoverenik/model/resenje\";\n" +
            "for $x in collection(\"/db/sample/resenja\")\n" +
            "return $x";

    public static final String X_QUERY_FIND_RESENJE_BY_ID_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/apipoverenik/model/resenje\";\n" +
            "for $x in collection(\"/db/sample/resenja\")/Resenje\n" +
            "where $x/id=%d\n" +
            "return $x";

    public static final String X_UPDATE_UPDATE_RESENJE_BY_ID_EXPRESSION = "xquery version \"3.1\";\n" +
            "xmldb:update(\"/db/sample/resenja\",\n" +
            "    <xu:modifications version=\"1.0\"\n" +
            "    \txmlns:xu=\"http://www.xmldb.org/xupdate\"\n" +
            "    \txmlns=\"http://ftn.uns.ac.rs/tim5/apipoverenik/model/resenje\"\n" +
            "    \txmlns:ns2=\"http://ftn.uns.ac.rs/tim5/apipoverenik/model/util\">\n" +
            "    \t<xu:update select=\"doc('/db/sample/resenja/%s')/Resenje\">\n" +
            "    \t%s" +
            "    \t</xu:update>\n" +
            "    </xu:modifications>)\n";

    public static final String X_UPDATE_REMOVE_RESENJE_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/resenja', '%s')";


}
