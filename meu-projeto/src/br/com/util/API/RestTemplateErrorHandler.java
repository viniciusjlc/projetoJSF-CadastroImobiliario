package br.com.util.API;

import br.com.shared.dto.ApiErrorDTO;
import br.com.util.API.ClienteRestException;
import br.com.util.JWTUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RestTemplateErrorHandler implements ResponseErrorHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);

    public RestTemplateErrorHandler() {
    }

    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR || clientHttpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
    }

    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        this.mostrarErros(clientHttpResponse);
        throw new ClienteRestException("Erro ao executar operação.");
    }

    private void mostrarErros(ClientHttpResponse clientHttpResponse) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!this.obterJson(clientHttpResponse).startsWith("[")) {
            this.adicionarMensagemErroAoContexto(context, this.obterErro(clientHttpResponse));
        } else {
            Iterator var3 = this.obterErros(clientHttpResponse).iterator();

            while(var3.hasNext()) {
                ApiErrorDTO erro = (ApiErrorDTO)var3.next();
                Logger var10000 = LOGGER;
                String var10001 = erro.getCampo();
                var10000.error(var10001 + ": " + erro.getMensagem());
                this.adicionarMensagemErroAoContexto(context, erro);
            }
        }

    }

    private ApiErrorDTO obterErro(ClientHttpResponse clientHttpResponse) throws IOException {
        return (ApiErrorDTO)(new Gson()).fromJson(this.obterJson(clientHttpResponse), ApiErrorDTO.class);
    }

    private List<ApiErrorDTO> obterErros(ClientHttpResponse clientHttpResponse) throws IOException {
        Type tipoLista = (new TypeToken<ArrayList<ApiErrorDTO>>() {
        }).getType();
        return (List)(new Gson()).fromJson(this.obterJson(clientHttpResponse), tipoLista);
    }

    private String obterJson(ClientHttpResponse clientHttpResponse) throws IOException {
        return StreamUtils.copyToString(clientHttpResponse.getBody(), StandardCharsets.UTF_8);
    }

    private void adicionarMensagemErroAoContexto(FacesContext context, ApiErrorDTO erro) {
        String mensagem = erro.getCampo().concat(" ").concat(erro.getMensagem());
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "Erro");
        context.addMessage((String)null, facesMessage);
    }
}
