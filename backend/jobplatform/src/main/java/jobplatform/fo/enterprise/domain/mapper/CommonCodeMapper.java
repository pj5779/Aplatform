package jobplatform.fo.enterprise.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jobplatform.fo.enterprise.domain.vo.CommonCodeVO;

@Mapper
public interface CommonCodeMapper {

	//지원상태 정보 얻기
	List<CommonCodeVO> selectCommonCodeApplyCategoryData();

}
