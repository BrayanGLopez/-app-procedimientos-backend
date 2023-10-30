package com.procedimientos.serviceImp;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.procedimientos.dto.CodigoDto;
import com.procedimientos.dto.DocumentCreateDto;
import com.procedimientos.dto.DocumentResponseDto;
import com.procedimientos.dto.DocumentoResponseProductDto;
import com.procedimientos.exceptions.ResourceNotFoundException;
import com.procedimientos.model.Client;
import com.procedimientos.model.Document;
import com.procedimientos.model.Engineer;
import com.procedimientos.model.Product;
import com.procedimientos.model.Type;
import com.procedimientos.repository.ClientRepository;
import com.procedimientos.repository.DocumentRepository;
import com.procedimientos.repository.EngineerRepository;
import com.procedimientos.repository.ProductRepository;
import com.procedimientos.repository.TypeRepository;
import com.procedimientos.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private TypeRepository typeRepository;
	@Autowired
	private EngineerRepository engineerRepository;
	
	@Override
	public DocumentoResponseProductDto convertDocumentToDocumentResponseProductDto(Document document) {
		return DocumentoResponseProductDto.builder()
				.idDocument(document.getIdDocument())
				.nameDocument(document.getNameDocument())
				.typeDocument(document.getType().getNameType())
				.build();
	}

	@Override
	public Document convertDocumentCreateDtoToDocument(DocumentCreateDto documentCreateDto) {
		return Document.builder()
					.codigoDocument(documentCreateDto.getCodigoDocument())
					.urlDocument(documentCreateDto.getUrlDocument())
					.product(Product.builder()
								.idProduct(documentCreateDto.getProduct().getIdProduct())
								.nameProduct(documentCreateDto.getProduct().getNameProduct())
								.client(Client.builder()
											.idClient(documentCreateDto.getProduct().getClient().getIdClient())
											.nameClient(documentCreateDto.getProduct().getClient().getNameClient())
											.build())
								.build())
					.type(Type.builder()
							.idType(documentCreateDto.getType().getIdType())
							.nameType(documentCreateDto.getType().getNameType())
							.build())
					.engineer(Engineer.builder()
								.idEngineer(documentCreateDto.getEngineer().getIdEngineer())
								.nameEngineer(documentCreateDto.getEngineer().getNameEngineer())
								.build())
					.build();
	}

	@Override
	public DocumentResponseDto convertDocumentToDocumentResponseDto(Document document) {
		return DocumentResponseDto.builder()
					.idDocument(document.getIdDocument())
					.nameDocument(document.getNameDocument())
					.codigoDocument(document.getCodigoDocument())
					.dateCreate(document.getDateCreate())
					.dateUpdate(document.getDateUpdate())
					.urlDocument(document.getUrlDocument())
					.nameClient(document.getProduct().getClient().getNameClient())
					.nameProduct(document.getProduct().getNameProduct())
					.type(document.getType().getNameType())
					.nameEngineer(document.getEngineer().getNameEngineer())
					.build();
	}

	@Override
	public List<DocumentResponseDto> getAllDocuments() {
		return documentRepository.findAll()
					.stream()
					.map( document -> convertDocumentToDocumentResponseDto(document))
					.collect(Collectors.toList());
	}

	@Override
	public DocumentResponseDto getDocumentById(Long id) {
		Document documentAux = documentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Documento", "id", id.toString())); 
		return convertDocumentToDocumentResponseDto(documentAux);
	}
	
	@Override
	public String saveDocument(MultipartFile file, String nameClient, String nameProduct, String nameType) throws IOException {
		
		String route = "D:/Procedimientos/";
		String nameDocument = "";
		File directory;
		
		route +=  nameClient + "/" + nameProduct;
		
		nameDocument = nameType + "_" + nameClient + "_" + nameProduct + ".pdf";
		
		nameDocument = nameDocument.replace(" ", "_");
		
		route = route.replace(" ", "_");
		
		directory = new File(route);
		
		if(!directory.exists()){
			if(!directory.mkdirs()){
				System.out.println("Error al crear la carpeta");
			}
		}
		
		try {
			
			byte[] bytes = file.getBytes();
			Path path = Paths.get(route + "/" + nameDocument);
			Files.write(path, bytes);
			
			return path.toUri().toString();
		} catch (IOException e) {
			throw new IOException(e.getMessage() + "->");
		}
		
		
	}
	
	@Override
	public Resource loadDocument(Long id) {
		
		try {
			
			Document document = documentRepository.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("Documento", "id", id.toString()));
			
			Path file = Paths.get(document.getUrlDocument());
			Resource resource = new UrlResource((file.toUri()));
			
			if(resource.exists() || resource.isReadable()){
				return resource;
			}else{
				throw new RuntimeException("No se encontro el archivo " + document.getNameDocument());
			}
			
		} catch (MalformedURLException e) {
			throw new RuntimeException("No se encontro el archivo con id " + id.toString());
		}
	}
	
	@Override
	public DocumentResponseDto createDocument(DocumentCreateDto documentCreateDto){
		
		String route = "D:/Procedimientos/";
		String nameDocument = "", urlDocument = "";
		Document documentAux = convertDocumentCreateDtoToDocument(documentCreateDto);
		Optional<Client> clientOptional;
		Optional<Product> productOptional;
		Optional<Type> typeOptional;
		Optional<Engineer> enginnerOptional;
		Client client;
		Product product;
		Type type;
		Engineer engineer;
		
		route +=  documentCreateDto.getProduct().getClient().getNameClient() + "/"
					+ documentCreateDto.getProduct().getNameProduct();
		
		nameDocument = documentCreateDto.getType().getNameType() + "_"
						+ documentCreateDto.getProduct().getClient().getNameClient() + "_"
						+ documentCreateDto.getProduct().getNameProduct() + ".pdf";
		
		nameDocument = nameDocument.replace(" ", "_");
		
		urlDocument = route + "/" + nameDocument;
		
		urlDocument = urlDocument.replace(" ", "_");
										
		clientOptional = clientRepository.findById(documentCreateDto.getProduct().getClient().getIdClient());
		
		if(!clientOptional.isPresent()){
			client = clientRepository.save(Client.builder()
					.idClient(documentCreateDto.getProduct().getClient().getIdClient())
					.nameClient(documentCreateDto.getProduct().getClient().getNameClient())
					.build());
		}else {
			client = clientOptional.get();
		}
		
		productOptional = productRepository.findById(documentCreateDto.getProduct().getIdProduct());
		
		if(!productOptional.isPresent()){
			product = productRepository.save(Product.builder()
					.idProduct(documentCreateDto.getProduct().getIdProduct())
					.nameProduct(documentCreateDto.getProduct().getNameProduct())
					.client(client)
					.build());
		}else {
			product = productOptional.get();
		}
		
		typeOptional = typeRepository.findById(documentCreateDto.getType().getIdType());
		
		if(!typeOptional.isPresent()){
			type = typeRepository.save(Type.builder()
					.idType(documentCreateDto.getType().getIdType())
					.nameType(documentCreateDto.getType().getNameType())
					.build());
		}else{
			type = typeOptional.get();
		}
		
		enginnerOptional = engineerRepository.findById(documentCreateDto.getEngineer().getIdEngineer());
		
		if(!enginnerOptional.isPresent()){
			engineer = engineerRepository.save(Engineer.builder()
					.idEngineer(documentCreateDto.getEngineer().getIdEngineer())
					.nameEngineer(documentCreateDto.getEngineer().getNameEngineer())
					.build());
		}else {
			engineer = enginnerOptional.get();
		}
				
		documentAux.setNameDocument(nameDocument);
		documentAux.setDateCreate(LocalDate.now());
		documentAux.setDateUpdate(LocalDate.now());
		documentAux.setUrlDocument(urlDocument);
		documentAux.setProduct(product);
		documentAux.setType(type);
		documentAux.setEngineer(engineer);
		
		
		return convertDocumentToDocumentResponseDto(documentRepository.save(documentAux));
	}
	

	@Override
	public DocumentResponseDto updateDocument(MultipartFile file, Long id){
		
		Document document = documentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Document", "id", id.toString()));
		
		document.setDateUpdate(LocalDate.now());
		
		try {
			saveDocument(file,
					document.getProduct().getClient().getNameClient(),
					document.getProduct().getNameProduct(),
					document.getType().getNameType());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return convertDocumentToDocumentResponseDto(documentRepository.save(document));
	}

	@Override
	public DocumentResponseDto deleteDocument(Long id) {
		
		Document document = documentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Document", "id", id.toString()));
				
		documentRepository.deleteById(id);
		
		if(document.getProduct().getDocuments().size() < 1) {
			productRepository.deleteById(document.getProduct().getIdProduct());
			
			if(document.getProduct().getClient().getProducts().size() < 1) {
				clientRepository.deleteById(document.getProduct().getClient().getIdClient());
			}
		}
		
		return convertDocumentToDocumentResponseDto(document);
	}

	@Override
	public CodigoDto getEndCodDocument() {
		return CodigoDto
				.builder()
				.cod(documentRepository.getEndCodDocument())
				.build();
	}

}
