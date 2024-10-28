package br.edu.unicesumar.folia.domain.contaBancaria;

import br.edu.unicesumar.folia.controller.contaBancaria.ContaBancariaListaDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    //cadastrar conta
    public ContaBancaria cadastrarContaBancaria (@Valid ContaBancaria contaBancaria){
        return contaBancariaRepository.save(contaBancaria);
    }
    //listar contas
    public List<ContaBancariaListaDTO> listarTodas() {
        List<ContaBancaria> contas = contaBancariaRepository.findAll();
        return contas.stream().map(this::convertToDTO).collect(Collectors.toList());

    }
    private ContaBancariaListaDTO convertToDTO (ContaBancaria contaBancaria){
        return new ContaBancariaListaDTO(
                contaBancaria.getBanco(),
                contaBancaria.getTitular(),
                contaBancaria.getNumeroConta(),
                contaBancaria.getCodigoIban()
        );
    }

    public Optional<ContaBancaria> buscarPorId(UUID uuid){
        return contaBancariaRepository.findById(uuid);

    }

    public ContaBancaria atualizarConta (UUID uuid, @Valid ContaBancaria contaAtualizada ){
        Optional<ContaBancaria> contaExistente = contaBancariaRepository.findById(uuid);

        if (contaExistente.isPresent()){
            ContaBancaria contaBancaria = contaExistente.get();

            contaBancaria.setNumeroConta(contaAtualizada.getNumeroConta());
            contaBancaria.setAgencia(contaAtualizada.getAgencia());
            contaBancaria.setBanco(contaAtualizada.getBanco());
            contaBancaria.setTitular(contaAtualizada.getTitular());
            contaBancaria.setCpfCnpj(contaAtualizada.getCpfCnpj());
            contaBancaria.setTipoConta(contaAtualizada.getTipoConta());
            contaBancaria.setCodigoIban(contaAtualizada.getCodigoIban());
            contaBancaria.setDataAbertura(contaAtualizada.getDataAbertura());
            contaBancaria.setTelefoneContato(contaAtualizada.getTelefoneContato());
            contaBancaria.setEmailTitular(contaAtualizada.getEmailTitular());

            return contaBancariaRepository.save(contaBancaria);
        } else {

            throw new EntityNotFoundException("Conta bancaria não encontrada pelo o ID: " + uuid);
        }

    }

    public void detelarContaBancaria(UUID uuid){
        Optional<ContaBancaria> contaExistente = contaBancariaRepository.findById(uuid);

        if (contaExistente.isPresent()){
            contaBancariaRepository.delete(contaExistente.get());
        }else {
            throw new EntityNotFoundException("Conta bancária não encontrada com o ID: " + uuid);
        }

    }



}
