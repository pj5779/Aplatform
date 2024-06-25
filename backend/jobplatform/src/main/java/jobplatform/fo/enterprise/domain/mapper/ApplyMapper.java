package jobplatform.fo.enterprise.domain.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jobplatform.fo.enterprise.domain.dto.SearchListDataDTO;
import jobplatform.fo.enterprise.domain.vo.ApplyListVO;

@Mapper
public interface ApplyMapper {
	
	// 총 지원자 숫자 받아오기
	int loadApplyListCount(SearchListDataDTO searchListDataDTO);
	// 지원자 리스트 얻기
	ApplyListVO selectApplyListData(Map<String, Object> map);


}
