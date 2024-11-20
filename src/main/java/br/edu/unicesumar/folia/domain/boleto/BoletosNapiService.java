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

    private final BoletoRepository boletoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;
    private final BancoRepository bancoRepository;

    public BoletosNapiService(BoletoRepository boletoRepository,
                              UsuarioRepository usuarioRepository,
                              EmpresaRepository empresaRepository,
                              BancoRepository bancoRepository) {
        this.boletoRepository = boletoRepository;
        this.usuarioRepository = usuarioRepository;
        this.empresaRepository = empresaRepository;
        this.bancoRepository = bancoRepository;
    }

    public List<BoletoNapiDTO> consultarNapiEProcessarDados() {
        String url = "";
        RestTemplate restTemplate = new RestTemplate();

        try {
            NapiResponseWrapper response = restTemplate.getForObject(url, NapiResponseWrapper.class);
            List<Boleto> boletosSave = new ArrayList<>();

            if (response != null && response.getValues() != null) {
                List<BoletoNapiDTO> dto = response.getValues();

                dto.forEach(boletoDto -> {
                    Banco banco = bancoRepository.findByAgenciaAndConta(
                            boletoDto.getBancoAgencia(),
                            boletoDto.getBancoConta()
                    ).orElseGet(() -> {
                        Banco novoBanco = new Banco();
                        novoBanco.setNome(boletoDto.getBancoNome());
                        novoBanco.setConta(boletoDto.getBancoConta());
                        novoBanco.setContaDigito(boletoDto.getBancoContaDigito());
                        novoBanco.setAgencia(boletoDto.getBancoAgencia());
                        novoBanco.setAgenciaDigito("8");
                        return bancoRepository.save(novoBanco);
                    });

                    Empresa empresa = empresaRepository.findByCnpj(
                            boletoDto.getEstabelecimentoIdentificacao()
                    ).orElseGet(() -> {
                        Empresa novaEmpresa = new Empresa();
                        novaEmpresa.setCnpj(boletoDto.getEstabelecimentoIdentificacao());
                        novaEmpresa.setNomeFantasia(boletoDto.getEstabelecimentoNome());
                        novaEmpresa.setRazaoSocial(boletoDto.getEstabelecimentoNome());
                        novaEmpresa.setEmail("Teste@gmail.com");
                        novaEmpresa.setTelefone("5544333333333");
                        return empresaRepository.save(novaEmpresa);
                    });

                    Usuario usuario = usuarioRepository.findByIdentificacao(
                            boletoDto.getPessoaIdentificacao()
                    ).orElseGet(() -> {
                        Usuario novoUsuario = new Usuario();
                        novoUsuario.setIdentificacao(boletoDto.getPessoaIdentificacao());
                        novoUsuario.setNome(boletoDto.getPessoaNome());
                        novoUsuario.setTipoUsuario(TipoUsuario.valueOf("USER"));
                        novoUsuario.setEmail("Teste@gmail.com");
                        novoUsuario.setEmpresa(empresa);
                        return usuarioRepository.save(novoUsuario);
                    });

                    Boleto boleto = new Boleto();
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

                    // Configurações adicionais
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

