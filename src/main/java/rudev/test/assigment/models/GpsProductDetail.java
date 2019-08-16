package rudev.test.assigment.models;

import java.util.StringJoiner;

/**
 * Object for storing the product details in Json response
 */
public class GpsProductDetail
{
    public int phase;
    public String oriMaxPrice;
    public Object productId;
    public String discount;
    public String shopUrl;
    public int promotionId;
    public String productDetailUrl;
    public String productTitle;
    public String trace;
    public int sellerId;
    public String productImage;
    public String minPrice;
    public String oriMinPrice;
    public int startTime;
    public String orders;
    public String maxPrice;
    public int endTime;
    public String shopName;
    public boolean soldout;
    public String totalStock;
    public String stock;

    /**
     * Overridden toString
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return new StringJoiner ( "\n" , GpsProductDetail.class.getSimpleName () + "[" , "]" )
                .add ( "phase=" + phase )
                .add ( "oriMaxPrice='" + oriMaxPrice + "'" )
                .add ( "productId=" + productId )
                .add ( "discount='" + discount + "'" )
                .add ( "shopUrl='" + shopUrl + "'" )
                .add ( "promotionId=" + promotionId )
                .add ( "productDetailUrl='" + productDetailUrl + "'" )
                .add ( "productTitle='" + productTitle + "'" )
                .add ( "trace='" + trace + "'" )
                .add ( "sellerId=" + sellerId )
                .add ( "productImage='" + productImage + "'" )
                .add ( "minPrice='" + minPrice + "'" )
                .add ( "oriMinPrice='" + oriMinPrice + "'" )
                .add ( "startTime=" + startTime )
                .add ( "orders='" + orders + "'" )
                .add ( "maxPrice='" + maxPrice + "'" )
                .add ( "endTime=" + endTime )
                .add ( "shopName='" + shopName + "'" )
                .add ( "soldout=" + soldout )
                .add ( "totalStock='" + totalStock + "'" )
                .add ( "stock='" + stock + "'" )
                .toString ();
    }
}
