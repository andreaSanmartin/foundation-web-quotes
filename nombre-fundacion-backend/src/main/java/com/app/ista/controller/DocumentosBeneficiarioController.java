package com.app.ista.controller;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.ista.model.DocumentosBeneficiario;
import com.app.ista.service.DocumentosBeneficiarioService;

@RestController
@RequestMapping("/documentosBeneficiarios")
public class DocumentosBeneficiarioController {
	
    @Autowired
    DocumentosBeneficiarioService documentosBeneficiarioService;

    List<String> files = new ArrayList<String>();
	private final Path rootLocation = Paths.get("_Path_To_Save_The_File");
	

    @PostMapping(value = "/addDocumentos")
    public DocumentosBeneficiario guardarDocumentoBeneficiaros(@RequestBody DocumentosBeneficiario documentosBeneficiario){
    	DocumentosBeneficiario documentoCreado = documentosBeneficiarioService.guardarDocumentos(documentosBeneficiario);
        return documentoCreado;
    }
    
    @GetMapping("/porcedula/{cedulaPersona}")
    public List<DocumentosBeneficiario> porCedula(@PathVariable String cedulaPersona){
        return documentosBeneficiarioService.porCedula(cedulaPersona);
    }
    
    @GetMapping(path = "/listadoDocumentos", produces = "application/json")
    public List<DocumentosBeneficiario>listadoDocumentos(){
      return documentosBeneficiarioService.listardoDocumetnos();
    }
        
    @PutMapping("/updateDocumentos")
    public DocumentosBeneficiario actualizaDocumentos(@RequestBody DocumentosBeneficiario documentosBeneficiario){
        DocumentosBeneficiario documentoActualizado= documentosBeneficiarioService.porId(documentosBeneficiario.getIdDocumentos());
        documentoActualizado.setNombreArchivo(documentosBeneficiario.getNombreArchivo());
        documentoActualizado.setTipoDocumento(documentosBeneficiario.getTipoDocumento());
        documentosBeneficiarioService.guardardoc(documentoActualizado);
        return documentoActualizado;
    }
    
    @DeleteMapping("delete-documento/{idDocumentos}")
	 public ResponseEntity<Integer>eliminarDocumento(@PathVariable int idDocumentos){
   	documentosBeneficiarioService.eliminarDocumento(idDocumentos);
     return new ResponseEntity<>(idDocumentos, HttpStatus.OK);
	}
    
    //Ruta para guardar las imagenes
  	public static final String DIRECTORY = System.getProperty("user.home") + "/Documents/DocumentosBeneficiarios/";
  	
    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files")List<MultipartFile> multipartFiles) throws IOException {
        List<String> filenames = new ArrayList<>();
        for(MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(filename);
        }
        return ResponseEntity.ok().body(filenames);
    }
    
    @GetMapping("download/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }
    	
    @DeleteMapping("deletedoc/{filename}")
    public ResponseEntity<Resource> deletedFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        Files.delete(filePath);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }
    
    /**
     * metodo que despliega el folder/archivo
     * @param path el folder/archivo a desplegar
     */
    @GetMapping("ver/{filename}")
    private void showfolder(@PathVariable("filename") String filename)throws IOException {
    	Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        if (!Files.exists(filePath)) {
            System.out.printf("el archivo < %s > no existe", filePath.toString());
        } else {
            try {
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("explorer.exe "+filePath);
            } catch (IOException ex) {
            	System.out.println(ex);
            }
        }
    }
    
}