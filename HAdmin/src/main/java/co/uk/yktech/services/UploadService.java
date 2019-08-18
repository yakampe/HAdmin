package co.uk.yktech.services;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import co.uk.yktech.models.MoneyTransaction;

@Service
public class UploadService {
	@Autowired
	MoneyTransactionService moneyTransactionService;

	public void upload(MultipartFile file, String type) {

		Logger logger = LoggerFactory.getLogger(getClass());

		List<MoneyTransaction> transactions = new ArrayList<>();

		try {
			Reader reader = new InputStreamReader(file.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> allRecords = csvReader.readAll();
			allRecords.forEach(line -> {
				if (type.equals("ND")) {
					MoneyTransaction mt = new MoneyTransaction();
					if (!line[0].toLowerCase().equals("pending")) {
						mt.setDate(LocalDate.parse(line[0], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
						mt.setDescription(line[1]);

						Double amount = Double.parseDouble(line[2]);
						if (amount > 0) {
							mt.setDebit(BigDecimal.valueOf(amount));
						} else {
							mt.setCredit(BigDecimal.valueOf(amount));
						}
						transactions.add(mt);
					}

				} else if (type.equals("LL")) {
					MoneyTransaction mt = new MoneyTransaction();
					logger.info(line[0]);
					mt.setDate(LocalDate.parse(line[0], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					logger.info(line[4]);
					mt.setDescription(line[4]);
					if (line[5].length() > 0) {
						logger.info(line[5]);
						mt.setDebit(BigDecimal.valueOf(Double.parseDouble(line[5])));
					} else {
						logger.info(line[6]);
						mt.setCredit(BigDecimal.valueOf(Double.parseDouble(line[6])));
					}
					transactions.add(mt);
				}

			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		moneyTransactionService.createTransactions(transactions);

	}

}
