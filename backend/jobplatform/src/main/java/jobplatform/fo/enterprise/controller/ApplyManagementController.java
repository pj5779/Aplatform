package jobplatform.fo.enterprise.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jobplatform.fo.enterprise.domain.dto.SearchListDataDTO;
import jobplatform.fo.enterprise.service.ApplyManagementService;

@RestController
public class ApplyManagementController {
	
	private final ApplyManagementService applyManagementService;
	
	public ApplyManagementController(ApplyManagementService applyManagementService) {
		this.applyManagementService = applyManagementService;
	}
	
	// 지원자 리스트 불러오기ResponseEntity<Map<String, Object>>
	@GetMapping("/applys/apply-list/{jbp_sq}/{division}/{condition}/{sort}")
	public ResponseEntity<Map<String, Object>> findApplyListData(
			@PathVariable(name = "jbp_sq", required = false) int jbp_sq,
			@PathVariable(name = "division", required = false) String division,
			@PathVariable(name = "condition", required = false) int condition,
			@PathVariable(name = "sort", required = false) String sort
			) {
		//검색 정보 VO (공고번호, 구분(지원apply / 제안proposal), 상태, 정렬)
		SearchListDataDTO searchListDataDTO = new SearchListDataDTO();
		// set 해야함.
		searchListDataDTO.setJbp_sq(jbp_sq);
		searchListDataDTO.setDivision(division);
		searchListDataDTO.setCondition(condition);
		searchListDataDTO.setSort(sort);
		
		System.out.println(jbp_sq + " / " + division + " / " + condition + " / " + sort);

		
		Map<String, Object> map = applyManagementService.findApplyData(searchListDataDTO);

		
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	// 지원자 상세 가져오기
	@GetMapping("/applys/applyDetail/{apy_sq}")
	public void findApplyDetailData(@PathVariable(name = "apy_sq", required = false) int apy_sq) {
		System.out.println("컨트롤 도착 : " + apy_sq);
		// 지원자 PK를 받아와서 이력서PK 알아내고-> 그것으로 정보 받아와서 처리 
		// 쿼리 rsm_sq 일반화때문에 이력서로 가져와야할수도? 
	}
	
	// 지원자 상태변경 
	@PatchMapping("/applys/condition/{apy_sq}/{apy_cndtn}")
	public void modifyApplyCondition(
			@PathVariable(name = "apy_sq", required = false) int apy_sq,
			@PathVariable(name = "apy_cndtn", required = false) String apy_cndtn,
			@RequestParam String Date
			) {
		System.out.println("컨트롤 도착 : " + apy_sq + " / " + apy_cndtn);
		System.out.println("날짜 : " + Date);
		//상태 변경		
	}
	
}
