package snapchat.adsclient;


//import java.net.URI;
//import java.net.URLEncoder;
//import java.net.http.*;
//import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import snapchat.adsclient.models.Audience;
import snapchat.adsclient.models.Targeting;
import snapchat.adsclient.models.targeting.Demographics;
import snapchat.adsclient.models.targeting.Geos;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class AdsProvider {
    private final String accessToken;
    private final String accountId;

    public AdsProvider(String accessToken, String accountId) {
        this.accessToken = accessToken;
        this.accountId = accountId;
    }

    public static String refreshAcccessToken() {
        try {
            String url = "https://accounts.snapchat.com/login/oauth2/access_token";
            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("grant_type", "refresh_token"));
            params.add(new BasicNameValuePair("refresh_token", "eyJraWQiOiJyZWZyZXNoLXRva2VuLWExMjhnY20uMCIsInR5cCI6IkpXVCIsImVuYyI6IkExMjhHQ00iLCJhbGciOiJkaXIifQ..m7aXvCtdxkQRg1yf.lTzpiYT5Cl7Z4pV_19vmpjA03hU6XFjShLyVI0lIwKZVDOYandW4aKNvBl-IWAHhnJlGdJtcpD3skobofVy_8Kqed3v1YyeLeBVzrF4hfkXqLCmKVHG_rzWXCFxDWHIwRkSwkAL6r-JWiZ_vxE-d51-KXMbyXyZ7vwWc99nx79I0XHyB8JyI85wwlydiKzrPBFB1KAatU_3qviLQwQcYBc0OwjAa98NYSnZ3UHsN8OoN3KBvhRWXWeCu3gboxAonrOcy67BDBRw47vI.rwkM4UixGn3fqJ_Paxg5lg"));
            params.add(new BasicNameValuePair("client_id", "534cd4c5-82a1-481f-915f-5797cc781b2e"));
            params.add(new BasicNameValuePair("client_secret", "6eb2fcc0ee03810f4374"));
            return tokenRequest(url, new UrlEncodedFormEntity(params, "utf-8"));
        }catch (Exception e){
            return "{\"message\":\"error\"}";
        }
    }

    public void getAudienceSize() throws UnsupportedEncodingException {
        String url = "https://adsapi.snapchat.com/v1/adaccounts/";
        url += this.accountId + "/audience_size_v2";

        Audience audience = new Audience();
        audience.setName("test");
        audience.setStatus("ACTIVE");
        audience.setAuto_bid("false");
        audience.setBid_micro("6000000");
        audience.setDaily_budget_micro("50000000");
        audience.setDelivery_constraint("DAILY_BUDGET");
        audience.setPlacement("CONTENT");
        audience.setOptimization_goal("APP_INSTALLS");
        audience.setType("SNAP_ADS");

        Targeting targeting = new Targeting();
        Geos geos = new Geos();
        geos.setCountry_code("us");
        targeting.setGeos(new Geos[]{geos});

        Demographics demographics = new Demographics();
        demographics.setAge_groups(new String[]{"13-17", "18-20", "21-24"});
        targeting.setDemographics(new Demographics[]{demographics});

        audience.setTargeting(targeting);
        Gson gson = new Gson();
        StringEntity postingString = new StringEntity(gson.toJson(audience));
        String res = postRequest(url, postingString);
        System.out.println(res);
    }

    private String postRequest(String url, HttpEntity params) {
        try {
            HttpPost request = new HttpPost(url);
            request.setEntity(params);
            request.setHeader("Authorization", "Bearer " + this.accessToken);
            request.setHeader("Content-Type", "application/json");
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null)
                return EntityUtils.toString(entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "{\"message\":\"error\"}";
    }

    private static String tokenRequest(String url, HttpEntity params) {
        try {
            HttpPost request = new HttpPost(url);
            request.setEntity(params);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null)
                return EntityUtils.toString(entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "{\"message\":\"error\"}";
    }

}
