CREATE TABLE public.usuarios
(
    id     uuid         NOT NULL,
    email  varchar(50)  NOT NULL,
    name   varchar(150) NOT NULL,
    perfil varchar(255) NOT NULL,
    senha  varchar(100) NOT NULL,
    CONSTRAINT ukkfsp0s1tflm1cwlj8idhqsad0 UNIQUE (email),
    CONSTRAINT usuarios_perfil_check CHECK (((perfil)::text = ANY ((ARRAY['OPERADOR':: character varying, 'ADMINISTRADOR':: character varying])::text[])
) ),
	CONSTRAINT usuarios_pkey PRIMARY KEY (id)
);

INSERT INTO public.usuarios (id, email, name, perfil, senha)
VALUES ('d290f1ee-6c54-4b01-90e6-d701748f0851', 'enzoadmin@gmai.com',
        'Enzo Admin', 'ADMINISTRADOR', '5390ed5af27200957dc82d8c1ab6cf9ec23b54c5e23481cc56a20ff9b2a4361f');
