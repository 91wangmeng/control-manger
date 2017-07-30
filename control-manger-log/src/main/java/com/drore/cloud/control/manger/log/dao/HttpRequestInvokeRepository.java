package com.drore.cloud.control.manger.log.dao;

import com.drore.cloud.control.manger.log.domain.HttpRequestLogMongoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月23日 22:51 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public interface HttpRequestInvokeRepository extends MongoRepository<HttpRequestLogMongoEntity, String> {
    /**
     * Find http request log mongo entities by parent id page.
     *
     * @param parentId the parent id
     * @param pageable the pageable
     * @return the page
     */
    Page<HttpRequestLogMongoEntity> findHttpRequestLogMongoEntitiesByParentId(String parentId, Pageable pageable);
}
