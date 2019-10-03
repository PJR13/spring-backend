package me.paulojr.spbk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import me.paulojr.spbk.model.Categoria;
import me.paulojr.spbk.model.Cidade;
import me.paulojr.spbk.model.Estado;
import me.paulojr.spbk.model.Produto;
import me.paulojr.spbk.repositories.CategoriaRepository;
import me.paulojr.spbk.repositories.CidadeRepository;
import me.paulojr.spbk.repositories.EstadoRepository;
import me.paulojr.spbk.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackendApplication.class, args);
	}

	@Autowired
	private CategoriaRepository crepo;
	@Autowired
	private ProdutoRepository prepo;
	@Autowired
	private CidadeRepository cdrepo;
	@Autowired
	private EstadoRepository erepo;

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		Cidade c1 = new Cidade(null, "Uberlândia", e1);
		Cidade c2 = new Cidade(null, "São Paulo", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2,c3));
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		crepo.saveAll(Arrays.asList(cat1, cat2));
		prepo.saveAll(Arrays.asList(p1, p2, p3));
		erepo.saveAll(Arrays.asList(e1, e2));
		cdrepo.saveAll(Arrays.asList(c1,c2,c3));
	}

}
