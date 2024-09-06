package com.yuds.spring_yuds.auth.dto;

import com.yuds.spring_yuds.employee.entity.EmployeeInfo;
import com.yuds.spring_yuds.common.enums.EmployeeLevel;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmpTokenPayloadDto {


  private String name;
  private Long hospital_id;
  private Long employee_id;
  private String login_id;
  private Integer duty;
  private Boolean is_enable;
  private String image_url;
  private EmployeeLevel employee_level;

  public static EmpTokenPayloadDto fromEntity(EmployeeInfo employeeInfo) {
    return new EmpTokenPayloadDto(
        employeeInfo.getName(),
        employeeInfo.getHospitalInfo().getHospitalId(),
        employeeInfo.getEmployeeId(),
        employeeInfo.getLoginId(),
        employeeInfo.getDuty(),
        employeeInfo.getIsEnable(),
        employeeInfo.getImageUrl(),
        employeeInfo.getEmployeeLevel()
    );
  }

  // JWT 만들기
  public Map<String, Object> getClaims() {
    Map<String, Object> dataMap = new HashMap<>();

    dataMap.put("name", name);
    dataMap.put("hospital_id", hospital_id);
    dataMap.put("employee_id", employee_id);
    dataMap.put("login_id", login_id);
    dataMap.put("duty", duty);
    dataMap.put("is_enable", is_enable);
    dataMap.put("image_url", image_url);
    dataMap.put("employee_level", employee_level);

    return dataMap;
  }
}
