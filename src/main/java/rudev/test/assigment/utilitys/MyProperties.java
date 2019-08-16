package rudev.test.assigment.utilitys;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Class for property instance
 */
public class MyProperties
{
    public static Properties PROPERTY = new Properties ();

    /**
     * Read properties file
     *
     * @throws Exception - Exception
     */
    public static void ReadProperties() throws Exception
    {
        InputStream input = new FileInputStream ( "src/main/resources/config.properties" );

        PROPERTY.load ( input );

        input.close ();

        if (CheckPropertires ()) {
            System.out.println ( MyProperties.MyPropertiesToString () );
            MyLogger.LOGGER.info ( MyProperties.MyPropertiesToString () );
        }
    }

    /**
     * Check properties file
     *
     * @return true if all ok or throw exception
     * @throws Exception - Exception
     */
    private static boolean CheckPropertires() throws Exception
    {
        if (PROPERTY.getProperty ( "proxyip" ) == null &&
                PROPERTY.getProperty ( "proxyip" ).isEmpty ())
            throw new Exception ( "Proxy IP not set!" );
        else if (PROPERTY.getProperty ( "proxyport" ) == null &&
                PROPERTY.getProperty ( "proxyport" ).isEmpty ())
            throw new Exception ( "Proxy port not set!" );
        else if (PROPERTY.getProperty ( "useragent" ) == null &&
                PROPERTY.getProperty ( "useragent" ).isEmpty ())
            throw new Exception ( "User-Agent not set!" );
        else if (PROPERTY.getProperty ( "mainurl" ) == null &&
                PROPERTY.getProperty ( "mainurl" ).isEmpty ())
            throw new Exception ( "URL not set!" );
        else if (PROPERTY.getProperty ( "requesturl" ) == null &&
                PROPERTY.getProperty ( "requesturl" ).isEmpty ())
            throw new Exception ( "Request URL not set!" );
        else if (PROPERTY.getProperty ( "limit" ) == null &&
                PROPERTY.getProperty ( "limit" ).isEmpty ())
            throw new Exception ( "Limit not set!" );
        else if (PROPERTY.getProperty ( "amount" ) == null &&
                PROPERTY.getProperty ( "amount" ).isEmpty ())
            throw new Exception ( "Amount not set!" );
        else if (PROPERTY.getProperty ( "cvsfile" ) == null &&
                PROPERTY.getProperty ( "cvsfile" ).isEmpty ())
            throw new Exception ( "CVS filename not set!" );

        return true;
    }

    /**
     * Create string from properties
     *
     * @return String
     */
    private static String MyPropertiesToString()
    {
        return new StringJoiner ( "\n" , "\n" , "\n" )
                .add ( "config.properties:" )
                .add ( "Proxy IP: " + PROPERTY.getProperty ( "proxyip" ) )
                .add ( "Proxy Port: " + PROPERTY.getProperty ( "proxyport" ) )
                .add ( "User-Agent: " + PROPERTY.getProperty ( "useragent" ) )
                .add ( "URL: " + PROPERTY.getProperty ( "mainurl" ) )
                .add ( "Request URL: " + PROPERTY.getProperty ( "requesturl" ) )
                .add ( "Limit: " + PROPERTY.getProperty ( "limit" ) )
                .add ( "Amount: " + PROPERTY.getProperty ( "amount" ) )
                .add ( "CVS filename: " + PROPERTY.getProperty ( "cvsfile" ) )
                .toString ();
    }
}
