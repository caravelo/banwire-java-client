import com.caravelo.banwire.client.BanwireClient;
import com.caravelo.banwire.client.BanwireClientException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BanwireClientApp
{
    private static final String HOST = "https://cr.banwire.com/";
    private static final String USER = "pruebasbw";

    public static void main(String[] args) throws BanwireClientException
    {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("reference", System.currentTimeMillis());
        parameters.put("token", "crd.1jJckdSrFY3uAVZecM1voax1gLhv");
        parameters.put("amount", 100);

        BanwireClient banwire = new BanwireClient(HOST, USER);
        System.out.println(banwire.purchase(parameters));
    }
}
