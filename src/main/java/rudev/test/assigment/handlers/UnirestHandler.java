package rudev.test.assigment.handlers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.HttpHost;
import rudev.test.assigment.utilitys.MyLogger;
import rudev.test.assigment.utilitys.MyProperties;

import java.util.StringJoiner;

/**
 * Class for work with Unirest
 */
public class UnirestHandler
{
    private static String ip = "";
    private static int port = 0;
    private static String userAgent = "";
    private static String mainUrl = "";
    private static String requestUrl = "";

    /**
     * Setup Unirest and class fields for future requests
     */
    public static void SetupUnirest()
    {
        UnirestHandler.ip = MyProperties.PROPERTY.getProperty ( "proxyip" );
        UnirestHandler.port = Integer.parseInt ( MyProperties.PROPERTY.getProperty ( "proxyport" ) );
        UnirestHandler.userAgent = MyProperties.PROPERTY.getProperty ( "useragent" );
        UnirestHandler.mainUrl = MyProperties.PROPERTY.getProperty ( "mainurl" );
        UnirestHandler.requestUrl = MyProperties.PROPERTY.getProperty ( "requesturl" );

        Unirest.setProxy ( new HttpHost ( UnirestHandler.ip , port ) );
        Unirest.setDefaultHeader ( "User-Agent" , userAgent );
    }

    /**
     * Running GET request
     *
     * @param limit - limit of goods
     * @param offset - offset for request
     * @param postback - postback needed to query execution
     * @return JSON String or empty String
     * @throws UnirestException - UnirestException exception
     */
    public static String GetResponse(int limit , int offset , String postback) throws UnirestException
    {
        HttpResponse <JsonNode> response = Unirest.
                get ( UnirestHandler.requestUrl )
                .header ( "DNT" , "1" )
                .header ( "Referer" , UnirestHandler.mainUrl )
                .header ( "Sec-Fetch-Mode" , "no-cors" )
                .queryString ( "widget_id" , "5547572" )
                .queryString ( "platform" , "pc" )
                .queryString ( "limit" , limit )
                .queryString ( "offset" , offset )
                .queryString ( "phase" , "1" )
                .queryString ( "postback" , postback )
                .asJson ();

        System.out.println ( UnirestHandler.ResponseToString ( response ) );
        MyLogger.LOGGER.info ( UnirestHandler.ResponseToString ( response ) );

        if (response.getStatus () == 200)
            return response.getBody ().toString ();
        else return "";
    }

    /**
     * Create string from Response
     *
     * @param response - response from which it is created
     * @return Created String or empty String
     */
    private static String ResponseToString(HttpResponse response)
    {
        if(response != null)
            return new StringJoiner ( "\n" , "\n" , "\n" )
                    .add ( "Response:" )
                    .add ( "Headers: " + response.getHeaders () )
                    .add ( "Status: " + response.getStatus () )
                    .add ( "Body : " + response.getBody () )
                    .toString ();
        return "";
    }
}
