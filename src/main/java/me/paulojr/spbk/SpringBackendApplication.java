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



	@Override
	public void run(String... args) throws Exception {


	}

}
