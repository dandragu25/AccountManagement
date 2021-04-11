package ro.dan.dragu.account.management.service.mapper;

import org.springframework.stereotype.Service;
import ro.dan.dragu.account.management.dal.entity.Transaction;
import ro.dan.dragu.account.management.service.model.TransactionData;

import java.time.format.DateTimeFormatter;

@Service
public class TransactionDataMapper extends ObjectMapper<Transaction, TransactionData> {

    @Override
    public TransactionData mapTo(Transaction source) {
        return new TransactionData(source.getId(), source.getAmount(), source.getTimestamp().format(DateTimeFormatter.ISO_DATE), source.getDetails());
    }

    @Override
    public Transaction mapFrom(TransactionData source) {
       throw new UnsupportedOperationException();
    }
}
