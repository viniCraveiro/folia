package br.edu.unicesumar.folia.domain.contaBancaria;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    //cadastrar conta
    public ContaBancaria cadastrarContaBancaria (ContaBancaria contaBancaria){
        return contaBancariaRepository.save(contaBancaria);
    }
    //listar contas
    public List<ContaBancaria> listarTodas() {
        return contaBancariaRepository.findAll();
    }

    public Optional<ContaBancaria> buscarPorId(UUID uuid){
        return contaBancariaRepository.findById(uuid);

    }

    public ContaBancaria atualizarConta (UUID uuid, ContaBancaria contaAtualizada ){
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
