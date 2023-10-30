package com.procedimientos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.procedimientos.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{
	
	@Query(value = "SELECT codigo_document\r\n"
			+ "  FROM documents\r\n"
			+ "  WHERE id_document = (\r\n"
			+ "	SELECT Max(id_document)\r\n"
			+ "	FROM documents\r\n"
			+ "  )", nativeQuery = true)
	public String getEndCodDocument();
}
