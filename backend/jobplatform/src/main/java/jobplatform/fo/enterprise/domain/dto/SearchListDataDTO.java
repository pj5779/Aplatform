package jobplatform.fo.enterprise.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchListDataDTO {
	// 공고번호
	private int jbp_sq;
	// 구분
	private String division;
	//상태
	private int condition;
	//정렬
	private String sort;
}
