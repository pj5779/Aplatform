package jobplatform.fo.enterprise.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import jobplatform.fo.enterprise.common.Pagination;
import jobplatform.fo.enterprise.domain.dto.ResumeSearchDataDTO;
import jobplatform.fo.enterprise.domain.mapper.ResumeMapper;
import jobplatform.fo.enterprise.domain.vo.ResumeListVO;

@Service
public class ResumeManagementService {

	private final ResumeMapper resumeMapper;

	public ResumeManagementService(ResumeMapper resumeMapper) {
		this.resumeMapper = resumeMapper;
	}
	
	//이력서 리스트 데이터 얻기
	public Map<String, Object> findResumeData(ResumeSearchDataDTO resumeSearchDataDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 총 데이터 갯수
		int totalCount= resumeMapper.loadResumeListCount(resumeSearchDataDTO);

		// 페이지네이션 정보 생성 (기본 1페이지 = 데이터10 / 페이지그룹= 페이지5)
		Pagination pagination = new Pagination(totalCount, resumeSearchDataDTO.getPageNo());
			
		//페이지네이션 정보 map 추가
		map.put("paginationData", pagination);
		
		// 검색/정렬 정보 map 추가
		map.put("searchData", resumeSearchDataDTO);
		// 여기가 에러!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//이력서 리스트 정보 얻기
		ResumeListVO resumeListVO = resumeMapper.selectResumeData(map);
		System.out.println(resumeListVO);
		// 이력서 리스트 정보 map 추가
		map.put("resumeDatas", resumeListVO);
		
		return map;
	}
	
}