package com.yuds.spring_yuds.hospital.entity;


import com.yuds.spring_yuds.common.entity.BaseTimeEntity;
import com.yuds.spring_yuds.common.enums.HospitalColorSetting;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@Table(name = "hospital_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class HospitalInfo extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "hospital_id", nullable = false)
  private Long hospitalId;

  @Size(max = 255)
  @Column(name = "hospital_code")
  private String hospitalCode;

  @Size(max = 100)
  @Column(name = "name", length = 100)
  private String name;

  @Size(max = 100)
  @Column(name = "business_no", length = 100)
  private String businessNo;

  @Size(max = 30)
  @Column(name = "ceo", length = 30)
  private String ceo;

  @Size(max = 255)
  @Column(name = "address")
  private String address;

  @Size(max = 255)
  @Column(name = "tel_number")
  private String telNumber;

  @Size(max = 100)
  @Column(name = "email", length = 100)
  private String email;

  @Size(max = 150)
  @Column(name = "company_name", length = 150)
  private String companyName;

  @Size(max = 150)
  @Column(name = "corporation_no", length = 150)
  private String corporationNo;

  @Size(max = 100)
  @Column(name = "license_kind", length = 100)
  private String licenseKind;

  @Size(max = 100)
  @Column(name = "license_no", length = 100)
  private String licenseNo;

  @Size(max = 100)
  @Column(name = "department", length = 100)
  private String department;

  @NotNull
  @ColumnDefault("0")
  @Column(name = "baruda_point", nullable = false)
  private Integer barudaPoint;

  @ColumnDefault("0")
  @Column(name = "use_sns")
  private Boolean useSns;

  @Size(max = 255)
  @Column(name = "photo_1_url")
  private String photo1Url;

  @Size(max = 255)
  @Column(name = "photo_2_url")
  private String photo2Url;

  @Size(max = 255)
  @Column(name = "photo_3_url")
  private String photo3Url;

  @Size(max = 255)
  @Column(name = "blog")
  private String blog;

  @Size(max = 255)
  @Column(name = "instagram")
  private String instagram;

  @Size(max = 255)
  @Column(name = "facebook")
  private String facebook;

  @Size(max = 255)
  @Column(name = "homepage")
  private String homepage;

  @Column(name = "lat")
  private Double lat;

  @Column(name = "lon")
  private Double lon;

  @Column(name = "use_chart")
  private Integer useChart;

  @ColumnDefault("0")
  @Column(name = "is_test")
  private Boolean isTest;

  @Size(max = 255)
  @Column(name = "kakao_channel")
  private String kakaoChannel;

  @ColumnDefault("'STATUS'")
  @Enumerated(value = EnumType.STRING)
  @Column(name = "color_setting")
  private HospitalColorSetting colorSetting;

  @Size(max = 255)
  @Column(name = "best_field")
  private String bestField;

  @Column(name = "operation_info", columnDefinition = "TEXT")
  private String operationInfo;

  @Column(name = "remark", columnDefinition = "TEXT")
  private String remark;

  @Size(max = 50)
  @NotNull
  @ColumnDefault("'07088062729'")
  @Column(name = "solapi_send_num", nullable = false, length = 50)
  private String solapiSendNum;

//  @OneToMany(mappedBy = "hospitalInfo", fetch = FetchType.LAZY)
//  private TimeTable timeTable;
}