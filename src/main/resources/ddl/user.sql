-- Table: public."user"

-- DROP TABLE public."user";

CREATE TABLE public."user"
(
    user_id character varying(5) COLLATE pg_catalog."default" NOT NULL,
    user_name character varying(20) COLLATE pg_catalog."default",
    email character varying(256) COLLATE pg_catalog."default",
    age integer,
    birthday date,
    CONSTRAINT user_pkey PRIMARY KEY (user_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."user"
    OWNER to postgres;