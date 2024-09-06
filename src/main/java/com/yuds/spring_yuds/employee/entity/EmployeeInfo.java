package com.yuds.spring_yuds.employee.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yuds.spring_yuds.common.entity.BaseTimeEntity;
import com.yuds.spring_yuds.common.enums.EmployeeLevel;
import com.yuds.spring_yuds.hospital.entity.HospitalInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@Table(name = "employee_info")
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeInfo extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employee_id", nullable = false)
  private Long employeeId;

  @ColumnDefault("0")
  @Column(name = "delete_flag", nullable = false)
  @Max(1)
  @Min(0)
  private Integer deleteFlag = 0;

  @Size(max = 50)
  @NotNull
  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @NotNull
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY, optional = false) // 연관관계 무조건 있어야 함.
  @JoinColumn(name = "hospital_id", nullable = false)
  private HospitalInfo hospitalInfo;

//  @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
//  private List<ReservationInfo> reservationsAsDoctor = new ArrayList<>();
//
//  @OneToMany(mappedBy = "healer", fetch = FetchType.LAZY)
//  private List<ReservationInfo> reservationsAsHealer = new ArrayList<>();

  @Size(max = 50)
  @NotNull
  @Column(name = "login_id", nullable = false, length = 50)
  private String loginId;

  @Size(max = 255)
  @NotNull
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "sex")
  private Integer sex;

  @Column(name = "duty")
  private Integer duty;

  @Column(name = "sa")
  private Integer sa;

  @Column(name = "is_exposure")
  private Integer isExposure;

  @Size(max = 30)
  @Column(name = "phone", length = 30)
  private String phone;

  @Size(max = 200)
  @Column(name = "email", length = 200)
  private String email;

  @Size(max = 255)
  @Column(name = "tag_data")
  private String tagData;

  @Lob
  @Column(name = "self_memo", columnDefinition = "TEXT")
  private String selfMemo;

  @Size(max = 255)
  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "emp_seq")
  private Integer empSeq;

  @Size(max = 100)
  @Column(name = "writer", length = 100)
  private String writer;

  @Column(name = "refresh_token", columnDefinition = "TEXT")
  private String refreshToken;

  @ColumnDefault("0")
  @Column(name = "is_enable")
  private Boolean isEnable;

  @NotNull
  @ColumnDefault("'USER'")
  @Column(name = "employee_level", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private EmployeeLevel employeeLevel;

  @ColumnDefault("0")
  @Column(name = "is_rep")
  private Boolean isRep;

  public void changePassword(String password) {
    this.password = password;
  }
}