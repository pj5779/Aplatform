package com.community.fo.jpa.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_COMMON_CODE_CATEGORY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonCodeCategoryEntity {
	
	// 코드_유형_아이디
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code_ctgry_id")
	@Id
	private int codeCtgryId; 
	
	// 코드유형명
	@Column(name = "code_ctgry_name")
	private String codeCtgryName;
	
	// 코드유형 영문명
	@Column(name = "code_ctgry_eng_name")
	private String codeCtgryEngName;
	
	// 등록회원순번
	@Column(name = "insrt_mbr_sq")
	private int insrtMbrSq;
	
	// 등록일시
	@CreationTimestamp //해당 필드가 DB에 삽입될 때 현재 시간으로 자동 설정됨
	@Column(name = "insrt_dtm")
	private LocalDateTime insrtDtm;
	
	// 수정회원순번
	@Column(name = "updt_mrd_sq")
	private int updtMrdSq;
	
	// 수정일시
	@LastModifiedDate //해당 필드가 DB에 저장되거나 업데이트될 때마다 현재 시간으로 업데이트
	@Column(name = "updt_dtm")
	private LocalDateTime updtDtm;
	
	// 삭제회원순번
	@Column(name = "dlt_dtm_sq")
	private int dltDtmSq;
	
	// 삭제일시
	@LastModifiedDate //해당 필드가 DB에 저장되거나 업데이트될 때마다 현재 시간으로 업데이트
	@Column(name = "dlt_dtm")
	private LocalDateTime dltDtm;
	
	// 삭제여부
	@ColumnDefault("false")
	@Column(name = "dlt_chck")
	private boolean dltChck;
	
	// 사용여부
	@ColumnDefault("true")
	@Column(name = "use_chck")
	private boolean useChck;
	
}
