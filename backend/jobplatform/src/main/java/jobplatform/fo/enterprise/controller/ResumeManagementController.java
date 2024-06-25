package jobplatform.fo.enterprise.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jobplatform.fo.enterprise.service.ResumeManagementService;

@RestController
public class ResumeManagementController {
	
	private final ResumeManagementService resumeManagementService;
	
	public ResumeManagementController(ResumeManagementService resumeManagementService) {
		this.resumeManagementService = resumeManagementService;
	}
	
	// 이력서 목록 불러오기 (정렬 일반화)
	@GetMapping("/resumes/resume-list/{mbr_sq}/{sort}")
	public void findResumeListData(
			@PathVariable("mbr_sq") int mbr_sq,
			@PathVariable(name = "sort", required = false) String sort
			) {
		System.out.println("컨트롤 도착 : " + mbr_sq);
	}
	// 이력서 상세 불러오기
	@GetMapping("/resumes/resumeDetail/{rsm_sq}")
	public void findResumeDetailData(@PathVariable("rsm_sq") int rsm_sq) {
		System.out.println("컨트롤 도착 : " + rsm_sq);
		// 지원자 상세와 일반화하면 좋음
	}
	
	// 이력서 등록
	@PostMapping("/resumes")
	public void inputResumeFullData(@RequestBody String Data) {
		
	}
	
	// 이력서 수정	
	@PatchMapping("/resumes/{rsm_sq}")
	public void modifyResumeFullData(
			@PathVariable("rsm_sq") int rsm_sq,
			@RequestBody String Data
			) {
		System.out.println("컨트롤 도착 : " + rsm_sq);
		// 여기가 쫌 문제./././././
		
	}

	// 이력서 삭제
	@DeleteMapping("/resumes/{rsm_sq}")
	public void deleteResumeFullData(@PathVariable("rsm_sq") int rsm_sq) {
		System.out.println("컨트롤 도착 : " + rsm_sq);
		// 하위들 까지 전부다 YN
		
	}
	
	/////////////////////////// 리스트 페이지 기능 ///////////////////////////////////
	// 대표이력서 수정
	@PatchMapping("/resumes/representative/{rsm_sq}")
	public void chageResumeRepresentative(@PathVariable("rsm_sq") int rsm_sq) {
		System.out.println("컨트롤 도착 : " + rsm_sq);
		// 변경로직 -> Y인걸 전부 N 시키고 , 보낸PK를 Y로
		
	}
	
	// 복제
	@PostMapping("/resumes/add")
	public void addResumeFullData(@RequestBody String Data) {
		
	}
	
	
	
}
