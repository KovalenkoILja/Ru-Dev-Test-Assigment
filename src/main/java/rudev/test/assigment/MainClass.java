package rudev.test.assigment;

/**
 * Main class with entry point
 */
public class MainClass
{
    /**
     * Entry point
     *
     * @param args - ignored
     */
    public static void main(String[] args)
    {
        try
        {
            Application app = new Application ();
            app.Begin ();
        }
        catch (Exception e) {
            e.printStackTrace ();
        }
    }
}
