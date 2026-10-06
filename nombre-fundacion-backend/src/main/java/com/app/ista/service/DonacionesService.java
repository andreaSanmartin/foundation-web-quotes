package com.app.ista.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ista.model.Donaciones;
import com.app.ista.repository.DonacionesRepository;

@Service
public class DonacionesService {
	@Autowired
	private DonacionesRepository donacrepo;

	public Donaciones guardarDonacion(Donaciones donaciones/*, MultipartFile file*/){
		if (donacrepo.findAll().isEmpty()) {
			donaciones.setIdDonacion(1);
		} else {
			donaciones.setIdDonacion(donacrepo.id().get(0).getIdDonacion() + 1);
		}
		
		//donaciones.setPicByte(compressZLib(file.getBytes()));
		
		return donacrepo.save(donaciones);
	}
	
	public List<Donaciones>listadoDonaciones(){
		
		/*List<Donaciones> listaDonacionesDecompress = new ArrayList<>();
		
		for (Donaciones donaciones : donacrepo.findAll()) {
			Donaciones donacion = new Donaciones(donaciones.getIdDonacion(), donaciones.getNombreDonacion(), donaciones.getCantidad(), donaciones.getCategoria(), donaciones.getFechaDonacion(), donaciones.getDescripcionDonacion(), donaciones.getCedulaPersona(),  decompressZLib(donaciones.getPicByte()));
			listaDonacionesDecompress.add(donacion);
		}
		
		return listaDonacionesDecompress;*/
		return donacrepo.findAll();
	}
	
	public List<Donaciones> listaDoncionesByNombre(String nombreDonacion){
		return donacrepo.findByNombreDonacion(nombreDonacion);
	}
	
	public List<Donaciones> listaDonacionesByCategoria(String categoria){
		return donacrepo.findByCategoria(categoria);
	}
	
	public Optional<Donaciones> porId(Long idDonacion){
		return donacrepo.findById(idDonacion);
	}
	
	public Donaciones actualizaDonacion(Long idDonacion, Donaciones donaciones) {
		if (donacrepo.findById(idDonacion)!=null){
			donaciones.setIdDonacion(idDonacion.intValue());
			return donacrepo.save(donaciones);
		}else {
			return null;
		}
	}
	
	public boolean eliminarDonacion(Long idDonacion) {
		if (donacrepo.findById(idDonacion)!=null) {
			donacrepo.deleteById(idDonacion);
			return true;
		}else {
			return false;
		}
	}
	
	
	// compress the image bytes before storing it in the database
		public static byte[] compressZLib(byte[] data) {
			Deflater deflater = new Deflater();
			deflater.setInput(data);
			deflater.finish();

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			while (!deflater.finished()) {
				int count = deflater.deflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
			System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

			return outputStream.toByteArray();
		}

		// uncompress the image bytes before returning it to the angular application
		public static byte[] decompressZLib(byte[] data) {
			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			try {
				while (!inflater.finished()) {
					int count = inflater.inflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
			} catch (IOException ioe) {
			} catch (DataFormatException e) {
			}
			return outputStream.toByteArray();
		}
}
