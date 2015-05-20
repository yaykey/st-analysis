package com.st.framework.business.impl.room;

import com.st.framework.business.BaseBLOBsManager;
import com.st.framework.module.stock.ChatroomChat;
import com.st.framework.module.stock.example.ChatroomChatExample;
import com.st.framework.persistence.mapper.BaseBLOBsMapper;
import com.st.framework.persistence.mapper.stock.ChatroomChatMapper;
import com.st.framework.utils.db.route.DbContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("chatroomChatManager")
public class ChatroomChatManager extends BaseBLOBsManager<Integer, ChatroomChat, ChatroomChatExample> {
    @Autowired
    private ChatroomChatMapper chatroomChatMapper;

    @Override
    public BaseBLOBsMapper<Integer, ChatroomChat, ChatroomChatExample> getMapper() {
    	DbContextHolder.setDefaultDbType();
    	return chatroomChatMapper;
    }
}