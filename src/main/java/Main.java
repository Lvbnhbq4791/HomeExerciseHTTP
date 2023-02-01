import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)   // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
        HttpGet request = new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");
        CloseableHttpResponse response = httpClient.execute(request);
        String facts = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        httpClient.close();
        factsWithoutNullVotes(facts);
    }
   public static void factsWithoutNullVotes(String facts) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<FactsAboutCats> listOfFacts = objectMapper.readValue(facts, new TypeReference<>() {
        });
        List<FactsAboutCats> listFactFiltered = listOfFacts.stream()
                .filter(upvotes -> upvotes.getUpvotes() != null).toList();
        System.out.print("Факты без нулевых upvotes:");
        listFactFiltered.forEach(System.out::println);
    }
}
