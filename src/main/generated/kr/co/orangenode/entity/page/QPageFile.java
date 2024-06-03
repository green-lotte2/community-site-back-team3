package kr.co.orangenode.entity.page;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPageFile is a Querydsl query type for PageFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPageFile extends EntityPathBase<PageFile> {

    private static final long serialVersionUID = 1703436514L;

    public static final QPageFile pageFile = new QPageFile("pageFile");

    public final NumberPath<Integer> pageNo = createNumber("pageNo", Integer.class);

    public final NumberPath<Integer> pfno = createNumber("pfno", Integer.class);

    public final StringPath sName = createString("sName");

    public QPageFile(String variable) {
        super(PageFile.class, forVariable(variable));
    }

    public QPageFile(Path<? extends PageFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPageFile(PathMetadata metadata) {
        super(PageFile.class, metadata);
    }

}

