package com.drore.cloud.control.manger.common.base.domain.qo;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/8/28.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
@Data
public class PageQO {
    @JSONField(name = "page_size")
    @SerializedName(value = "page_size")
    private int pageSize;
    @JSONField(name = "current_page")
    @SerializedName(value = "current_page")
    private int currentPage;
}
