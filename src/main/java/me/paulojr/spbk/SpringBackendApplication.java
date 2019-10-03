package me.paulojr.spbk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import me.paulojr.spbk.domain.Categoria;
import me.paulojr.spbk.domain.Cidade;
import me.paulojr.spbk.domain.Cliente;
import me.paulojr.spbk.domain.Endereco;
import me.paulojr.spbk.domain.Estado;
import me.paulojr.spbk.domain.Produto;
import me.paulojr.spbk.domain.enums.TipoCliente;
import me.paulojr.spbk.repositories.CategoriaRepository;
import me.paulojr.spbk.repositories.CidadeRepository;
import me.paulojr.spbk.repositories.ClienteRepository;
import me.paulojr.spbk.repositories.EnderecoRepository;
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
	@Autowired
	private ClienteRepository clirepo;
	@Autowired
	private EnderecoRepository endrepo;

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
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "Tabajara Empreendimentos LDTA", "contato@tabajara.com", "04546220000176", TipoCliente.PESSOAJURIDICA);
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco end3 = new Endereco(null, "Rua Exemplo", "41", "Apto 14", "Centro", "32146597", cli2, c2);
		Endereco end4 = new Endereco(null, "Avenida 01", "72", "Sala 551", "Centro", "45678912", cli2, c3);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		cli2.getTelefones().addAll(Arrays.asList("87452136", "96875421"));
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		cli2.getEnderecos().addAll(Arrays.asList(end3, end4));
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2, c3));
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		crepo.saveAll(Arrays.asList(cat1, cat2));
		prepo.saveAll(Arrays.asList(p1, p2, p3));
		erepo.saveAll(Arrays.asList(e1, e2));
		cdrepo.saveAll(Arrays.asList(c1, c2, c3));
		clirepo.saveAll(Arrays.asList(cli1, cli2));
		endrepo.saveAll(Arrays.asList(end1, end2, end3, end4));

	}

}
