package rudev.test.assigment.utilitys;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Class for logger instance
 */
public class MyLogger
{
    public static final Logger LOGGER = Logger.getLogger (
            MyLogger.class.getName () );

    /**
     * Logger Setup
     *
     * @throws IOException - IOException
     */
    public static void LoggerSetup() throws IOException
    {
        Handler fileHandler = new FileHandler ( "./logfile.log" );
        SimpleFormatter simple = new SimpleFormatter ();
        fileHandler.setFormatter ( simple );
        LOGGER.addHandler ( fileHandler );
    }
}
