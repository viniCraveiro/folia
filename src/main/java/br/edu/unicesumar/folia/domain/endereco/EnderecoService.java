package br.edu.unicesumar.folia.domain.endereco;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco salvaEndereco(Endereco endereco){

        return enderecoRepository.save(endereco);
    }

    public void deletaEndereco(UUID uuid){
        Endereco endereco = enderecoRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        enderecoRepository.delete(endereco);

    }

    public Endereco atualizaEndereco(UUID uuid, Endereco EnderecoAtualizado) {
        Endereco enderecoExistente = enderecoRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        enderecoExistente.setLogradouro(EnderecoAtualizado.getLogradouro());
        enderecoExistente.setNumeroResidencial(EnderecoAtualizado.getNumeroResidencial());
        enderecoExistente.setComplemento(EnderecoAtualizado.getComplemento());
        enderecoExistente.setBairro(EnderecoAtualizado.getBairro());
        enderecoExistente.setCidade(EnderecoAtualizado.getCidade());
        enderecoExistente.setCep(EnderecoAtualizado.getCep());
        enderecoExistente.setUf(EnderecoAtualizado.getUf());
        return enderecoRepository.save(enderecoExistente);
    }

}
