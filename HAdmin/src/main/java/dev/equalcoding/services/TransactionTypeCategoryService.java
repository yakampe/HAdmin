package dev.equalcoding.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.equalcoding.dto.TransactionTypeCategoryDto;
import dev.equalcoding.models.TransactionType;
import dev.equalcoding.models.TransactionTypeCategory;
import dev.equalcoding.repositories.TransactionTypeCategoryRepository;
import dev.equalcoding.repositories.TransactionTypeRepository;

@Service
public class TransactionTypeCategoryService {

	@Autowired
	TransactionTypeCategoryRepository transactionTypeCategoryRepo;
	
	@Autowired
	TransactionTypeRepository transactionTypeRepo;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<TransactionTypeCategoryDto> getAllEntities() {
		return ((List<TransactionTypeCategory>) transactionTypeCategoryRepo.findAll())
				.stream()
				.map(ttc -> convertToDto(ttc))
				.collect(Collectors.toList());
	}

	public TransactionTypeCategoryDto newEntity(TransactionTypeCategoryDto ttcDto) {
		return convertToDto(transactionTypeCategoryRepo.save(convertToEntity(ttcDto)));
	}

	public TransactionTypeCategoryDto getEntityById(Long id) {
		return convertToDto(transactionTypeCategoryRepo.findById(id).get());
	}

	public TransactionTypeCategoryDto getEntityByCategoryName(String categoryName) {
		return convertToDto(transactionTypeCategoryRepo.getTransactionTypeCategoryByName(categoryName));
	}

	public void deleteEntityById(Long id) {
		transactionTypeCategoryRepo.deleteById(id);
	}

	
	@Transactional
	public TransactionTypeCategoryDto updateEntity(TransactionTypeCategoryDto ttcDto) {
	
		TransactionTypeCategory ttc = transactionTypeCategoryRepo.findById(ttcDto.getId()).get();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.map(ttcDto, ttc);
		
		//go through DTO names and populate the TTC pojo with the entities and set those entities
		if(ttc.getTransactionTypes() != null && ttcDto.getTransactionTypes().size() != ttc.getTransactionTypes().size()) {
			ttc.setTransactionTypes(
					ttcDto.getTransactionTypes()
					.stream()
					.map(typeString -> transactionTypeRepo.getEntityByTypeName(typeString))
					.map(tt-> {
						tt.setTransactionTypeCategory(ttc);
						return transactionTypeRepo.save(tt);
					})
					.collect(Collectors.toSet()));
		};
		
		
		return convertToDto(transactionTypeCategoryRepo.save(ttc));				
	}

	private TransactionTypeCategoryDto convertToDto(TransactionTypeCategory ttc) {

		TransactionTypeCategoryDto ttcDto = modelMapper.map(ttc, TransactionTypeCategoryDto.class);
		ttcDto.setTransactionTypes(ttc.getTransactionTypes() != null ?
				ttc.getTransactionTypes()
				.stream()
				.map(TransactionType::getTypeName)
				.collect(Collectors.toSet()) : null);
		
		return ttcDto;	
	}
	
	private TransactionTypeCategory convertToEntity(TransactionTypeCategoryDto ttcDto) {
		TransactionTypeCategory ttc = modelMapper.map(ttcDto, TransactionTypeCategory.class);
				
		return ttc;
	}

	
	
}
