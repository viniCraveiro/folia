package br.edu.unicesumar.folia.controller.boleto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NapiResponseWrapper {

    @JsonProperty("values")
    private List<BoletoNapiDTO> values;

    public  List<BoletoNapiDTO> getValues() {
        return values;
    }

    public void setValue( List<BoletoNapiDTO> value) {
        this.values = value;
    }

}
