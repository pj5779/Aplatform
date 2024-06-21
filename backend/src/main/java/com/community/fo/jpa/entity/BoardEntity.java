package com.community.fo.jpa.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_BOARD")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardEntity {

	// 게시판_순번
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "brd_sq", nullable = false)
	private int brdSq;

	// 게시판_유형_코드
	@Column(name = "brd_ctgry_cd", nullable = false, columnDefinition = "varchar(20)")
	private String brdCtgryCd;

	// 게시판_제목
	@Column(name = "brd_tl", nullable = false, columnDefinition = "varchar(100)")
	private String brdTl;

	// 게시판_내용
	@Column(name = "brd_cntnt", nullable = false, columnDefinition = "varchar(1000)")
	private String brdCntnt;

	// 게시판_답변_체택_체크
	@ColumnDefault("false")
	@Column(name = "brd_answr_slctn_chck", nullable = false)
	private boolean brdAnswrSlctnChck;

	// 게시판 조회수
	@ColumnDefault("0")
	@Column(name = "brd_hits", nullable = false)
	private int brdHits;

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
	
	// 파일 이름
    @Lob
    @Column(name = "file_name")
    private String fileName; // 파일 이름 저장 필드 추가
   
    // 파일 경로
    @Lob
    @Column(name = "file_path", nullable = true)
    private String filePath;
	
	//양방향 관계에서 사용, 관계의 반대쪽 필드 지정하고 변경사항은 자식 엔티티에도 적용
	@OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL)
	@JsonManagedReference("board-comment")
	private List<CommentEntity> commentEntity;
	
	//양방향 관계에서 사용, 관계의 반대쪽 필드 지정하고 변경사항은 자식 엔티티에도 적용
	@OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL)
	@JsonManagedReference("board-answer")
	private List<AnswerEntity> answerEntity;
}
