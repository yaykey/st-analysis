package com.st.framework.business.impl.room;

import com.st.framework.business.BaseBLOBsManager;
import com.st.framework.module.stock.CircleChat;
import com.st.framework.module.stock.example.CircleChatExample;
import com.st.framework.persistence.mapper.BaseBLOBsMapper;
import com.st.framework.persistence.mapper.stock.CircleChatMapper;
import com.st.framework.utils.db.route.DbContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("circleChatManager")
public class CircleChatManager extends BaseBLOBsManager<Integer, CircleChat, CircleChatExample> {
    @Autowired
    private CircleChatMapper circleChatMapper;

    @Override
    public BaseBLOBsMapper<Integer, CircleChat, CircleChatExample> getMapper() {
    	DbContextHolder.setDefaultDbType();
    	return circleChatMapper;
    }
    
    public CircleChat selectByMD5(String md5Code) {
		DbContextHolder.setDefaultDbType();
		return circleChatMapper.selectByMD5(md5Code);
	}

	public void insertOrUpdateSelective(CircleChat record) {
		DbContextHolder.setDefaultDbType();

		if (record == null || record.getMd5Code() == null
				|| "".equals(record.getMd5Code().trim())) {
			return;
		}
		CircleChat oldRecord = null;

		if (record.getId() == null) {
			oldRecord = this.selectByMD5(record.getMd5Code());
		} else {
			oldRecord = this.selectByPrimaryKey(record.getId());
		}

		if (oldRecord == null) {
			try {
				this.insertSelective(record);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			record.setId(oldRecord.getId());
//			this.updateByPrimaryKeyWithBLOBs(record);
		}
	}
}