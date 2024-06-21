package com.community.fo.jpa.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_NESTEDCOMMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NestedCommentEntity {

	// 대댓글_순번
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nstdc_sq", nullable = false)
	private int nstdcSq;

	// 댓글_순번
	@ManyToOne
	@JoinColumn(name = "cmnt_sq")
	@JsonBackReference("comment-nestedComment")
	private CommentEntity commentEntity;

	// 회원_순번
	@ManyToOne
	@JoinColumn(name = "mbr_sq")
	private MemberEntity memberEntity;

	// 대댓글_내용
	@Column(name = "nstdc_cntnt", nullable = false, columnDefinition = "varchar(10000)")
	private String nstdcCntnt;

	// 등록_회원_순번
	@ManyToOne
	@JoinColumn(name = "insrt_mbr_sq", referencedColumnName = "mbr_sq")
	private MemberEntity insrtMbrSq;

	// 등록_일시
	@CreationTimestamp // 해당 필드가 DB에 삽입될 때 현재 시간으로 자동 설정됨
	@Column(name = "insrt_dtm", updatable = false) // 해당 필드는 저장된 값 이후에 변경, 수정되지 않도록 보장
	private LocalDateTime insrtDtm;

	// 수정_회원_순번
	@Column(name = "updt_mbr_sq", nullable = true)
	private Integer updtMbrSq;

	// 수정_일시
	@LastModifiedDate // 해당 필드가 DB에 저장되거나 업데이트될 때마다 현재 시간으로 업데이트
	@Column(name = "updt_dtm")
	private LocalDateTime updtDtm;

	// 삭제_회원_순번
	@Column(name = "dlt_mbr_sq", nullable = true)
	private Integer dltMbrSq;

	// 삭제_일시
	@LastModifiedDate // 해당 필드가 DB에 저장되거나 업데이트될 때마다 현재 시간으로 업데이트
	@Column(name = "dlt_dtm")
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
