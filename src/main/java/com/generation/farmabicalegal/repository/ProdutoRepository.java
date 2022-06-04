package com.generation.farmabicalegal.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;

import com.generation.farmabicalegal.model.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome); //BUSCA POR NOME

	public List<Produto> findAllByLaboratorioContainingIgnoreCase(@Param("laboratorio") String laboratorio); //BUSCA POR LABORATÓRIO
	
	public List<Produto> findByNomeAndQuantidade(@Param("nome") String nome, Integer quantidade); //BUSCA POR NOME EM ORDEM CRESCENTE E A QUANTIDADE
	
	public List<Produto> findByLaboratorioOrPreco(@Param("laboratorio") String laboratorio, BigDecimal preco); // BUSCA POR LABORATORIO OU PREÇO
	
	public List<Produto> findByPrecoBetween(BigDecimal inicio, BigDecimal fim); // BUSCA POR INTERVALO DE PREÇOS
}
