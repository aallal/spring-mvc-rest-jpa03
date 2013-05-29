package com.serisoft.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPersonne is a Querydsl query type for Personne
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPersonne extends EntityPathBase<Personne> {

    private static final long serialVersionUID = -765542593;

    public static final QPersonne personne = new QPersonne("personne");

    public final NumberPath<Short> age = createNumber("age", Short.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Short> marie = createNumber("marie", Short.class);

    public final NumberPath<Short> nbenfants = createNumber("nbenfants", Short.class);

    public final StringPath nom = createString("nom");

    public final StringPath prenom = createString("prenom");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QPersonne(String variable) {
        super(Personne.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QPersonne(Path<? extends Personne> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QPersonne(PathMetadata<?> metadata) {
        super(Personne.class, metadata);
    }

}

