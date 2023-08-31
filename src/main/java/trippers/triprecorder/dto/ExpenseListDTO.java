package trippers.triprecorder.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseListDTO {
    private Long tripExpense;
    private Long useExpense;
    private Long remainExpense;
    private List<ExpenseDTO> expenseDTO;
}
