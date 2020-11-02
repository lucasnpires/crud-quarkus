package com.github.lucasnpires.resource;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.github.lucasnpires.model.entity.Produto;
import com.github.lucasnpires.model.request.ProdutoRequest;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

	@GET
	public List<Produto> buscarTodosProdutos() {
		return Produto.listAll();
	}

	@POST
	@Transactional
	public void criarProduto(ProdutoRequest produtoRequest) {

		Produto p = new Produto();
		p.setNome(produtoRequest.getNome());
		p.setValor(produtoRequest.getValor());
		p.persist();
	}

	@PUT
	@Path("{id}")
	@Transactional
	public void alterarProduto(@PathParam("id") Long id, ProdutoRequest produtoRequest) {

		Optional<Produto> produtoOp = Produto.findByIdOptional(id);

		if (produtoOp.isPresent()) {
			Produto produto = produtoOp.get();
			produto.setNome(produtoRequest.getNome());
			produto.setValor(produtoRequest.getValor());
			produto.persist();
		} else {
			throw new NotFoundException();
		}
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public void alterarProduto(@PathParam("id") Long id) {

		Optional<Produto> produtoOp = Produto.findByIdOptional(id);
		produtoOp.ifPresentOrElse(Produto::delete, () -> {
			throw new NotFoundException();
		});

	}
}
