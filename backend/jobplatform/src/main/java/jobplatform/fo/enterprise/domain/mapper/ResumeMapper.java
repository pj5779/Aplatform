package jobplatform.fo.enterprise.domain.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import jobplatform.fo.enterprise.domain.dto.ResumeSearchDataDTO;
import jobplatform.fo.enterprise.domain.vo.ResumeListVO;

@Mapper
public interface ResumeMapper {

	// 페이지네이션을 위한 데이터 수 얻기
	int loadResumeListCount(ResumeSearchDataDTO resumeSearchDataDTO);
	// 이력서 리스트 데이터 얻기
	ResumeListVO selectResumeData(Map<String, Object> map);

}
