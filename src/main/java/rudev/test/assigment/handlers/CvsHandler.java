package rudev.test.assigment.handlers;

import com.opencsv.CSVWriter;
import rudev.test.assigment.models.GpsProductDetail;
import rudev.test.assigment.models.JsonResponse;
import rudev.test.assigment.utilitys.MyLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Class for work with CSV files
 */
public class CvsHandler
{
    /**
     * Write list to CSV file
     * @param jsonResponse - list to write
     * @param filename - name of file
     * @throws IOException - IOException exception
     */
    static public void ToCSVFile(List <JsonResponse> jsonResponse , String filename) throws IOException
    {
        if (jsonResponse == null || jsonResponse.isEmpty ())
        {
            MyLogger.LOGGER.info ( "Received empty list" );
            return;
        }

        File returnFile = new File ( filename );

        CSVWriter writer = new CSVWriter (
                new FileWriter ( returnFile , true ) ,
                ';' ,
                CSVWriter.DEFAULT_QUOTE_CHARACTER ,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER ,
                CSVWriter.DEFAULT_LINE_END
        );

        String[] header =
                {
                "productId" , "productTitle" , "productImage" , "productDetailUrl" ,
                "sellerId" , "shopName" , "shopUrl" , "promotionId" ,
                "oriMinPrice" , "oriMaxPrice" ,
                "minPrice" , "maxPrice" ,
                "discount" , "orders" ,
                "stock" , "totalStock" ,
                "startTime" , "endTime" ,
                "phase" , "soldout" , "trace"
                };

        writer.writeNext ( header );

        for (JsonResponse response : jsonResponse)
        {
            for (GpsProductDetail gpsProductDetail : response.gpsProductDetails)
            {
                String[] dataToWrite =
                        {
                        gpsProductDetail.productId.toString () ,
                        gpsProductDetail.productTitle ,
                        gpsProductDetail.productImage ,
                        gpsProductDetail.productDetailUrl ,
                        String.valueOf ( gpsProductDetail.sellerId ) ,
                        gpsProductDetail.shopName ,
                        gpsProductDetail.shopUrl ,
                        String.valueOf ( gpsProductDetail.promotionId ) ,
                        gpsProductDetail.oriMinPrice ,
                        gpsProductDetail.oriMaxPrice ,
                        gpsProductDetail.minPrice ,
                        gpsProductDetail.maxPrice ,
                        gpsProductDetail.discount ,
                        gpsProductDetail.orders ,
                        gpsProductDetail.stock ,
                        gpsProductDetail.totalStock ,
                        String.valueOf ( gpsProductDetail.startTime ) ,
                        String.valueOf ( gpsProductDetail.endTime ) ,
                        String.valueOf ( gpsProductDetail.phase ) ,
                        String.valueOf ( gpsProductDetail.soldout ) ,
                        gpsProductDetail.trace
                        };
                writer.writeNext ( dataToWrite );
            }
        }

        writer.flush ();
        writer.close ();

        MyLogger.LOGGER.info ( "Write to " + filename + " was successful.\n" );
    }
}
