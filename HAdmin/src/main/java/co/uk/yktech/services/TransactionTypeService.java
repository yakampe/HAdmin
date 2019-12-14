package co.uk.yktech.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.uk.yktech.dto.TransactionTypeDto;
import co.uk.yktech.models.TransactionType;
import co.uk.yktech.repositories.TransactionTypeRepository;

@Service
public class TransactionTypeService {

	@Autowired
	TransactionTypeRepository transactionTypeRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	public List<TransactionTypeDto> getAllEntities() {
		return ((List<TransactionType>) transactionTypeRepo.findAll())
				.stream()
				.map(tt -> convertToDto(tt))
				.collect(Collectors.toList());
	}

	public TransactionTypeDto newEntity(TransactionTypeDto tt) {
		return convertToDto(transactionTypeRepo.save(convertToEntity(tt)));
	}


	public TransactionTypeDto getEntityById(Long id) {
		return convertToDto(transactionTypeRepo.findById(id).get());
	}


	public TransactionTypeDto getEntityByTypeName(String typeName) {
		return convertToDto(transactionTypeRepo.getEntityByTypeName(typeName));
	}


	public void deleteEntityById(Long id) {
		transactionTypeRepo.deleteById(id);
	}


	public TransactionTypeDto updateEntity(TransactionTypeDto ttDto) {
		
		
		TransactionType tt = transactionTypeRepo.findById(ttDto.getId()).get();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.map(ttDto, tt);
		return convertToDto(transactionTypeRepo.save(tt));
	}
	
	private TransactionTypeDto convertToDto(TransactionType tt) {

		TransactionTypeDto ttDto = modelMapper.map(tt, TransactionTypeDto.class);
		
		ttDto.setCategory(tt.getTransactionTypeCategory().getCategoryName());		
		return ttDto;	
	}
	
	private TransactionType convertToEntity(TransactionTypeDto ttDto) {
		TransactionType tt = modelMapper.map(ttDto, TransactionType.class);
				
		return tt;
	}
	

}
