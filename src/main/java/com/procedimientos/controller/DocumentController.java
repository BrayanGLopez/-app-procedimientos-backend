package com.procedimientos.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.procedimientos.dto.CodigoDto;
import com.procedimientos.dto.DocumentCreateDto;
import com.procedimientos.dto.DocumentResponseDto;
import com.procedimientos.service.DocumentService;

@RestController
@RequestMapping(value = "/document")
public class DocumentController {
	
	@Autowired
	private DocumentService documentService;
	
	@GetMapping(value = "")
	public ResponseEntity<List<DocumentResponseDto>> getAllDocuments(){
		return new ResponseEntity<>(documentService.getAllDocuments(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DocumentResponseDto> getDocumentById(@PathVariable(name = "id") Long id){
		return new ResponseEntity<>(documentService.getDocumentById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<String> saveDocument( @RequestParam(name = "file") MultipartFile file,
												@RequestParam(name = "nameClient") String nameClient,
												@RequestParam(name = "nameProduct") String nameProduct,
												@RequestParam(name = "nameType") String nameType ) throws IOException{		
		return new ResponseEntity<>(documentService.saveDocument(file, nameClient, nameProduct, nameType), HttpStatus.OK);
	}
	
	@GetMapping(value = "/load/{id}")
	public ResponseEntity<Resource> loadDocument(@PathVariable(name = "id") Long id){
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_TYPE, "application/pdf")
				.body(documentService.loadDocument(id));
				
	}
	
	@PostMapping(value = "")
	public ResponseEntity<DocumentResponseDto> createDocument(@RequestBody DocumentCreateDto documentCreateDto){		
		return new ResponseEntity<>(documentService.createDocument(documentCreateDto), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/end")
	public ResponseEntity<CodigoDto> getEndCodDocument(){
		return new ResponseEntity<>(documentService.getEndCodDocument(), HttpStatus.OK);
	}
	
	@PutMapping(value = "")
	public ResponseEntity<DocumentResponseDto> updateDocument( @RequestParam(name = "file") MultipartFile file,
															   @RequestParam(name = "idDocument") Long id){
		
		return new ResponseEntity<>(documentService.updateDocument(file, id), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<DocumentResponseDto> deleteDocument(@PathVariable(name = "id") Long id){
		return new ResponseEntity<>(documentService.deleteDocument(id), HttpStatus.OK);
	}
	
	
}
