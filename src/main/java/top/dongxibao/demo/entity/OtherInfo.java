package top.dongxibao.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName OtherInfo
 * @description test
 * @author Dongxibao
 * @date 2020/1/5
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class OtherInfo {
    private Long phone;
    private String address;
}
