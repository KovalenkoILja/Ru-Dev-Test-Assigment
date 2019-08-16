package rudev.test.assigment;

import rudev.test.assigment.handlers.CvsHandler;
import rudev.test.assigment.handlers.JsonHandler;
import rudev.test.assigment.handlers.UnirestHandler;
import rudev.test.assigment.models.JsonResponse;
import rudev.test.assigment.utilitys.MyProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

import static rudev.test.assigment.utilitys.MyLogger.LOGGER;
import static rudev.test.assigment.utilitys.MyLogger.LoggerSetup;

/**
 * Application class
 */
class Application
{
    private List <JsonResponse> JsonResponses;

    private long startTime = 0;
    private long endTime = 0;
    private int amount = 0;
    private int limit = 0;

    /**
     * Constructor, also setup logger
     *
     * @throws Exception - Exception
     */
    Application() throws Exception
    {
        super ();
        LoggerSetup ();

        JsonResponses = new ArrayList <> ();
    }

    /**
     * Main application logic
     *
     * @throws Exception - Exception
     */
    void Begin() throws Exception
    {
        try
        {
            LOGGER.info ( "------------------START--------------------" );

            this.startTime = System.currentTimeMillis ();

            MyProperties.ReadProperties ();

            UnirestHandler.SetupUnirest ();

            this.amount = Integer.parseInt ( MyProperties.PROPERTY.getProperty ( "amount" ) );
            this.limit = Integer.parseInt ( MyProperties.PROPERTY.getProperty ( "limit" ) );

            this.MainLoop ();

            CvsHandler.ToCSVFile ( JsonResponses , MyProperties.PROPERTY.getProperty ( "cvsfile" ) );

            this.endTime = System.currentTimeMillis ();

            this.PrintStat ();

            LOGGER.info ( "------------------END--------------------" );
        }
        catch (Exception e)
        {
            LOGGER.info ( "Exception: " + e.toString () );
            throw e;
        }
    }

    /**
     * Main application loop
     *
     * @throws Exception - Exception
     */
    private void MainLoop() throws Exception
    {
        JsonResponse response = null;

        for (int offset = 0; offset < this.amount; offset += this.limit)
        {
            String json = UnirestHandler.GetResponse ( this.limit , offset ,
                                                       response == null ? "" : response.postback
            );
            response = JsonHandler.ToResponseFromJson ( json );

            if (response != null)
                JsonResponses.add ( response );
            else throw new Exception ( "JSON Response Empty!" );
        }
    }

    /**
     * Statistics output
     *
     */
    private void PrintStat()
    {
        System.out.println ( this.toString () );
        LOGGER.info ( this.toString () );
    }

    /**
     * Overridden toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return new StringJoiner ( "\n" , "\n" , "\n" )
                .add ( "Number of successful requests: " + JsonResponses.size () )
                .add ( "Quantity of goods received: " +
                               JsonResponses.stream ().mapToInt (
                                       jsonResponse -> jsonResponse.gpsProductDetails.size () ).sum () )
                .add ( "That took: " +
                    String.format (
                        "%02d min, %02d sec (%d milliseconds)" ,
                        TimeUnit.MILLISECONDS.toMinutes ( (endTime - startTime) ) ,
                        TimeUnit.MILLISECONDS.toSeconds ( (endTime - startTime) )
                                - TimeUnit.MINUTES.toSeconds (
                                TimeUnit.MILLISECONDS.toMinutes ( (endTime - startTime) ) ) ,
                        (endTime - startTime)
                ) )
                .toString ();
    }
}
