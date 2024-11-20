package br.edu.unicesumar.folia.controller.boleto;

import br.edu.unicesumar.folia.controller.usuarioboleto.EmpresaBoletoFiltradoDTO;
import br.edu.unicesumar.folia.controller.usuarioboleto.UsuarioBoletoFiltradoDTO;
import br.edu.unicesumar.folia.controller.usuarioboleto.BoletoFiltroDTO;
import br.edu.unicesumar.folia.controller.usuarioboleto.UsuarioBoletoListaDTO;
import br.edu.unicesumar.folia.domain.boleto.Boleto;
import br.edu.unicesumar.folia.domain.boleto.BoletoRepository;
import br.edu.unicesumar.folia.domain.boleto.BoletoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Boleto",
        description = "Listar, buscar e atualizar status de boletos"
)
@RestController
@RequestMapping("api/boleto")
public class BoletoRestController {

    private final BoletoService boletoService;

    private final BoletoRepository repository;

    public BoletoRestController(BoletoService boletoService, BoletoRepository repository) {
        this.boletoService = boletoService;
        this.repository = repository;
    }

    //todos os boletos
    @GetMapping
    public ResponseEntity<List<Boleto>> listarBoletos() {
        List<Boleto> boletos = boletoService.listarBoletos();
        return new ResponseEntity<>(boletos, HttpStatus.OK);
    }

    @GetMapping("/usuario/{uuid}")
    public ResponseEntity<Page<BoletoListaDTO>> listarBoletosPorUsuario(@PathVariable UUID uuid, @PageableDefault(size = 10) Pageable pageable) {
        Page<BoletoListaDTO> boletos = boletoService.listarBoletosPorUsuario(uuid, pageable);
        return ResponseEntity.ok(boletos);
    }

    // Buscar boleto
    @GetMapping("/{uuid}")
    public ResponseEntity<Boleto> buscarBoleto(@PathVariable UUID uuid) {
        try {
            Boleto boleto = boletoService.buscarBoleto(uuid);
            return new ResponseEntity<>(boleto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Atualizar status boleto
    @PutMapping("/{uuid}/status")
    public ResponseEntity<String> atualizarStatusBoleto(@PathVariable UUID uuid, @RequestParam String novoStatus) {
        try {
            boletoService.atualizarStatusBoleto(uuid, novoStatus);
            return new ResponseEntity<>("Status do boleto atualizado com sucesso",HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Boleto não encontrado",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/dadosBoletos/{uuid}")
    public ResponseEntity<BoletoInformacoesDTO> informacoesBoletos(@PathVariable UUID uuid) {
        BoletoInformacoesDTO boletos = boletoService.listarBoletosPorEmpresa(uuid);
        return new ResponseEntity<>(boletos, HttpStatus.OK);
    }

    @GetMapping("/empresa/{empresaUuid}")
    public ResponseEntity<List<UsuarioBoletoListaDTO>> obterBoletosPorEmpresa(@PathVariable UUID empresaUuid) {
        List<UsuarioBoletoListaDTO> dtos = boletoService.obterDadosUsuariosPorEmpresa(empresaUuid);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/empresa/filtrar")
    public ResponseEntity<List<EmpresaBoletoFiltradoDTO>> filtrarBoletosEmpresa(@RequestBody BoletoFiltroDTO filtro) {
        return ResponseEntity.ok(boletoService.buscarComFiltroEmpresa(filtro));
    }

    @PostMapping("/usuario/filtrar")
    public ResponseEntity<List<UsuarioBoletoFiltradoDTO>> filtrarBoletosUsuario(@RequestBody BoletoFiltroDTO filtro) {
        return ResponseEntity.ok(boletoService.buscarComFiltroUsuario(filtro));
    }

}

