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
-- Name: tb_exemplares; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_exemplares (
    id_exemplar bigint NOT NULL,
    titulo_exemplar text NOT NULL,
    ano_exemplar integer NOT NULL,
    n_da_estante_exemplar integer NOT NULL,
    n_total_exemplares integer NOT NULL,
    n_dispo_exemplares integer NOT NULL,
    loc_estante bigint
);


ALTER TABLE public.tb_exemplares OWNER TO postgres;

--
-- Name: exemplares_id_exemplar_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.tb_exemplares ALTER COLUMN id_exemplar ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.exemplares_id_exemplar_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: tb_exemplares; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_exemplares (id_exemplar, titulo_exemplar, ano_exemplar, n_da_estante_exemplar, n_total_exemplares, n_dispo_exemplares, loc_estante) FROM stdin;
\.


--
-- Name: exemplares_id_exemplar_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.exemplares_id_exemplar_seq', 1, false);


--
-- Name: tb_exemplares exemplares_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_exemplares
    ADD CONSTRAINT exemplares_pkey PRIMARY KEY (id_exemplar);


--
-- Name: tb_exemplares n_da_estante_exemplar; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_exemplares
    ADD CONSTRAINT n_da_estante_exemplar UNIQUE (n_da_estante_exemplar);


--
-- Name: tb_exemplares titulo_exemplar; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_exemplares
    ADD CONSTRAINT titulo_exemplar UNIQUE (titulo_exemplar);


--
-- Name: tb_exemplares exemplares_loc_estante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_exemplares
    ADD CONSTRAINT exemplares_loc_estante_fkey FOREIGN KEY (loc_estante) REFERENCES public.tb_estantes_biblioteca(id_estante);


--
-- PostgreSQL database dump complete
--

