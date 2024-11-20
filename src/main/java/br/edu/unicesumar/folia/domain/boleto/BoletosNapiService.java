package br.edu.unicesumar.folia.domain.boleto;


import br.edu.unicesumar.folia.controller.boleto.BoletoNapiDTO;
import br.edu.unicesumar.folia.controller.boleto.NapiResponseWrapper;
import br.edu.unicesumar.folia.domain.banco.Banco;
import br.edu.unicesumar.folia.domain.banco.BancoRepository;
import br.edu.unicesumar.folia.domain.empresa.Empresa;
import br.edu.unicesumar.folia.domain.empresa.EmpresaRepository;
import br.edu.unicesumar.folia.domain.usuario.TipoUsuario;
import br.edu.unicesumar.folia.domain.usuario.Usuario;
import br.edu.unicesumar.folia.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class BoletosNapiService {

    private final BoletoRepository boletoRepository ;
    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;
    private final BancoRepository bancoRepository;

    public BoletosNapiService(BoletoRepository boletoRepository, UsuarioRepository usuarioRepository, UsuarioRepository usuarioRepository1, EmpresaRepository empresaRepository, BancoRepository bancoRepository){
        this.boletoRepository = boletoRepository;
        this.usuarioRepository = usuarioRepository;
        this.empresaRepository = empresaRepository;
        this.bancoRepository = bancoRepository;
    }

    public List<BoletoNapiDTO> consultarNapiEProcessarDados() {
        // URL da API, não esquecer de remover o token antes de comitar
        String url = "https://napi.service.dev.peon.tec.br/folia/v0/contareceber/?token=e66d98a2-28dd-4f88-b90c-916d3f4899ba";
        RestTemplate restTemplate = new RestTemplate();

        try {
            NapiResponseWrapper response = restTemplate.getForObject(url, NapiResponseWrapper.class);
            List<Boleto> boletosSave= new ArrayList<>();
            if (response  != null && response.getValues() != null) {
                List<BoletoNapiDTO> dto = response.getValues();

                dto.forEach(boletoDto-> {
                    Boleto boleto = new Boleto();
                    Banco banco = new Banco();
                    Usuario usuario = new Usuario();
                    Empresa empresa = new Empresa();

                    banco.setNome(boletoDto.getBancoNome());
                    banco.setConta(boletoDto.getBancoConta());
                    banco.setContaDigito(boletoDto.getBancoContaDigito());
                    banco.setAgencia(boletoDto.getBancoAgencia());

                    empresa.setCnpj(boletoDto.getEstabelecimentoIdentificacao());
                    empresa.setNomeFantasia(boletoDto.getEstabelecimentoNome());
                    empresa.setRazaoSocial(boletoDto.getEstabelecimentoNome());
                    empresa.setEmail("Teste@gmail.com");

                    usuario.setIdentificacao(boletoDto.getPessoaIdentificacao());
                    usuario.setNome(boletoDto.getPessoaNome());
                    usuario.setTipoUsuario(TipoUsuario.valueOf("USER"));
                    usuario.setEmail("Teste@gmail.com");
                    usuario.setEmpresa(empresa);

                    boleto.setUsuario(usuario);
                    boleto.setBanco(banco);
                    boleto.setEmpresa(empresa);
                    boleto.setValor(boletoDto.getValor());
                    boleto.setDataEmissao(boletoDto.getDataEmissao());
                    boleto.setDataVencimento(boletoDto.getDataVencimento());
                    boleto.setTotalParcelas(boletoDto.getTotalParcela());
                    boleto.setTipoDocumento(boletoDto.getNumeroDocumento());
                    boleto.setStatus(Status.fromString(boletoDto.getStatusParcela()));
                    boleto.setUrl(boletoDto.getUrlBoleto());

                    boleto.setConvenio(12345);
                    boleto.setCodigoCedente(67890);
                    boleto.setCodigoTransmissao(101112);
                    boleto.setModalidade(1);
                    boleto.setResponsavelEmissao(1);
                    boleto.setTipoCarteira(1);
                    boleto.setCaracteristicaTitulo(1);
                    boleto.setCodigoNegativacao(0);
                    boleto.setIdentificacaoDistribuicao(123);
                    boleto.setOperacao("Emissão");
                    boleto.setChavePix("1234567890");
                    boleto.setTipoChavePix(1);
                    boletosSave.add(boleto);

                });
                boletoRepository.saveAll(boletosSave);
                return dto;
            }
        } catch (Exception e) {
            System.err.println("Erro ao consultar API: " + e);
        }
        return null;

    }

}
