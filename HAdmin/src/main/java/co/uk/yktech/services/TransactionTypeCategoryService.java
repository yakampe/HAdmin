package co.uk.yktech.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.uk.yktech.dto.TransactionTypeCategoryDto;
import co.uk.yktech.models.TransactionType;
import co.uk.yktech.models.TransactionTypeCategory;
import co.uk.yktech.repositories.TransactionTypeCategoryRepository;

@Service
public class TransactionTypeCategoryService {

	@Autowired
	TransactionTypeCategoryRepository transactionTypeCategoryRepo;
	
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

	public TransactionTypeCategoryDto updateEntity(TransactionTypeCategoryDto ttcDto) {
	
		TransactionTypeCategory ttc = transactionTypeCategoryRepo.findById(ttcDto.getId()).get();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.map(ttcDto, ttc);
		
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
