create table cursos(

    id bigint not null unique auto_increment,
    nome varchar(100) not null,
    categoria varchar(100)not null,
    ativo tinyint,

    primary key(id)

);