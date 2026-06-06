CREATE TABLE usuarios(
    id uuid NOT NULL,
    email varchar(50) NOT NULL,
    name varchar(150) NOT NULL,
    perfil varchar(255) NOT NULL,
    senha varchar(100) NOT NULL,
    CONSTRAINT usuarios_pkey PRIMARY KEY (id),
    CONSTRAINT usuarios_email_key UNIQUE (email)
);

INSERT INTO usuarios (id, email, name, perfil, senha)
VALUES ('d290f1ee-6c54-4b01-90e6-d701748f0851', 'enzoadmin@gmai.com',
        'Enzo Admin', 'ADMINISTRADOR', '5390ed5af27200957dc82d8c1ab6cf9ec23b54c5e23481cc56a20ff9b2a4361f');