package me.paulojr.spbk;

import java.text.SimpleDateFormat;
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
import me.paulojr.spbk.domain.ItemPedido;
import me.paulojr.spbk.domain.Pagamento;
import me.paulojr.spbk.domain.PagamentoComBoleto;
import me.paulojr.spbk.domain.PagamentoComCartao;
import me.paulojr.spbk.domain.Pedido;
import me.paulojr.spbk.domain.Produto;
import me.paulojr.spbk.domain.enums.EstadoPagamento;
import me.paulojr.spbk.domain.enums.TipoCliente;
import me.paulojr.spbk.repositories.CategoriaRepository;
import me.paulojr.spbk.repositories.CidadeRepository;
import me.paulojr.spbk.repositories.ClienteRepository;
import me.paulojr.spbk.repositories.EnderecoRepository;
import me.paulojr.spbk.repositories.EstadoRepository;
import me.paulojr.spbk.repositories.ItemPedidoRepository;
import me.paulojr.spbk.repositories.PagamentoRepository;
import me.paulojr.spbk.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedrepo;
	@Autowired
	private PagamentoRepository pagrepo;
	@Autowired
	private ItemPedidoRepository iprepo;

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		crepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		prepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));






		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		Cidade c1 = new Cidade(null, "Uberlândia", e1);
		Cidade c2 = new Cidade(null, "São Paulo", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "Tabajara Empreendimentos LTDA", "contato@tabajara.com", "04546220000176",
				TipoCliente.PESSOAJURIDICA);
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

		erepo.saveAll(Arrays.asList(e1, e2));
		cdrepo.saveAll(Arrays.asList(c1, c2, c3));
		clirepo.saveAll(Arrays.asList(cli1, cli2));
		endrepo.saveAll(Arrays.asList(end1, end2, end3, end4));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		Pedido ped3 = new Pedido(null, sdf.parse("09/08/2017 11:24"), cli2, end3);
		Pedido ped4 = new Pedido(null, sdf.parse("25/11/2017 13:42"), cli2, end4);

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		Pagamento pag3 = new PagamentoComCartao(null, EstadoPagamento.PENDENTE, ped3, 2);
		Pagamento pag4 = new PagamentoComBoleto(null, EstadoPagamento.CANCELADO, ped4, sdf.parse("23/11/2017 00:00"),
				null);

		ped1.setPagamento(pag1);
		ped2.setPagamento(pag2);
		ped3.setPagamento(pag3);
		ped4.setPagamento(pag4);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		cli2.getPedidos().addAll(Arrays.asList(ped3, ped4));

		pedrepo.saveAll(Arrays.asList(ped1, ped2, ped3, ped4));
		pagrepo.saveAll(Arrays.asList(pag1, pag2, pag3, pag4));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		ItemPedido ip4 = new ItemPedido(ped3, p3, 50.00, 3, 80.00);
		ItemPedido ip5 = new ItemPedido(ped3, p2, 20.00, 1, 800.00);
		ItemPedido ip6 = new ItemPedido(ped4, p1, 10.00, 2, 2000.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip2));

		ped3.getItens().addAll(Arrays.asList(ip4, ip5));
		ped4.getItens().addAll(Arrays.asList(ip6));

		p1.getItens().addAll(Arrays.asList(ip1, ip6));
		p2.getItens().addAll(Arrays.asList(ip3, ip5));
		p3.getItens().addAll(Arrays.asList(ip2, ip4));

		iprepo.saveAll(Arrays.asList(ip1, ip2, ip3, ip4, ip5, ip6));

	}

}
