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
-- Name: tb_livros; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_livros (
    isbn bigint NOT NULL,
    id_editora bigint NOT NULL,
    autor_livro text NOT NULL,
    edicao_livro integer NOT NULL
)
INHERITS (public.tb_exemplares);


ALTER TABLE public.tb_livros OWNER TO postgres;

--
-- Name: tb_livros_isbn_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.tb_livros ALTER COLUMN isbn ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.tb_livros_isbn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: tb_livros; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_livros (id_exemplar, titulo_exemplar, ano_exemplar, n_da_estante_exemplar, n_total_exemplares, n_dispo_exemplares, loc_estante, isbn, id_editora, autor_livro, edicao_livro) FROM stdin;
\.


--
-- Name: tb_livros_isbn_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tb_livros_isbn_seq', 1, false);


--
-- Name: tb_livros tb_livros_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_livros
    ADD CONSTRAINT tb_livros_pkey PRIMARY KEY (isbn);


--
-- Name: tb_livros tb_livros_autor_livro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_livros
    ADD CONSTRAINT tb_livros_autor_livro_fkey FOREIGN KEY (autor_livro) REFERENCES public.tb_autores(codigo);


--
-- Name: tb_livros tb_livros_id_editora_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_livros
    ADD CONSTRAINT tb_livros_id_editora_fkey FOREIGN KEY (id_editora) REFERENCES public.tb_editoras(id);


--
-- PostgreSQL database dump complete
--

