package br.ifsp.address.dto;

import java.util.ArrayList;
import java.util.List;


import br.ifsp.contacts.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressResponseDTO {
	private String rua;
	private String cidade;
	private String estado;
	private String cep;
	
	public static AddressResponseDTO transformInDto(Address addr) {
		return new AddressResponseDTO(addr.getRua(), addr.getCidade(), addr.getEstado(), addr.getCep());
	}
	
	public static List<AddressResponseDTO> transformListDto(List<Address> addr){
		List<AddressResponseDTO> dtoList = new ArrayList<>();
		for(Address address : addr) {
			dtoList.add(AddressResponseDTO.transformInDto(address));
		}
		return dtoList;
	}
}
