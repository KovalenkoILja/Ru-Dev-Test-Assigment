package rudev.test.assigment.models;

import java.util.List;
import java.util.StringJoiner;

/**
 * Object for storing a Json response
 */
public class JsonResponse
{
    public String postback;
    public boolean lastPage;
    public String pin;
    public int pageSize;
    public boolean finished;
    public int page;
    public List <GpsProductDetail> gpsProductDetails;

    /**
     * Overridden toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return new StringJoiner ( "\n" , JsonResponse.class.getSimpleName () + "[" , "]" )
                .add ( "postback='" + postback + "'" )
                .add ( "lastPage=" + lastPage )
                .add ( "pin='" + pin + "'" )
                .add ( "pageSize=" + pageSize )
                .add ( "finished=" + finished )
                .add ( "page=" + page )
                .add ( "gpsProductDetails=" + gpsProductDetails )
                .toString ();
    }
}
