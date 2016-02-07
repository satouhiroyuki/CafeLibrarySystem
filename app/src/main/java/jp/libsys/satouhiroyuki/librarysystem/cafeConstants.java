package jp.libsys.satouhiroyuki.librarysystem;

/**
 * Created by sato_hiroyuki on 2016/02/07.
 */
public class cafeConstants {

    public final static int READ_URL_CODE = 1001;

    public final static String AMAZON_SEARCH_URL = "http://amazon.jp/dp/";

    //URL形式
    public static final String MATCH_URL =
            "^(https?|ftp)(:\\/\\/[-_.!~*\\'()a-zA-Z0-9;\\/?:\\@&=+\\$,%#]+)$";
    //半角数字のみ
    public static final String MATCH_NUMBER = "^[0-9]+$";
}
