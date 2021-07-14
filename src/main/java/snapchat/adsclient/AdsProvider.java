package snapchat.adsclient;


//import java.net.URI;
//import java.net.URLEncoder;
//import java.net.http.*;
//import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class AdsProvider {
    private final String accessToken;
    private final String accountId;

    public AdsProvider(String accessToken, String accountId) {
        this.accessToken = accessToken;
        this.accountId = accountId;
    }

    public void refreshAcccessToken() throws Exception {
        String url = "https://accounts.snapchat.com/login/oauth2/access_token";
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "refresh_token"));
        params.add(new BasicNameValuePair("refresh_token", "eyJraWQiOiJyZWZyZXNoLXRva2VuLWExMjhnY20uMCIsInR5cCI6IkpXVCIsImVuYyI6IkExMjhHQ00iLCJhbGciOiJkaXIifQ..m7aXvCtdxkQRg1yf.lTzpiYT5Cl7Z4pV_19vmpjA03hU6XFjShLyVI0lIwKZVDOYandW4aKNvBl-IWAHhnJlGdJtcpD3skobofVy_8Kqed3v1YyeLeBVzrF4hfkXqLCmKVHG_rzWXCFxDWHIwRkSwkAL6r-JWiZ_vxE-d51-KXMbyXyZ7vwWc99nx79I0XHyB8JyI85wwlydiKzrPBFB1KAatU_3qviLQwQcYBc0OwjAa98NYSnZ3UHsN8OoN3KBvhRWXWeCu3gboxAonrOcy67BDBRw47vI.rwkM4UixGn3fqJ_Paxg5lg"));
        params.add(new BasicNameValuePair("client_id", "534cd4c5-82a1-481f-915f-5797cc781b2e"));
        params.add(new BasicNameValuePair("client_secret", "6eb2fcc0ee03810f4374"));
        String res = postRequest(url, params);
        System.out.println(res);

    }

    public void getAudienceSize() {
        String url = "https://adsapi.snapchat.com/v1/adaccounts/";
        url += this.accountId + "/audience_size_v2";
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("name", "test"));
        params.add(new BasicNameValuePair("status", "ACTIVE"));
        params.add(new BasicNameValuePair("type", "SNAP_ADS"));
        params.add(new BasicNameValuePair("placement", "CONTENT"));
        params.add(new BasicNameValuePair("bid_micro", "6000000"));
        params.add(new BasicNameValuePair("auto_bid", "false"));
        params.add(new BasicNameValuePair("daily_budget_micro", "50000000"));
        params.add(new BasicNameValuePair("delivery_constraint", "DAILY_BUDGET"));
        params.add(new BasicNameValuePair("optimization_goal", "APP_INSTALLS"));

        params.add(new BasicNameValuePair("included_content_types", "[\"SCIENCE_TECHNOLOGY\"]"));
        params.add(new BasicNameValuePair("targeting", "{\"geos\": [{\"country_code\": \"us\"}],\"demographics\": [{\"age_groups\": [\"13-17\",\"18-20\",\"21-24\"]}]}"));
        System.out.println(params.toString());
        String res = postRequest(url,params);
        System.out.println(res);
    }

    private String postRequest(String url, List<BasicNameValuePair> params) {
        try {
            HttpPost request = new HttpPost(url);
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, "utf-8");
            request.setEntity(urlEncodedFormEntity);
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

    public void getRequest() {
        //TODO code
    }
}
