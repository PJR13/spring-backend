package me.paulojr.spbk.services;

import me.paulojr.spbk.domain.Cidade;
import me.paulojr.spbk.domain.Cliente;
import me.paulojr.spbk.domain.Endereco;
import me.paulojr.spbk.domain.enums.TipoCliente;
import me.paulojr.spbk.dto.ClienteDTO;
import me.paulojr.spbk.dto.ClienteNewDTO;
import me.paulojr.spbk.repositories.ClienteRepository;
import me.paulojr.spbk.repositories.EnderecoRepository;
import me.paulojr.spbk.services.exceptions.DataIntegrityException;
import me.paulojr.spbk.services.exceptions.IllegalDirectionValueException;
import me.paulojr.spbk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository repoEnd;


	public Cliente buscar(int i) {
		Optional<Cliente> cli = repo.findById(i);
		return cli.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ID: " + i + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> listar() {
		return repo.findAll();
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		repoEnd.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = buscar(obj.getId());
		return repo.save(updateData(newObj, obj));
	}

	public Page<Cliente> findPage(Integer i, Integer lines, String orderBy, String direction) {
		Direction d;
		try {
			d = Direction.valueOf(direction);
		} catch (IllegalArgumentException e) {
			throw new IllegalDirectionValueException("Parametro de direção (direction) inválido, use DESC ou ASC.");
		}
		PageRequest pageRequest = PageRequest.of(i, lines, d, orderBy);
		return repo.findAll(pageRequest);
	}

	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar um Cliente que possui pedidos.");
		}

	}

	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cli = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()));
		Cidade city = new Cidade(dto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cli, city);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(dto.getTelefone1());
		if (dto.getTelefone2() != null) cli.getTelefones().add(dto.getTelefone2());
		if (dto.getTelefone3() != null) cli.getTelefones().add(dto.getTelefone3());
		return cli;
	}

	private Cliente updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		return newObj;
	}

}
