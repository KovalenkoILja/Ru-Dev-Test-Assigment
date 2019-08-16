package rudev.test.assigment.handlers;

import com.google.gson.Gson;
import rudev.test.assigment.models.JsonResponse;

/**
 * Class for work with json (use Gson)
 */
public class JsonHandler
{
    /**
     * Deserialize json string into JsonResponse
     *
     * @param json - json String
     * @return JsonResponse
     */
    public static JsonResponse ToResponseFromJson(String json)
    {
        Gson gson = new Gson ();

        return gson.fromJson ( json , JsonResponse.class );
    }
}
