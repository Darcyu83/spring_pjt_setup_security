package com.yuds.spring_yuds.common.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass // 해당 클래스를 상속할 경우 이 클래스의 필드들을 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) //이 클래스에 Auditing기능을 포함 시킨다.
public abstract class BaseTimeEntity {

  @CreatedDate // 엔티티 생성 후 저장때 시간이 자동 저장됨.
  private LocalDateTime createdAt;

  @LastModifiedDate // 조회된 엔티티의 값이 변경될 때 시간이 자동 저장 됨.
  private LocalDateTime updatedAt;
}
