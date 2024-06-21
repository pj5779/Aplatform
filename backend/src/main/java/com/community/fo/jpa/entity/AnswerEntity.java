package com.community.fo.jpa.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
@Table(name = "TBL_ANSWER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerEntity {
   
   // 답변_순번
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "answr_sq", nullable = false)
   private int answrSq;
   
   // 게시판_순번
   @ManyToOne
   @JoinColumn(name = "brd_sq")
   @JsonBackReference("board-answer")
   private BoardEntity boardEntity;
   
   // 회원_순번
   @ManyToOne
   @JoinColumn(name = "mbr_sq")
   private MemberEntity memberEntity;
   
   // 답변_내용
   @Column(name="answr_cntnt", nullable = false, columnDefinition = "varchar(10000)")
   private String answrCntnt;
   
   // 답변_채택_체크
   @ColumnDefault("false")
   @Column(name = "answr_slctn_chck", nullable = false)
   private Boolean answrSlctnChck;
   
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
   
   @OneToMany(mappedBy = "answerEntity", cascade = CascadeType.ALL)
   @JsonManagedReference("answer-comment")
   private List<CommentEntity> CommentEntity;
   
}
