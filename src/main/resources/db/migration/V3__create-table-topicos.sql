create table postagens(

    id bigint not null unique auto_increment,
    titulo varchar(100) not null unique,
    mensagem varchar(255) not null unique,
    ativo tinyint,
    curso varchar (100) not null,
    autor varchar(100) not null,
    data_postagem varchar(100) not null,
    status varchar(100) not null,


    primary key(id)
);