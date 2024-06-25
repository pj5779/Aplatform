package jobplatform.fo.enterprise.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jobplatform.fo.enterprise.common.Pagination;
import jobplatform.fo.enterprise.domain.dto.SearchListDataDTO;
import jobplatform.fo.enterprise.domain.mapper.ApplyMapper;
import jobplatform.fo.enterprise.domain.mapper.CommonCodeMapper;
import jobplatform.fo.enterprise.domain.vo.ApplyListVO;
import jobplatform.fo.enterprise.domain.vo.CommonCodeVO;

@Service
public class ApplyManagementService {
	
	private final ApplyMapper applyMapper;
	private final CommonCodeMapper commonCodeMapper;
	
	public ApplyManagementService(ApplyMapper applyMapper, CommonCodeMapper commonCodeMapper) {
		this.applyMapper = applyMapper;
		this.commonCodeMapper = commonCodeMapper;
	}
	
	// 지원자 리스트 불러오기(정렬, 초기화면 일반화)
	public Map<String, Object> findApplyData(SearchListDataDTO searchListDataDTO) {		
		// 총 데이터 갯수
		int totalCount= applyMapper.loadApplyListCount(searchListDataDTO);
		
		// 1페이지당 표시할 데이터 숫자
		int showDataCountPerPage = 10;
		// 페이지네이션 그룹의 숫자
		int showPageGroupsCount = 5;
		// 현재 페이지 번호 (첫화면이라 1)
		int pageNo = 1;
		// 페이지네이션 정보 생성
		Pagination pagination = new Pagination(totalCount, showDataCountPerPage, pageNo, showPageGroupsCount);
			
		Map<String, Object> map = new HashMap<String, Object>();
		//페이지네이션 정보 map 추가
		map.put("paginationData", pagination);
		
		// 검색/정렬 정보 map 추가
		map.put("searchListData", searchListDataDTO);
		
		//-----------------------에러!!!--------------------------------------------------------------
		// 지원자 리스트 정보 얻기
		List<ApplyListVO> applyListVO = applyMapper.selectApplyListData(map);
		
		// 지원자 리스트 정보 map 추가
		map.put("applyDatas", applyListVO);
		
		// 코드 정보 가져오기 (지원취소 빼고)		
		List<CommonCodeVO> commonCodeListVO = commonCodeMapper.selectCommonCodeApplyCategoryData();
		//코드 정보 map 추가
		map.put("applyConditions", commonCodeListVO);

		return map;
	}
}
