package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.FactLogDownload;
import com.st.framework.module.stock.example.FactLogDownloadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FactLogDownloadMapper {
    int countByExample(FactLogDownloadExample example);

    int deleteByExample(FactLogDownloadExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FactLogDownload record);

    int insertSelective(FactLogDownload record);

    List<FactLogDownload> selectByExample(FactLogDownloadExample example);

    FactLogDownload selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FactLogDownload record, @Param("example") FactLogDownloadExample example);

    int updateByExample(@Param("record") FactLogDownload record, @Param("example") FactLogDownloadExample example);

    int updateByPrimaryKeySelective(FactLogDownload record);

    int updateByPrimaryKey(FactLogDownload record);
}