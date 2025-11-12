package br.com.fiap.gsjava.service;



import br.com.fiap.gsjava.DTO.UsuarioRequest;
import br.com.fiap.gsjava.DTO.UsuarioRequestDTO;
import br.com.fiap.gsjava.DTO.UsuarioResponse;
import br.com.fiap.gsjava.model.LocalizacaoTrabalho;
import br.com.fiap.gsjava.model.Mensagem;
import br.com.fiap.gsjava.model.Usuario;
import br.com.fiap.gsjava.repository.LocalizacaoTrabalhoRepository;
import br.com.fiap.gsjava.repository.MensagemRepository;
import br.com.fiap.gsjava.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    public  final UsuarioRepository usuarioRepository;
    public  final LocalizacaoTrabalhoRepository localizacaoTrabalhoRepository;
    public final MensagemRepository mensagemRepository;
    public UsuarioService(UsuarioRepository usuarioRepository, LocalizacaoTrabalhoRepository localizacaoTrabalhoRepository, MensagemRepository mensagemRepository ) {
        this.mensagemRepository = mensagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.localizacaoTrabalhoRepository = localizacaoTrabalhoRepository;
    }


    public UsuarioResponse criarUsuario(UsuarioRequest usuarioRequest){
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.getNome());
        usuario.setCpf(usuarioRequest.getCpf());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(usuarioRequest.getSenha());
        usuario.setLocTrabalho(null);
        usuario.setMensagens(null);
        usuario = usuarioRepository.save(usuario);

        return new UsuarioResponse(
          usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuarioRequest.getEmail(),
                usuario.getSenha(),
                null,
                null
        );
    }

    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }


    public UsuarioResponse pegarPorId(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o id"));

        List<Long> ids = usuario.getMensagens().stream().map(Mensagem::getId).collect(Collectors.toList());
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getLocTrabalho() != null ? usuario.getLocTrabalho().getId() : null,
                ids

        );
    }

    public UsuarioResponse atualizarUsuario(Long id, UsuarioRequestDTO usuarioRequest){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        usuario.setNome(usuarioRequest.nome());
        usuario.setCpf(usuarioRequest.cpf());
        usuario.setEmail(usuarioRequest.email());
        usuario.setSenha(usuarioRequest.senha());
        if(usuarioRequest.idLocalizacao() != null){
            LocalizacaoTrabalho idLoc = localizacaoTrabalhoRepository.findById(usuarioRequest.idLocalizacao()).orElseThrow(() -> new RuntimeException("Não foi possível encontrar o id da localização"));
            usuario.setLocTrabalho(idLoc);
        }
        Mensagem mensagem = new Mensagem();

        if (usuarioRequest.idMensagens() != null){
            List<Mensagem> mensagensIds = mensagemRepository.findAllById(usuarioRequest.idMensagens());
            usuario.setMensagens(mensagensIds);
        }
        usuario = usuarioRepository.save(usuario);

        List<Long> ids = usuario.getMensagens().stream().map(Mensagem::getId).collect(Collectors.toList());
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getLocTrabalho() != null ? usuario.getLocTrabalho().getId() : null,
                ids
        );

    }

}
