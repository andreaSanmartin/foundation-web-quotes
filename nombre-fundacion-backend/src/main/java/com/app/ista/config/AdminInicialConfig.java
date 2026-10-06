package com.app.ista.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.ista.model.Usuarios;
import com.app.ista.repository.UsuariosRepository;
import com.app.ista.service.UsuarioService;

//Crea el primer SuperAdministrador cuando la base de datos no tiene usuarios,
//a partir de ADMIN_CEDULA, ADMIN_PASSWORD y ADMIN_NOMBRE
@Configuration
public class AdminInicialConfig {

	private static final int TIPO_SUPER_ADMINISTRADOR = 1;

	@Value("${app.admin.cedula:}")
	private String cedula;

	@Value("${app.admin.password:}")
	private String password;

	@Value("${app.admin.nombre:Administrador}")
	private String nombre;

	@Bean
	CommandLineRunner crearAdminInicial(UsuariosRepository usuariosRepository, UsuarioService usuarioService) {
		return args -> {
			if (cedula.isEmpty() || password.isEmpty() || usuariosRepository.count() > 0) {
				return;
			}
			Usuarios admin = new Usuarios();
			admin.setUsuarioCedula(cedula);
			admin.setUsuarioNombre(nombre);
			admin.setUsuarioContrasenia(password);
			admin.setUsuarioTipo(TIPO_SUPER_ADMINISTRADOR);
			admin.setUsuarioEstado(true);
			admin.setUsuarioFechaCreacion("Fecha:" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			usuarioService.guardarUsuario(admin);
			System.out.println("Usuario SuperAdministrador inicial creado con cedula " + cedula);
		};
	}
}
