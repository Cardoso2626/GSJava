package br.com.fiap.gsjava.service;

import br.com.fiap.gsjava.DTO.MensagemRequest;
import br.com.fiap.gsjava.DTO.MensagemRequestDTO;
import br.com.fiap.gsjava.DTO.MensagemResponse;
import br.com.fiap.gsjava.model.Mensagem;
import br.com.fiap.gsjava.model.Usuario;
import br.com.fiap.gsjava.repository.MensagemRepository;
import br.com.fiap.gsjava.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensagemService {
    public final MensagemRepository mensagemRepository;
    public final UsuarioRepository usuarioRepository;
    public MensagemService(MensagemRepository mensagemRepository, UsuarioRepository usuarioRepository) {
        this.mensagemRepository = mensagemRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Page<MensagemResponse> listarMensagensPorPagina(Pageable pageable) {
     return mensagemRepository.findAll(pageable)
             .map(m -> new MensagemResponse(
                     m.getId(),
                     m.getMensagem(),
                     m.getNivelEstresse(),
                     m.getUsuario() != null ? m.getUsuario().getId() : null
             ));
    }

    public MensagemResponse criarMensagem(MensagemRequest mensagemRequest) {
        Mensagem mensagem = new Mensagem();
        mensagem.setMensagem(mensagemRequest.getMensagem());
        mensagem.setNivelEstresse(mensagemRequest.getNivelEstresse());
        mensagem.setUsuario(null);
        mensagem = mensagemRepository.save(mensagem);

        return new MensagemResponse(
                mensagem.getId(),
                mensagem.getMensagem(),
                mensagem.getNivelEstresse(),
                null
        );
    }

    public MensagemResponse pegarPorId(Long id){
        Mensagem mensagem = mensagemRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível achar a mensagem"));
        return new MensagemResponse(
                mensagem.getId(),
                mensagem.getMensagem(),
                mensagem.getNivelEstresse(),
                mensagem.getUsuario() != null ? mensagem.getUsuario().getId() : null
        );
    }

    public void excluirMensagem(Long id){
        mensagemRepository.deleteById(id);
    }

    public MensagemResponse autalizarMensagem (Long id, MensagemRequestDTO mensagemRequest){
        Mensagem mensagem = mensagemRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível achar a mensagem"));
        mensagem.setMensagem(mensagemRequest.mensagem());
        mensagem.setNivelEstresse(mensagemRequest.nivelEstresse());
        if(mensagemRequest.idUsuario() != null){
            Usuario usuario = usuarioRepository.findById(mensagemRequest.idUsuario()).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o usuario"));
            mensagem.setUsuario(usuario);
        }
        mensagem = mensagemRepository.save(mensagem);

        return new MensagemResponse(
                mensagem.getId(),
                mensagem.getMensagem(),
                mensagem.getNivelEstresse(),
                mensagem.getUsuario() != null ? mensagem.getUsuario().getId() : null
        );
    }

    public List<MensagemResponse> listarMensagens (){
        List<Mensagem> mensagens = mensagemRepository.findAll();
        return mensagens.stream()
                .map(m -> new MensagemResponse(
                        m.getId(),
                        m.getMensagem(),
                        m.getNivelEstresse(),
                        m.getUsuario() != null ? m.getUsuario().getId() : null
                )).collect(Collectors.toList());
    }
}
