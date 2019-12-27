package me.paulojr.spbk.services.validation;

import me.paulojr.spbk.domain.enums.TipoCliente;
import me.paulojr.spbk.dto.ClienteNewDTO;
import me.paulojr.spbk.resources.exceptions.FieldMessage;
import me.paulojr.spbk.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
    @Override
    public void initialize(ClienteInsert constraintAnnotation) { }
    @Override
    public boolean isValid(ClienteNewDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> errors = new ArrayList<>();
        if (value.getTipo().equals(TipoCliente.PESSOAFISICA.getId()) && !BR.isValidCPF(value.getCpfOuCnpj())) {
            errors.add(new FieldMessage("cpfOuCnpj", "CPF inválido."));
        }
        if (value.getTipo().equals(TipoCliente.PESSOAJURIDICA.getId()) && !BR.isValidCNPJ(value.getCpfOuCnpj())) {
            errors.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido."));
        }
        errors.forEach(fieldMessage -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
        });
        return errors.isEmpty();
    }
}
