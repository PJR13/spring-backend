package me.paulojr.spbk.services.validation;

import me.paulojr.spbk.domain.Cliente;
import me.paulojr.spbk.domain.enums.TipoCliente;
import me.paulojr.spbk.dto.ClienteDTO;
import me.paulojr.spbk.dto.ClienteNewDTO;
import me.paulojr.spbk.repositories.ClienteRepository;
import me.paulojr.spbk.resources.exceptions.FieldMessage;
import me.paulojr.spbk.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteInsert, ClienteDTO> {
    @Autowired
    private ClienteRepository repo;

    @Autowired
    private HttpServletRequest rqst;

    @Override
    public void initialize(ClienteInsert constraintAnnotation) { }
    @Override
    public boolean isValid(ClienteDTO value, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) rqst.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> errors = new ArrayList<>();
        Cliente cli = repo.findByEmail(value.getEmail());
        if(cli != null && !cli.getId().equals(uriId)){
            errors.add(new FieldMessage("email", "Email já existente"));
        }

        errors.forEach(fieldMessage -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
        });
        return errors.isEmpty();
    }
}
