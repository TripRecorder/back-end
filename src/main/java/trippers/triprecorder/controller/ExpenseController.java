package trippers.triprecorder.controller;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import trippers.triprecorder.dto.ExpenseDTO;
import trippers.triprecorder.dto.ExpenseListDTO;
import trippers.triprecorder.entity.TripVO;
import trippers.triprecorder.repository.ExpenseRepository;
import trippers.triprecorder.repository.TripRepository;
import trippers.triprecorder.service.ExpenseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expense")
public class ExpenseController {
	private final ExpenseService expenseService;

	private final TripRepository tripRepository;
	private final ExpenseRepository expenseRepository;

	// 경비등록
	@PostMapping("/register")
	public String expenseRegister(@RequestBody ExpenseDTO expenseDTO) { // DTO를 한 번에 호출하지 말고 DTO를 한번에 넣어주기
		return expenseService.saveExpense(expenseDTO);
	}

	// 연결된 게시글이 없는 경비 조회
	@GetMapping("/notConnectSns/{tripNo}")//주소 수정 getMapping으로 바꾸기 함수 메서드 수정
	public List<ExpenseDTO> notConnectSnsAndExpenseLists(@PathVariable Long tripNo) {
		//TripVO trip = trepo.findById(tripNo).orElse(null);
	//	List<Expense> tmpExpList = erepo.findByTripAndSnsNullOrderByExpNoDesc(trip);
//
//		List<JSONObject> expList = new ArrayList<>();
//		tmpExpList.forEach(exp -> {
//			JSONObject obj = new JSONObject();
//			obj.put("expNo", exp. getId());
//			obj.put("expTitle", exp.getExpenseName());
//			expList.add(obj);
//		});

		return expenseService.findExpenseList(tripNo);
	}
//
//	// 게시글과 연동된 경비 정보
//	@PostMapping("/sns/{expNo}")
//	public JSONObject postExpWithSns(@PathVariable Long expNo) {
//		Expense tmpExp = erepo.findById(expNo).orElse(null);
//
//		JSONObject exp = new JSONObject();
//		exp.put("expTitle", tmpExp.getExpTitle());
//		exp.put("expPlace", tmpExp.getExpPlace());
//		exp.put("expMoney", tmpExp.getExpMoney());
//		exp.put("expNo", tmpExp.getExpNo());
//
//		return exp;
//	}
//
//	// 경비 리스트
	@GetMapping("/{tripNo}/list")
	public ExpenseListDTO seeExpenseList(@PathVariable Long tripNo) {
		return expenseService.seeExpenseList(tripNo);
	}
//
//	// 경비 상세보기
//	@GetMapping("/detail/{expNo}")
//	public ExpInfoDto getExpDetail(@PathVariable Long expNo) {
//		Expense tmpExp = erepo.findById(expNo).orElse(null);
//		ExpInfoDto exp = ExpInfoDto.builder().expNo(tmpExp.getExpNo()).expTitle(tmpExp.getExpTitle())
//				.tripNo(tmpExp.getTrip().getTripNo()).expPlace(tmpExp.getExpPlace()).expAddress(tmpExp.getExpAddress())
//				.expMoney(tmpExp.getExpMoney()).expTime(tmpExp.getExpTime()).expWay(tmpExp.getExpWay())
//				.expCate(tmpExp.getExpCate()).build();
//
//		if (tmpExp.getCard() != null) {
//			exp.setCardNo(tmpExp.getCard().getCardNo());
//		}
//		if (tmpExp.getSns() != null) {
//			exp.setSnsNo(tmpExp.getSns().getSnsNo());
//		}
//
//		return exp;
//	}
//
//	// 경비 삭제
//	@DeleteMapping("/delete/{expNo}")
//	public String deleteExp(@PathVariable Long expNo) {
//		erepo.deleteById(expNo);
//		return "OK";
//	}
}
