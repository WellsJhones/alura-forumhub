create table postagens(

    id bigint not null unique auto_increment,
    titulo varchar(100) not null,
    mensagem varchar(1000)not null,
    ativo tinyint,
    curso varchar (100) not null,
    autor varchar(100) not null,


    primary key(id)
);