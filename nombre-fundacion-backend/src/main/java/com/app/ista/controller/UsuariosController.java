package com.app.ista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ista.model.Usuarios;
import com.app.ista.service.UsuarioService;

@RestController
public class UsuariosController {

	@Autowired
	UsuarioService usuarioService;
			
	@PostMapping("/post-usuario")
	public Usuarios guardarUsuario(@RequestBody Usuarios usuario) {	
		Usuarios usuariocreado = usuarioService.guardarUsuario(usuario);
		return usuariocreado;
	}
	
	@GetMapping("/getAll-usuario")
	public List<Usuarios> listarUsuarios() {
		return usuarioService.listarUsuarios();
	}

	@GetMapping("/login-usuario")
	public Usuarios login(@RequestParam("usuarioCedula") String usuarioCedula, @RequestParam("usuarioContrasenia") String usuarioContrasenia,
			@RequestParam("usuarioTipo") int usuarioTipo) {
		Usuarios usuario = usuarioService.iniciarSesion(usuarioCedula, usuarioContrasenia, usuarioTipo);
		if (usuario != null) {
			return usuario;
		}
		return new Usuarios();
	}
		
	@PutMapping("/update-usuario")
	public Usuarios actualizarUsuario(@RequestBody Usuarios usuario) {
		Usuarios usuarioAct = usuarioService.buscarPorId(usuario.getIdUsuario());
		usuarioAct.setUsuarioCedula(usuario.getUsuarioCedula());
		usuarioAct.setUsuarioNombre(usuario.getUsuarioNombre());
		usuarioAct.setUsuarioContrasenia(usuario.getUsuarioContrasenia());
		usuarioAct.setUsuarioTipo(usuario.getUsuarioTipo());
		usuarioAct.setUsuarioEstado(usuario.isUsuarioEstado());
		usuarioAct.setUsuarioFechaCreacion(usuario.getUsuarioFechaCreacion());
		usuarioService.guardar(usuarioAct);
		return usuarioAct;
	}
	
	@DeleteMapping("delete-usuario/{idUsuario}")
	 public ResponseEntity<Integer>eliminarEmpleado(@PathVariable int idUsuario){
		usuarioService.eliminarUsuario(idUsuario);	
       return new ResponseEntity<>(idUsuario, HttpStatus.OK);
	}
	
	@GetMapping("/getUsuarioByCedula")
	public Usuarios getByCedula(@RequestParam("usuarioCedula") String usuarioCedula) {
		Usuarios usuario = usuarioService.buscarPorCedula(usuarioCedula);
		if (usuario != null) {
			return usuario;
		}
		return new Usuarios();
	}

}
