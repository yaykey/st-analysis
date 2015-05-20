package com.st.framework.persistence.mapper.stock;

import com.st.framework.module.stock.ChatroomChat;
import com.st.framework.module.stock.example.ChatroomChatExample;
import com.st.framework.persistence.mapper.BaseBLOBsMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatroomChatMapper extends BaseBLOBsMapper<Integer, ChatroomChat, ChatroomChatExample> {
}