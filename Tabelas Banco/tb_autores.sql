--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: tb_autores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_autores (
    id bigint NOT NULL,
    codigo text NOT NULL,
    nome text NOT NULL,
    sobrenome text NOT NULL
);


ALTER TABLE public.tb_autores OWNER TO postgres;

--
-- Name: autores_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.tb_autores ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.autores_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: tb_autores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_autores (id, codigo, nome, sobrenome) FROM stdin;
\.


--
-- Name: autores_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.autores_id_seq', 1, false);


--
-- Name: tb_autores autores_codigo_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_autores
    ADD CONSTRAINT autores_codigo_key UNIQUE (codigo);


--
-- Name: tb_autores autores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_autores
    ADD CONSTRAINT autores_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

