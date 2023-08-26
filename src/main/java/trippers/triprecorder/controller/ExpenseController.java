package trippers.triprecorder.controller;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import trippers.triprecorder.dto.ExpenseDTO;
import trippers.triprecorder.service.ExpenseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expense")
public class ExpenseController {
	ExpenseService expenseService;

	// 경비등록
	@PostMapping("/register")
	public String ocr(@RequestBody ExpenseDTO expenseDTO) throws JsonProcessingException {
		return expenseService.saveExpense(expenseDTO.getTrip().getTripNo(), expenseDTO.getSns().getSnsNo(), expenseDTO.getCard().getCardNo());
	}

	// 연결된 게시글이 없는 경비 조회
//	@PostMapping("/list/{tripNo}")
//	public List<JSONObject> postExpList(@PathVariable Long tripNo) {
//		TripVO trip = trepo.findById(tripNo).orElse(null);
//		List<Expense> tmpExpList = erepo.findByTripAndSnsNullOrderByExpNoDesc(trip);
//
//		List<JSONObject> expList = new ArrayList<>();
//		tmpExpList.forEach(exp -> {
//			JSONObject obj = new JSONObject();
//			obj.put("expNo", exp. getId());
//			obj.put("expTitle", exp.getExpenseName());
//			expList.add(obj);
//		});
//
//		return expList;
//	}
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
//	@GetMapping("/{tripNo}/list")
//	public JSONObject getExpList(@PathVariable Long tripNo) {
//		TripVO trip = trepo.findById(tripNo).orElse(null);
//		List<Expense> tmpExp = erepo.findByTrip(trip, Sort.by("expTime"));
//
//		List<ExpSimpleDto> expList = new ArrayList<>();
//		Long tripExp = trepo.findById(tripNo).orElse(null).getTripExp();
//		Long useExp = 0L;
//
//		for (int i = 0; i < tmpExp.size(); i++) {
//			Expense exp = tmpExp.get(i);
//
//			ExpSimpleDto e = ExpSimpleDto.builder().expNo(exp.getExpNo()).expTitle(exp.getExpTitle())
//					.expPlace(exp.getExpPlace()).expMoney(exp.getExpMoney()).expTime(exp.getExpTime()).build();
//
//			expList.add(e);
//			useExp += exp.getExpMoney();
//		}
//
//		JSONObject expObj = new JSONObject();
//		expObj.put("tripExp", tripExp);
//		expObj.put("useExp", useExp);
//		expObj.put("remainExp", (tripExp - useExp));
//		expObj.put("exp", expList);
//		return expObj;
//	}
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
