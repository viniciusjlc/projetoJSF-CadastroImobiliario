package br.com.util;

import br.com.shared.dto.TokenDTO;
import br.com.util.API.ClienteRestException;
import br.com.util.API.RestTemplateErrorHandler;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JWTUtil {
    private String token;
    private static JWTUtil instance;

    public static JWTUtil getInstance() {
        if (instance == null) {
            instance = new JWTUtil();
        }
        return instance;
    }

    private RestTemplate getClient() {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        restTemplate.setMessageConverters(getHttpMessageConverters());
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        return restTemplate;
    }

    public <T> T chamarMetodoPost(String url, Object parametros, Class<T> classType) {
        System.out.println("metodo post: " + url);
        try {
            HttpEntity<?> body = new HttpEntity(parametros, obterHeaders());
            return getClient().exchange(url, HttpMethod.POST, body, classType, new Object[0]).getBody();
        } catch (HttpStatusCodeException var4) {
            throw new ClienteRestException(var4.getResponseBodyAsString());
        }
    }

    public <T> List<T> chamarMetodoPostListagem(String url, Object parametros, Class<T[]> listType) {
        System.out.println("metodo post list: " + url);
        try {
            HttpEntity<?> body = new HttpEntity(parametros, obterHeaders());
            ResponseEntity<T[]> response = getClient().exchange(url, HttpMethod.POST, body, listType, new Object[0]);
            return (List) (response.getBody() != null ? Arrays.asList((Object[]) response.getBody()) : new ArrayList());
        } catch (HttpStatusCodeException var5) {
            throw new ClienteRestException(var5.getResponseBodyAsString());
        }
    }

    public <T> T chamarMetodoGet(String url, Class<T> classType) {
        System.out.println("metodo get: " + url);
        try {
            HttpEntity<?> body = new HttpEntity(obterHeaders());
            return getClient().exchange(url, HttpMethod.GET, body, classType, new Object[0]).getBody();
        } catch (HttpStatusCodeException var3) {
            throw new ClienteRestException(var3.getResponseBodyAsString());
        }
    }

    public <T> List<T> chamarMetodoGetListagem(String url, Class<T[]> listType) {
        System.out.println("metodo get list: " + url);
        try {
            HttpEntity<?> body = new HttpEntity(obterHeaders());
            ResponseEntity<T[]> response = getClient().exchange(url, HttpMethod.GET, body, listType, new Object[0]);
            return (List) (response.getBody() != null ? Arrays.asList((Object[]) response.getBody()) : new ArrayList());
        } catch (HttpStatusCodeException var4) {
            throw new ClienteRestException(var4.getResponseBodyAsString());
        }
    }

    public <T> T chamarMetodoDelete(String url, Class<T> classType) {
        try {
            HttpEntity<?> body = new HttpEntity(obterHeaders());
            return getClient().exchange(url, HttpMethod.DELETE, body, classType, new Object[0]).getBody();
        } catch (HttpStatusCodeException var4) {
            throw new ClienteRestException(var4.getResponseBodyAsString());
        }
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        return new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
    }

    private List<HttpMessageConverter<?>> getHttpMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList();
        messageConverters.add(getJacksonMessageConverter());
        return messageConverters;
    }

    private MappingJackson2HttpMessageConverter getJacksonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        converter.setObjectMapper(getObjectMapper());
        return converter;
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    private HttpHeaders obterHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        setarBearerTokenCasoNecessario(headers);
        return headers;
    }

    private void setarBearerTokenCasoNecessario(HttpHeaders headers) {
        String token = retorarToken();
        if (VerificadorUtil.naoEstaNuloOuVazio(token)) {
            headers.setBearerAuth(token);
        }
    }

    private String retorarToken() {
        return this.token;
    }

    public void gravarToken(TokenDTO tokenDTO) {
        try {
            this.token = tokenDTO.getToken();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
