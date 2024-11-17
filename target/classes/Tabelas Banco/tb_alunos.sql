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
-- Name: tb_alunos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_alunos (
    id_aluno bigint NOT NULL,
    nome text NOT NULL,
    sobrenome text NOT NULL,
    data_nascimento date NOT NULL,
    numero_rg text NOT NULL,
    numero_matricula text NOT NULL,
    cpf text NOT NULL,
    telefone text NOT NULL
);


ALTER TABLE public.tb_alunos OWNER TO postgres;

--
-- Name: tb_alunos_id_aluno_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.tb_alunos ALTER COLUMN id_aluno ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.tb_alunos_id_aluno_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: tb_alunos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_alunos (id_aluno, nome, sobrenome, data_nascimento, numero_rg, numero_matricula, cpf, telefone) FROM stdin;
\.


--
-- Name: tb_alunos_id_aluno_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_alunos_id_aluno_seq', 1, false);


--
-- Name: tb_alunos tb_alunos_cpf_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_alunos
    ADD CONSTRAINT tb_alunos_cpf_key UNIQUE (cpf);


--
-- Name: tb_alunos tb_alunos_nome_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_alunos
    ADD CONSTRAINT tb_alunos_nome_key UNIQUE (nome);


--
-- Name: tb_alunos tb_alunos_telefone_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_alunos
    ADD CONSTRAINT tb_alunos_telefone_key UNIQUE (telefone);


--
-- PostgreSQL database dump complete
--

