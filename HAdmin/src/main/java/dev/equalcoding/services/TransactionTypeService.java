package dev.equalcoding.services;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.equalcoding.dto.TransactionTypeDto;
import dev.equalcoding.models.TransactionType;
import dev.equalcoding.models.TransactionTypeCategory;
import dev.equalcoding.repositories.TransactionTypeCategoryRepository;
import dev.equalcoding.repositories.TransactionTypeRepository;

@Service
public class TransactionTypeService {

	@Autowired
	TransactionTypeRepository transactionTypeRepo;

	@Autowired
	TransactionTypeCategoryRepository transactionTypeCategoryRepository;
	
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
		
		//TODO: when updating entity if category is different this needs to be changed,.
		
			
		//if setting up a category from empty
		if(tt.getTransactionTypeCategory() == null && Strings.trimToNull(ttDto.getCategory()) != null) {
			//check that category already exists
			TransactionTypeCategory newTtc = transactionTypeCategoryRepository.getTransactionTypeCategoryByName(ttDto.getCategory());
			if(newTtc != null) {
				tt.setTransactionTypeCategory(newTtc);
				
			}
			
		} else if(tt.getTransactionTypeCategory() != null && !tt.getTransactionTypeCategory().getCategoryName().equals(ttDto.getCategory())) {
			TransactionTypeCategory newTtc = transactionTypeCategoryRepository.getTransactionTypeCategoryByName(ttDto.getCategory());
			if(newTtc != null) {
				tt.setTransactionTypeCategory(newTtc);
				
			}
		}
		
		return convertToDto(transactionTypeRepo.save(tt));
	}
	
	private TransactionTypeDto convertToDto(TransactionType tt) {

		TransactionTypeDto ttDto = modelMapper.map(tt, TransactionTypeDto.class);
		
		ttDto.setCategory(tt.getTransactionTypeCategory() != null ? tt.getTransactionTypeCategory().getCategoryName() : null);		
		return ttDto;	
	}
	
	private TransactionType convertToEntity(TransactionTypeDto ttDto) {
		TransactionType tt = modelMapper.map(ttDto, TransactionType.class);
				
		return tt;
	}
	

}
