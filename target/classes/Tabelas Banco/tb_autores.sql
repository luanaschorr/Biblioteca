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
-- Name: tb_editoras; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_editoras (
    id bigint NOT NULL,
    nome text NOT NULL,
    nacional boolean NOT NULL
);


ALTER TABLE public.tb_editoras OWNER TO postgres;

--
-- Name: editoras_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.tb_editoras ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.editoras_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: tb_editoras; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_editoras (id, nome, nacional) FROM stdin;
\.


--
-- Name: editoras_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.editoras_id_seq', 1, false);


--
-- Name: tb_editoras editoras_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_editoras
    ADD CONSTRAINT editoras_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

