package kr.co.orangenode.repository.impl.chat;


import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.orangenode.entity.chat.ChatRoom;
import kr.co.orangenode.entity.chat.QChatRoom;
import kr.co.orangenode.entity.chat.QChatUser;
import kr.co.orangenode.repository.custom.chat.ChatRoomRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChatRoom> findChatRoomsByUid(String uid) {
        QChatRoom chatRoom = QChatRoom.chatRoom;
        QChatUser chatUser = QChatUser.chatUser;

        return queryFactory.selectFrom(chatRoom)
                .join(chatUser).on(chatRoom.chatNo.eq(chatUser.chatNo))
                .where(chatUser.uid.eq(uid))
                .fetch();
    }
}
