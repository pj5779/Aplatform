package com.community.fo.jpa.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.LastModifiedDate;

import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_TERMS_CONTENT")
@Data
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TermsContentEntity {

   // 약관_순번
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "trms_sq", nullable = false)
   private int trmsSq;

   // 약관_유형_코드
   @Column(name = "trms_ctgry_cd", nullable = true, columnDefinition = "varchar(20)")
   private String trmsCtgryCd;
   
   // 약관_내용
   @Column(name = "trms_cntnt", nullable = true, columnDefinition = "varchar(10000)")
   private String trmsCntnt;

   // 등록_회원_순번
   @Column(name = "insrt_mbr_sq", nullable = true)
   private Integer insrtMbrSq;

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
