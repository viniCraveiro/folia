package br.edu.unicesumar.folia.domain.empresa;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class EmpresaService {
    private final EmpresaRepository  empresaRepository;


    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa salvaEmpresa(Empresa empresa){
        return empresaRepository.save(empresa);
    }

    public void deletaEmpresa(UUID uuid){
        Empresa empresa = empresaRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        empresaRepository.delete(empresa);

    }

    public Empresa atualizaEmpresa(UUID uuid, Empresa empresaAtualizado) {
        Empresa empresaExistente = empresaRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        empresaExistente.setNomeFantasia(empresaAtualizado.getNomeFantasia());
        empresaExistente.setCnpj(empresaAtualizado.getCnpj());
        empresaExistente.setRazaoSocial(empresaAtualizado.getRazaoSocial());
        empresaExistente.setEmail(empresaAtualizado.getEmail());
        empresaExistente.setSenha(empresaAtualizado.getSenha());
        empresaExistente.setEndereco(empresaAtualizado.getEndereco());

        return empresaRepository.save(empresaExistente);
    }
}

