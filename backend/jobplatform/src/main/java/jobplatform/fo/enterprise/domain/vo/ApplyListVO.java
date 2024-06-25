package jobplatform.fo.enterprise.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyListVO {
	// 지원자 순번
		private int apy_sq;
		// 지원 일시
		private int apy_dtm;
		//지원 상태
		private int apy_cndtn;
		
		// 이력서 순번
		private int rsm_sq;
		// 이력서 이미지 주소
		private String rsm_img_file_url;
		// 이력서 대표여부
		private String rsm_rprsntv_yn;
		// 이력서 최종학력 코드 한글화
		private String rsm_fnl_edctn;
		// 이력서 학점
		private int rsm_grd;
		// 이력서 희망연봉
		private int rsm_es;
		// 이력서 이름
		private String rsm_name;
		// 구분 (가상컬럼)
		private int rnqns;

}
