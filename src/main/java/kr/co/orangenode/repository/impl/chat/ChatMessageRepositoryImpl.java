package kr.co.orangenode.repository.impl.chat;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import kr.co.orangenode.entity.chat.ChatMessage;

import kr.co.orangenode.entity.chat.QChatMessage;
import kr.co.orangenode.entity.user.QUser;
import kr.co.orangenode.repository.custom.chat.ChatMessageRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class ChatMessageRepositoryImpl implements ChatMessageRepositoryCustom {

    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;
    private final QUser qUser = QUser.user;
    private final QChatMessage qChatMessage = QChatMessage.chatMessage;

    @Override
    public List<Tuple> saveMessageWithRoom(int chatNo) {

        List<Tuple> chatAndName = jpaQueryFactory.select(qUser.name, qChatMessage)
                .from(qChatMessage)
                .join(qUser).on(qUser.uid.eq(qChatMessage.uid))
                .where(qChatMessage.chatNo.eq(chatNo))
                .fetch();

        log.info("impl" + chatAndName);


        // entityManager.persist(chatMessage);
        return chatAndName;
    }

    @Override
    public ChatMessage saveMessageWithRoom2(ChatMessage chatMessage) {
        chatMessage.setUid(chatMessage.getUid());
        chatMessage.setCDate(LocalDateTime.now());

        entityManager.persist(chatMessage);
        return chatMessage;
    }

}
