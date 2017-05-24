-- Table: public.test_user

-- DROP TABLE public.test_user;

CREATE TABLE public.test_user
(
    user_id character varying(5) COLLATE pg_catalog."default" NOT NULL,
    user_name character varying(20) COLLATE pg_catalog."default",
    age integer,
    birthday date,
    email character varying(256) COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (user_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.test_user
    OWNER to postgres;