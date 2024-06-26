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
import jobplatform.fo.enterprise.domain.vo.ApplyDetailDataVO;
import jobplatform.fo.enterprise.service.ApplyManagementService;

@RestController
public class ApplyManagementController {
	
	private final ApplyManagementService applyManagementService;
	
	public ApplyManagementController(ApplyManagementService applyManagementService) {
		this.applyManagementService = applyManagementService;
	}
	
	@GetMapping("/test")
	public HttpStatus test() {
		System.out.println("성공");
		
		return HttpStatus.OK;
	}
	
	// 지원자 리스트 불러오기 (일반화 완료)
	@GetMapping("/applys/apply-list/{jbp_sq}/{division}/{condition}/{sort}/{pageNo}")
	public ResponseEntity<Map<String, Object>> findApplyListData(
			@PathVariable(name = "jbp_sq", required = false) int jbp_sq,
			@PathVariable(name = "division", required = false) String division,
			@PathVariable(name = "condition", required = false) int condition,
			@PathVariable(name = "sort", required = false) String sort,
			@PathVariable(name = "pageNo", required = false) int pageNo
			) {
		//검색 정보 VO (공고번호, 구분(지원apply / 제안proposal), 상태, 정렬)
		SearchListDataDTO searchListDataDTO = new SearchListDataDTO(jbp_sq, division, condition, sort, pageNo);
		
		Map<String, Object> map = applyManagementService.findApplyData(searchListDataDTO);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	// 지원자 상세 가져오기
	@GetMapping("/applys/applyDetail/{apy_sq}")
	public ResponseEntity<ApplyDetailDataVO> findApplyDetailData(@PathVariable(name = "apy_sq", required = false) int apy_sq) {
		//지원자 PK 번호 가져옴
		System.out.println("컨트롤 도착 : " + apy_sq);
		// 지원자 상세 데이터 받아오기
		ApplyDetailDataVO applyDetailData = applyManagementService.findApplyDetailData(apy_sq);
		
		return new ResponseEntity<ApplyDetailDataVO>(applyDetailData, HttpStatus.OK);
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
