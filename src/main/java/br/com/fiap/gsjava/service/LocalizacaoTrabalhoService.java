package br.com.fiap.gsjava.service;

import br.com.fiap.gsjava.DTO.LocalizacaoTrabalhoRequest;
import br.com.fiap.gsjava.DTO.LocalizacaoTrabalhoRequestDTO;
import br.com.fiap.gsjava.DTO.LocalizacaoTrabalhoResponse;
import br.com.fiap.gsjava.model.LocalizacaoTrabalho;
import br.com.fiap.gsjava.model.Usuario;
import br.com.fiap.gsjava.repository.LocalizacaoTrabalhoRepository;
import br.com.fiap.gsjava.repository.UsuarioRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalizacaoTrabalhoService {

    private final LocalizacaoTrabalhoRepository localizacaoTrabalhoRepository;
    private final UsuarioRepository usuarioRepository;

    public LocalizacaoTrabalhoService(LocalizacaoTrabalhoRepository localizacaoTrabalhoRepository, UsuarioRepository usuarioRepository) {
        this.localizacaoTrabalhoRepository = localizacaoTrabalhoRepository;
        this.usuarioRepository = usuarioRepository;
    }
    @Transactional
    @CachePut(value = "localizacao", key = "#result.id")
    public LocalizacaoTrabalhoResponse criarLocalizacao(LocalizacaoTrabalhoRequest request) {
        LocalizacaoTrabalho loc = new LocalizacaoTrabalho();
        loc.setTipo(request.getTipo());
        loc.setGrausCelcius(request.getGrausCelcius());
        loc.setNivelUmidade(request.getNivelUmidade());
        loc.setUsuarios(null);

        loc = localizacaoTrabalhoRepository.save(loc);

        return new LocalizacaoTrabalhoResponse(
                loc.getId(),
                loc.getTipo(),
                loc.getGrausCelcius(),
                loc.getNivelUmidade(),
                null
        );
    }

    @Transactional
    @CacheEvict(value = "localizacao", key = "#id")
    public void deletarLocalizacao(Long id) {
        localizacaoTrabalhoRepository.deleteById(id);
    }
    @Cacheable(value = "localizacao", key = "#id")
    public LocalizacaoTrabalhoResponse pegarPorId(Long id) {
        LocalizacaoTrabalho loc = localizacaoTrabalhoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));

        List<Long> idsUsuarios = loc.getUsuarios().stream().map(Usuario::getId).collect(Collectors.toList());
        return new LocalizacaoTrabalhoResponse(
                loc.getId(),
                loc.getTipo(),
                loc.getGrausCelcius(),
                loc.getNivelUmidade(),
                idsUsuarios
        );
    }

    @Transactional
    @CachePut(value = "localizacao", key = "#id")
    public LocalizacaoTrabalhoResponse atualizarLocalizacao(Long id, LocalizacaoTrabalhoRequestDTO requestDTO) {
        LocalizacaoTrabalho loc = localizacaoTrabalhoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));

        loc.setTipo(requestDTO.tipo());
        loc.setGrausCelcius(requestDTO.grausCelcius());
        loc.setNivelUmidade(requestDTO.nivelUmidade());

        if (requestDTO.idUsuarios() != null) {
            List<Usuario> usuarios = usuarioRepository.findAllById(requestDTO.idUsuarios());
            loc.setUsuarios(usuarios);
        }

        loc = localizacaoTrabalhoRepository.save(loc);

        List<Long> idsUsuarios = loc.getUsuarios().stream().map(Usuario::getId).collect(Collectors.toList());
        return new LocalizacaoTrabalhoResponse(
                loc.getId(),
                loc.getTipo(),
                loc.getGrausCelcius(),
                loc.getNivelUmidade(),
                idsUsuarios
        );
    }
}
