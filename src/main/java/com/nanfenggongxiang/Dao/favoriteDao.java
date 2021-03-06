package com.nanfenggongxiang.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by skyisbule on 2018/4/11.
 */
@Mapper
public interface favoriteDao {

    @Select("select commodity.*,favorites.* from favorites join commodity on "+
    "commodity.uid = favorites.uid where favorites.uid = ${uid}")
    public List<Map<String,Object>> getFavoritesByUid(@Param("uid") int uid);


}
