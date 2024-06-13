package kr.co.orangenode.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.orangenode.entity.project.QCollaborator;
import kr.co.orangenode.entity.project.QProject;
import kr.co.orangenode.entity.user.QUser;
import kr.co.orangenode.repository.custom.ProjectRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private final QProject qProject = QProject.project;
    private final QCollaborator qCollaborator = QCollaborator.collaborator;
    private final QUser qUser = QUser.user;

//    @Override
//    public List<Tuple> selectKanban(int proNo){
//        log.info("321321 = " + proNo);
//        // QueryDSL 쿼리 작성
//        List<Tuple> result = jpaQueryFactory
//                .select(qProject.proNo, qIssue.iNo, qIssue.title, qUser.uid, qUser.name, qUser.profile)
//                .from(qProject)
//                .leftJoin(qIssue).on(qProject.proNo.eq(qIssue.proNo))
//                .leftJoin(qWorker).on(qIssue.iNo.eq(qWorker.ino))
//                .leftJoin(qCollaborator).on(qWorker.colNo.eq(qCollaborator.colNo))
//                .leftJoin(qUser).on(qCollaborator.uid.eq(qUser.uid))
//                .where(qProject.proNo.eq(proNo))
//                //.orderBy(qIssue.iNo.asc(), qUser.name.asc())
//                .fetch();
//        log.info("222222 = " + result);
//        return result;
//
//    }
}
