package com.app.ista.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ista.model.Usuarios;
import com.app.ista.repository.UsuariosRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuariosRepository usuarioRepository;
		
	public Usuarios guardarUsuario(Usuarios usuario) {
		if (usuarioRepository.findAll().isEmpty()) {
			usuario.setIdUsuario(1);
        } else {
        	usuario.setIdUsuario(usuarioRepository.id().get(0).getIdUsuario()+ 1);
        }
        return usuarioRepository.save(usuario);
	}
	
	public Usuarios guardar(Usuarios usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuarios>listarUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public Usuarios iniciarSesion(String usuarioCedula, String usuarioContrasenia, int usuarioTipo) {
		return usuarioRepository.findByUsuarioCedulaAndUsuarioContraseniaAndUsuarioTipo(usuarioCedula, usuarioContrasenia, usuarioTipo);
	}
	
	public Usuarios buscarPorId(int idUsuario) {
		return usuarioRepository.findByIdUsuario(idUsuario);
	}
	
	public Usuarios buscarPorCedula(String usuarioCedula) {
		return usuarioRepository.findByUsuarioCedula(usuarioCedula);
	}
	
    public Integer eliminarUsuario(Integer idUsuario) {
        return usuarioRepository.deleteByIdUsuario(idUsuario);
    }
}
