package com.procedimientos.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.procedimientos.dto.CodigoDto;
import com.procedimientos.dto.DocumentCreateDto;
import com.procedimientos.dto.DocumentResponseDto;
import com.procedimientos.dto.DocumentoResponseProductDto;
import com.procedimientos.model.Document;

public interface DocumentService {
	public Document convertDocumentCreateDtoToDocument(DocumentCreateDto documentCreateDto);
	public DocumentResponseDto convertDocumentToDocumentResponseDto(Document document);
	public DocumentoResponseProductDto convertDocumentToDocumentResponseProductDto(Document document);
	public List<DocumentResponseDto> getAllDocuments();
	public DocumentResponseDto getDocumentById(Long id);
	public DocumentResponseDto createDocument(DocumentCreateDto documentCreateDto);
	public String saveDocument(MultipartFile file, String nameClient, String nameProduct, String nameType) throws IOException;
	public Resource loadDocument(Long id);
	public DocumentResponseDto updateDocument(MultipartFile file, Long id);
	public DocumentResponseDto deleteDocument(Long id);
	public CodigoDto getEndCodDocument();
}
