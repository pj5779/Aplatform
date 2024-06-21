package com.community.fo.jpa.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_COMMON_CODE")
@Data
@DynamicInsert // null 값을 제외하고 insert 시켜줌
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCodeEntity {

	// 외래 키로 사용되는 필드
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "code_ctgry_id")
	private CommonCodeCategoryEntity commonCodeCategoryEntity;
	
	// 코드_순번
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code_sq")
	private int codeSq;

	// 코드_아이디
	@Column(name = "code_id")
	private int codeId;
	
	// 코드_명
	@Column(name = "code_name", nullable = false, columnDefinition = "varchar(20)")
	private String codeName;
	
	// 코드_영문명
	@Column(name = "code_eng_name", nullable = false, columnDefinition = "varchar(20)")
	private String codeEngName;

	// 등록_회원_순번
	@Column(name = "insrt_mbr_sq", nullable = false)
	private int insrtMbrSq;

	// 등록_일시
	@CreationTimestamp // 해당 필드가 DB에 삽입될 때 현재 시간으로 자동 설정됨
	@Column(name = "insrt_dtm", updatable = false) // 해당 필드는 저장된 값 이후에 변경, 수정되지 않도록 보장
	private LocalDateTime insrtDtm;

	// 수정_회원_순번
	@Column(name = "updt_mbr_sq", nullable = true)
	private int updtMbrSq;

	// 수정_일시
	@LastModifiedDate // 해당 필드가 DB에 저장되거나 업데이트될 때마다 현재 시간으로 업데이트
	@Column(name = "updt_dtm", nullable = true)
	private LocalDateTime updtDtm;

	// 삭제_회원_순번
	@Column(name = "dlt_mbr_sq", nullable = true)
	private int dltMbrSq;

	// 삭제_일시
	@LastModifiedDate // 해당 필드가 DB에 저장되거나 업데이트될 때마다 현재 시간으로 업데이트
	@Column(name = "dlt_dtm", nullable = true)
	private LocalDateTime dltDtm;

	// 삭제_여부
	@ColumnDefault("false")
	@Column(name = "dlt_chck", nullable = false)
	private Boolean dltChck;

	// 사용_여부
	@ColumnDefault("true")
	@Column(name = "use_chck", nullable = false)
	private Boolean useChck;

}
