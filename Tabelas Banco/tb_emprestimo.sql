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
-- Name: tb_emprestimo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_emprestimo (
    emp_nome_aluno character varying(255),
    emp_telefone_aluno character varying(20),
    emp_cpf_aluno character varying(14) NOT NULL,
    emp_titulo_exemplar character varying(255) NOT NULL,
    n_da_estante integer,
    emp_cod_autor integer,
    data_emprestimo timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.tb_emprestimo OWNER TO postgres;

--
-- Data for Name: tb_emprestimo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tb_emprestimo (emp_nome_aluno, emp_telefone_aluno, emp_cpf_aluno, emp_titulo_exemplar, n_da_estante, emp_cod_autor, data_emprestimo) FROM stdin;
\.


--
-- Name: tb_emprestimo tb_emprestimo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_emprestimo
    ADD CONSTRAINT tb_emprestimo_pkey PRIMARY KEY (emp_cpf_aluno, emp_titulo_exemplar, data_emprestimo);


--
-- Name: tb_emprestimo tb_emprestimo_emp_cod_autor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_emprestimo
    ADD CONSTRAINT tb_emprestimo_emp_cod_autor_fkey FOREIGN KEY (emp_cod_autor) REFERENCES public.tb_autores(id);


--
-- Name: tb_emprestimo tb_emprestimo_emp_cpf_aluno_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_emprestimo
    ADD CONSTRAINT tb_emprestimo_emp_cpf_aluno_fkey FOREIGN KEY (emp_cpf_aluno) REFERENCES public.tb_alunos(cpf);


--
-- Name: tb_emprestimo tb_emprestimo_emp_nome_aluno_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_emprestimo
    ADD CONSTRAINT tb_emprestimo_emp_nome_aluno_fkey FOREIGN KEY (emp_nome_aluno) REFERENCES public.tb_alunos(nome);


--
-- Name: tb_emprestimo tb_emprestimo_emp_telefone_aluno_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_emprestimo
    ADD CONSTRAINT tb_emprestimo_emp_telefone_aluno_fkey FOREIGN KEY (emp_telefone_aluno) REFERENCES public.tb_alunos(telefone);


--
-- Name: tb_emprestimo tb_emprestimo_emp_titulo_exemplar_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_emprestimo
    ADD CONSTRAINT tb_emprestimo_emp_titulo_exemplar_fkey FOREIGN KEY (emp_titulo_exemplar) REFERENCES public.tb_exemplares(titulo_exemplar);


--
-- Name: tb_emprestimo tb_emprestimo_n_da_estante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_emprestimo
    ADD CONSTRAINT tb_emprestimo_n_da_estante_fkey FOREIGN KEY (n_da_estante) REFERENCES public.tb_exemplares(n_da_estante_exemplar);


--
-- PostgreSQL database dump complete
--

