package com.showaye.microappointment.dao;

import com.showaye.microappointment.model.entity.CategoryDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: 类别mapper接口
 * @Author HuangShiming
 * @Date 2018/2/5
 */
public interface CategoryMapper {

    List<CategoryDetail> findAllTypes();

    Map<String, Integer> findCategoryIdByTypeId(@Param("typeId") Integer typeId);
}
