package kr.co.orangenode.entity.page;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBlock is a Querydsl query type for Block
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlock extends EntityPathBase<Block> {

    private static final long serialVersionUID = -904948810L;

    public static final QBlock block = new QBlock("block");

    public final NumberPath<Integer> blockOrder = createNumber("blockOrder", Integer.class);

    public final NumberPath<Integer> bno = createNumber("bno", Integer.class);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> pageNo = createNumber("pageNo", Integer.class);

    public final StringPath type = createString("type");

    public QBlock(String variable) {
        super(Block.class, forVariable(variable));
    }

    public QBlock(Path<? extends Block> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBlock(PathMetadata metadata) {
        super(Block.class, metadata);
    }

}

