package br.ifsp.contacts.dto;


import br.ifsp.contacts.model.Contact;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactParametersDTO {
    @NotBlank(message = "O nome não pode estar vazio")
    private String nome;
    @Size(min = 8, max = 15, message = "O telefone deve ter entre 8 e 15 caracteres")
    @Pattern(regexp = "\\d+", message = "O telefone deve conter apenas números")
    private String telefone;
    @Email(message = "O email deve ter um formato válido")
    private String email;

    public Contact transformToObject() {
    	System.out.println("NOME:" + this.nome);
    	return new Contact(nome, telefone, email);
    }
    
}
