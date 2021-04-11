package ro.dan.dragu.account.management.service;

import ro.dan.dragu.account.management.service.model.TransactionData;

import java.time.temporal.ChronoUnit;
import java.util.List;

public interface TransactionService {

    List<TransactionData> getForLastTimeFrame(String userName, Integer timeFrame, ChronoUnit timeUnit);
}
