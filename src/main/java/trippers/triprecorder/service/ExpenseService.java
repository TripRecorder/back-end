package trippers.triprecorder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import trippers.triprecorder.dto.ExpenseDTO;
import trippers.triprecorder.dto.ExpenseListDTO;
import trippers.triprecorder.entity.CardVO;
import trippers.triprecorder.entity.Expense;
import trippers.triprecorder.entity.SnsVO;
import trippers.triprecorder.entity.TripVO;
import trippers.triprecorder.repository.CardRepository;
import trippers.triprecorder.repository.ExpenseRepository;
import trippers.triprecorder.repository.SnsRepository;
import trippers.triprecorder.repository.TripRepository;

import java.util.ArrayList;
import java.util.List;

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
    public String saveExpense(ExpenseDTO expenseDTO){
        //존재하면 set하는 걸로 코드 수정 orElse
        TripVO trip = tripRepository.findById(expenseDTO.getId()).orElse(null);

        if (trip == null) {
            return "failed!";
        }
        //dto를 entity로 만든다음에 저장
        Expense expense = ExpenseDTO.toExpense();
        snsRepository.findById(expenseDTO.getSns().getSnsNo()).ifPresent(sns -> {
            expense.setSns(sns);
        });

        cardRepository.findById(expenseDTO.getCard().getCardNo()).ifPresent(card ->{
            expense.setCard(card);
        });
        expenseRepository.save(expense);
        return "success!";
    }

    /**
     * 2.연결된 게시글이 없는 경비 조회
     * @param tripNo
     * @return
     */
    public List<ExpenseDTO> findExpenseList(Long tripNo){
        TripVO trip = tripRepository.findById(tripNo).orElse(null);
        List<Expense> expense = expenseRepository.findByTripAndSnsNullOrderByExpNoDesc(trip);
        List<ExpenseDTO> expenseDTOS = new ArrayList<>();
        for(Expense expen : expense){
            expenseDTOS.add(ExpenseDTO.builder().expenseName(expen.getExpenseName())
                            .id(expen.getId())
                            .build());
        }
        return expenseDTOS;
    }

    /**
     * 경비 리스트 조회하는 메서드
     * @param tripNo
     * @return
     */
    public ExpenseListDTO seeExpenseList(Long tripNo){
        TripVO trip = tripRepository.findById(tripNo).orElse(null);
        List<ExpenseDTO> tmpExp = expenseRepository.findByTrip(trip, Sort.by("time"));

        List<ExpenseDTO> expList = new ArrayList<>();
        Long tripExpense = tripRepository.findById(tripNo).orElse(null).getTripExp();
        Long useExpense = 0L;

        for (ExpenseDTO expenseDTO : tmpExp) {
            ExpenseDTO e = ExpenseDTO.builder()
                    .id(expenseDTO.getId())
                    .expenseName(expenseDTO.getExpenseName())
                    .place(expenseDTO.getPlace())
                    .money(expenseDTO.getMoney())
                    .time(expenseDTO.getTime())
                    .build();

            expList.add(e);
            useExpense += expenseDTO.getMoney();
        }

        ExpenseListDTO expenseListDTO = new ExpenseListDTO();
        expenseListDTO.setTripExpense(tripExpense);
        expenseListDTO.setUseExpense(useExpense);
        expenseListDTO.setRemainExpense((tripExpense - useExpense));
        expenseListDTO.setExpenseDTO(expList);
        return expenseListDTO;
    }


    //게시글 등록할 떄 게시글이랑 경비가 일대일
    //여행에 해당하지만

}
