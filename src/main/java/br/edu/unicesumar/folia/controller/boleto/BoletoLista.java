package br.edu.unicesumar.folia.controller.boleto;

import br.edu.unicesumar.folia.controller.Conversor;
import br.edu.unicesumar.folia.domain.boleto.Boleto;
import br.edu.unicesumar.folia.domain.configuracaobanco.ConfiguracaoBanco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoletoLista {
    private BoletoStatus status;
    private String nome;
    private String banco;
    private String parcerla;
    private String vencimento;
    private String valor;

    public void alimentarDados(Boleto boleto, ConfiguracaoBanco configuracaoBanco){
        nome = boleto.getDadosEmpresa().getNomeFantasia();
        banco = Conversor.obterNomeBanco(Integer.parseInt(configuracaoBanco.getTipoCobranca()));
        parcerla = boleto.getTotalParcelas();
        vencimento = boleto.getDataVencimento();
        valor = boleto.getValor();
    }
}
