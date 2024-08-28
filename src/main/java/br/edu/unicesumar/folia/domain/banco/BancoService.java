package br.edu.unicesumar.folia.domain.banco;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Transactional
public class BancoService {
    private final BancoRepository bancoRepository;

    public BancoService(BancoRepository bancoRepository){this.bancoRepository = bancoRepository;}

    public void deletarBanco(UUID uuid){
        Banco banco = bancoRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        bancoRepository.delete(banco);
    }

    public Banco salvaBanco(Banco banco){
        return bancoRepository.save(banco);
    }

    public Banco atualizaBanco(UUID uuid, Banco bancoAtualizado) {
        Banco usuarioExistente = bancoRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        bancoAtualizado.setAgencia((bancoAtualizado.getAgencia()));
        bancoAtualizado.setConta((bancoAtualizado.getAgencia()));
        bancoAtualizado.setAgenciaDigito((bancoAtualizado.getAgencia()));
        bancoAtualizado.setContaDigito((bancoAtualizado.getAgencia()));
        bancoAtualizado.setDigitoVerificadorAgenciaConta((bancoAtualizado.getAgencia()));
        bancoAtualizado.setAgenciaDigito((bancoAtualizado.getAgencia()));

        return bancoRepository.save(bancoAtualizado);
    }

    public Banco encontrarPorIdBanco(UUID uuid) {
        return bancoRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }

}
