package rs.ac.uns.ftn.tim5.helper;

public class XQueryExpressions {


    //XQUERY I XUPDATE ZA ZALBU NA ODLUKU
    public static final String X_QUERY_FIND_ALL_ZALBE_NA_ODLUKU_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/zalba_na_odluku\";\n" +
            "for $x in collection(\"/db/sample/zalbe_na_odluku\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_ZALBA_NA_ODLUKU_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
            "xmldb:remove('/db/sample/zalbe_na_odluku', '%s')";



    //XQUERY I XUPDATE ZA ZALBU NA CUTANJE

    public static final String X_QUERY_FIND_ALL_ZALBE_CUTANJA_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/zalba_cutanja\";\n" +
            "for $x in collection(\"/db/sample/zalba_cutanja\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_ZALBA_CUTANJA_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/zalba_cutanja', '%s')";


    //XQUERY I XUPDATE ZA RESENJE

    public static final String X_QUERY_FIND_ALL_POVERENICI_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/poverenik\";\n" +
            "for $x in collection(\"/db/sample/poverenik\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_POVERENIK_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/poverenik', '%s')";


    //XQUERY I XUPDATE ZA RESENJE

    public static final String X_QUERY_FIND_ALL_RESENJA_EXPRESSION = "xquery version \"3.1\";\n" +
            "declare default element namespace \"http://ftn.uns.ac.rs/tim5/model/resenje\";\n" +
            "for $x in collection(\"/db/sample/resenja\")\n" +
            "return $x";

    public static final String X_UPDATE_REMOVE_RESENJE_BY_ID_EXPRESSION =
            "xquery version \"3.1\";\n" +
                    "xmldb:remove('/db/sample/resenja', '%s')";


}