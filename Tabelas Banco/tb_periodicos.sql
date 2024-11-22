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
-- Name: tb_periodicos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_periodicos (
    issn bigint NOT NULL,
    per_volume integer NOT NULL
) INHERITS (public.tb_exemplares);


ALTER TABLE public.tb_periodicos OWNER TO postgres;

--
-- Name: tb_periodicos_issn_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.tb_periodicos ALTER COLUMN issn ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.tb_periodicos_issn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: tb_periodicos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_periodicos (id_exemplar, titulo_exemplar, ano_exemplar, n_da_estante_exemplar, n_total_exemplares, n_dispo_exemplares, loc_estante, issn, per_volume) FROM stdin;
\.


--
-- Name: tb_periodicos_issn_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_periodicos_issn_seq', 1, false);


--
-- Name: tb_periodicos tb_periodicos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_periodicos
    ADD CONSTRAINT tb_periodicos_pkey PRIMARY KEY (issn);


--
-- PostgreSQL database dump complete
--

