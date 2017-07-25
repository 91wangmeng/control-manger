package com.drore.cloud.control.manger.common.base.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/7/20.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
@Data
@ToString
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 7764802712795360005L;
    @JSONField(name = "id")
    @Id
    private String id;
    @JSONField(name = "create_time")
    private long createTime;

}
