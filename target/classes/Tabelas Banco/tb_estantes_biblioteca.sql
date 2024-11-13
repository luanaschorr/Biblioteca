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
-- Name: tb_estantes_biblioteca; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_estantes_biblioteca (
    id_estante bigint NOT NULL
);


ALTER TABLE public.tb_estantes_biblioteca OWNER TO postgres;

--
-- Name: estantes_biblioteca_id_estante_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.tb_estantes_biblioteca ALTER COLUMN id_estante ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.estantes_biblioteca_id_estante_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: tb_estantes_biblioteca; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_estantes_biblioteca (id_estante) FROM stdin;
\.


--
-- Name: estantes_biblioteca_id_estante_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estantes_biblioteca_id_estante_seq', 1, false);


--
-- Name: tb_estantes_biblioteca estantes_biblioteca_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_estantes_biblioteca
    ADD CONSTRAINT estantes_biblioteca_pkey PRIMARY KEY (id_estante);


--
-- PostgreSQL database dump complete
--

