package br.edu.unicesumar.folia.domain.empresa;

import br.edu.unicesumar.folia.domain.usuario.ValidarIdentificacao;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import java.util.UUID;


@Service
@Transactional
public class EmpresaService {
    private final EmpresaRepository  empresaRepository;


    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa salvaEmpresa(@Valid Empresa empresa){
        empresa.setCnpj(ValidarIdentificacao.removeNonDigits(empresa.getCnpj()));
        if (ValidarIdentificacao.validarCNPJ(empresa.getCnpj())){
            empresaRepository.save(empresa);
            return empresa;
        }
        throw new RuntimeException("Esse CNPJ não existe ");
    }

    public void deletaEmpresa(UUID uuid){
        Empresa empresaExistente = empresaRepository.findById(uuid).orElseThrow(()-> new EntityNotFoundException("Empresa não encontrada com o id: " + uuid));
        empresaRepository.delete(empresaExistente);

    }

    public Empresa atualizaEmpresa(UUID uuid, @Valid Empresa empresaAtualizado) {
        Empresa empresaExistente = empresaRepository.findById(uuid).orElseThrow(() ->new EntityNotFoundException("Empresa não encontrada com o id: " + uuid));
        empresaExistente.setNomeFantasia(empresaAtualizado.getNomeFantasia());
        empresaExistente.setCnpj(empresaAtualizado.getCnpj());
        empresaExistente.setRazaoSocial(empresaAtualizado.getRazaoSocial());
        empresaExistente.setEmail(empresaAtualizado.getEmail());
        empresaExistente.setSenha(empresaAtualizado.getSenha());
        empresaExistente.setEndereco(empresaAtualizado.getEndereco());

        return empresaRepository.save(empresaExistente);
    }

    //execeção do EntityNot....
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotFound(EntityNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleValidationExceptions(ConstraintViolationException ex){
        return "Erro na validação" + ex.getMessage();
    }


}

