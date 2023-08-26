package trippers.triprecorder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trippers.triprecorder.entity.CardVO;
import trippers.triprecorder.entity.Expense;
import trippers.triprecorder.entity.SnsVO;
import trippers.triprecorder.entity.TripVO;
import trippers.triprecorder.repository.CardRepository;
import trippers.triprecorder.repository.ExpenseRepository;
import trippers.triprecorder.repository.SnsRepository;
import trippers.triprecorder.repository.TripRepository;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final TripRepository tripRepository;
    private final SnsRepository snsRepository;
    private final CardRepository cardRepository;
    /**
     * 1.경비 저장
     * @param tripNo
     * @param snsNo
     * @param cardNo
     * @return
     */
    public String saveExpense(Long tripNo, Long snsNo, Long cardNo){
        TripVO trip = tripRepository.findById(tripNo).orElse(null);
        SnsVO sns = snsRepository.findById(snsNo).orElse(null);
        CardVO card = cardRepository.findById(cardNo).orElse(null);

        if (trip == null || sns == null || card == null) {
            return "failed!";
        }

        expenseRepository.save(Expense.builder().trip(trip).sns(sns).card(card).build())
        return "success!";
    }

}
