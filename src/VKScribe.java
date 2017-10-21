import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ThreadLocalRandom;

public class VKScribe {
    public static String text = "stan";
    public static long random;
    public static String captchaSID;

    public static void VkAll(String fromSt, String toSt, String count) throws Exception {
        long i = 0;
        int from = Integer.parseInt(fromSt);
        int to = Integer.parseInt(toSt);
        String captchaURL;


        while (true) {
            while (i < Integer.parseInt(count)) {
                captchaURL = null;
                random = ThreadLocalRandom.current().nextLong(from, to);
                URIBuilder uriBuild = new URIBuilder();
                uriBuild
                        .setScheme("https")
                        .setHost("api.vk.com")
                        .setPath("/method/messages.send")
                        .setParameter("access_token", Solution.token)
                        .setParameter("user_id", "" + random)
                        .setParameter("message", Solution.mess[ThreadLocalRandom.current().nextInt(5, 13)]);

                URI uri = null;
                uri = uriBuild.build();

                System.out.println(uri);

                HttpClient client = HttpClientBuilder.create().build();
                HttpGet request = new HttpGet(uri);

                HttpResponse response = client.execute(request);
                Integer status = response.getStatusLine().getStatusCode();
                //System.out.println(uri);


                if (status == 200) {
                    StringWriter content = new StringWriter();

                    IOUtils.copy(response.getEntity().getContent(), content);

                    JSONParser parser = new JSONParser();

                    try {
                        JSONObject jsonResp = (JSONObject) parser.parse(content.toString());
                        System.out.println(jsonResp);
                        JSONObject postsList = (JSONObject) jsonResp.get("error");
                        System.out.println(postsList);
                        captchaSID = (String) postsList.get("captcha_sid");
                        captchaURL = (String) postsList.get("captcha_img");
                        System.out.println(captchaSID);
                        System.out.println(captchaURL);

                        Desktop.getDesktop().browse(new URI(captchaURL));
                        if (!captchaURL.equals(null)) {
                            Thread.sleep(1000 * Integer.parseInt(Solution.countSec));
                            break;
                        }
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    i++;
                    Thread.sleep(1000 * Integer.parseInt(Solution.countSec));
                }
            }
            if (i < Integer.parseInt(count))
                break;
            //System.out.println("Соси жопу");
        }
        ReturnForm.CteateForm();

    }
    public static void DecideCaptcha(String words) throws URISyntaxException, IOException {
        URIBuilder uriBuild = new URIBuilder();
        uriBuild
                .setScheme("https")
                .setHost("api.vk.com")
                .setPath("/method/messages.send")
                .setParameter("access_token", Solution.token)
                .setParameter("user_id",  "" + random)
                .setParameter("message", " " + words)
                .setParameter("captcha_sid", captchaSID)
                .setParameter("captcha_key", text);

        URI uri = null;
        uri = uriBuild.build();

        System.out.println(uri);

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(uri);

        HttpResponse response = client.execute(request);
        Integer status = response.getStatusLine().getStatusCode();

        FormClass.CteateForm();
    }
}