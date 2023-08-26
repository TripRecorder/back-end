package trippers.triprecorder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import trippers.triprecorder.entity.CardVO;
import trippers.triprecorder.entity.Expense;
import trippers.triprecorder.entity.SnsVO;
import trippers.triprecorder.entity.TripVO;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseDTO {
	private String place;
	private String address;
	private Long money;
	private String time;
	private TripVO trip;
	private SnsVO sns;
	private CardVO card;

	public Expense toExpenseDTO(String place, String address, Long money){
		return Expense.builder()
				.money(money)
				.place(place)
				.address(address)
				.trip(trip)
				.card(card)
				.sns(sns)
				.build();
	}
}
