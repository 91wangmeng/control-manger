package com.drore.cloud.control.manger.common.log.entity;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class BaseEntity implements Serializable{
    private static final long serialVersionUID = 7764802712795360005L;
    private String id;
}
