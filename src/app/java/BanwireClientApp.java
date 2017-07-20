import com.caravelo.banwire.client.BanwireClient;
import com.caravelo.banwire.client.BanwireClientException;
import com.caravelo.banwire.client.Purchase;
import com.caravelo.banwire.client.TokenisedCard;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BanwireClientApp
{
    private static final String HOST = "https://cr.banwire.com/";
    private static final String USER = "pruebasbw";

    private final BanwireClient banwire;

    public static void main(String[] args) throws BanwireClientException
    {
        BanwireClientApp app = new BanwireClientApp();
        TokenisedCard t = app.tokeniseCard();
        app.purchase("Reference " + System.currentTimeMillis(),
                t.getToken(),
                BigDecimal.valueOf(100));
    }

    private BanwireClientApp() throws BanwireClientException
    {
        this.banwire = new BanwireClient(HOST, USER);
    }

    private TokenisedCard tokeniseCard() throws BanwireClientException
    {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("number", "5134422031476272");
        parameters.put("exp_month", 12);
        parameters.put("exp_year", 19);
        parameters.put("cvv", 162);
        parameters.put("name", "Test Test");
        parameters.put("address", "123, Valhalla Lane");
        parameters.put("postal_code", 12345);
        parameters.put("email", "test@test.com");
        parameters.put("phone", "+1 987654321");
        return banwire.tokenise(parameters, true);
    }
    private Purchase purchase(String reference, String token, BigDecimal amount) throws BanwireClientException
    {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("reference", reference);
        parameters.put("token", "crd.1jJckdSrFY3uAVZecM1voax1gLhv");
        parameters.put("amount", 100);
        return banwire.purchase(parameters);
    }
}
