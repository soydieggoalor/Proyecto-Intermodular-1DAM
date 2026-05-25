--
-- PostgreSQL database dump
--

\restrict uv3Lp8jalsjvDhmFoduAfCCBhnR98qOobC6hnb5bLuvQ0mb6iKTlgro9yY2ZHNa

-- Dumped from database version 18.3
-- Dumped by pg_dump version 18.3

-- Started on 2026-05-02 22:09:20 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3880 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 25126)
-- Name: departamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.departamento (
    cod_dep character varying(10) NOT NULL,
    nom_dep character varying(50) NOT NULL,
    presupuesto numeric(10,2) NOT NULL,
    cod_director character varying(10),
    CONSTRAINT departamento_presupuesto_check CHECK ((presupuesto >= (0)::numeric))
);


ALTER TABLE public.departamento OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 25116)
-- Name: direccion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.direccion (
    cod_dir character varying(10) NOT NULL,
    calle character varying(100) NOT NULL,
    num character varying(10) NOT NULL,
    cp character(5) NOT NULL,
    ciudad character varying(50) NOT NULL
);


ALTER TABLE public.direccion OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 25180)
-- Name: dispositivo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dispositivo (
    cod_dispositivo character varying(20) NOT NULL,
    mac character(17) NOT NULL,
    num_serie character varying(50) NOT NULL,
    sistema_operativo character varying(50) NOT NULL,
    ip character varying(15) NOT NULL,
    tipo character varying(30) NOT NULL,
    cod_empleado character varying(10)
);


ALTER TABLE public.dispositivo OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 25137)
-- Name: empleado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empleado (
    cod_empleado character varying(10) NOT NULL,
    dni character varying(12) NOT NULL,
    nombre character varying(50) NOT NULL,
    apellido_1 character varying(50) NOT NULL,
    apellido_2 character varying(50),
    rol character varying(30) NOT NULL,
    fecha_contratacion date NOT NULL,
    salario numeric(10,2) NOT NULL,
    cod_dep character varying(10),
    mail character varying(100),
    CONSTRAINT empleado_salario_check CHECK ((salario >= (0)::numeric))
);


ALTER TABLE public.empleado OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 25200)
-- Name: incidencias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.incidencias (
    cod_incidencia character varying(10) NOT NULL,
    observaciones text,
    estado character varying(20) DEFAULT 'Pendiente'::character varying NOT NULL,
    fecha_alta date DEFAULT CURRENT_DATE NOT NULL,
    fecha_cierre date,
    cod_dispositivo character varying(20) NOT NULL,
    cod_responsable character varying(10),
    cod_creador character varying(10),
    CONSTRAINT incidencias_estado_check CHECK (((estado)::text = ANY ((ARRAY['Pendiente'::character varying, 'En Progreso'::character varying, 'Cerrada'::character varying])::text[])))
);


ALTER TABLE public.incidencias OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 25162)
-- Name: ubicacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ubicacion (
    cod_ubicacion character varying(10) NOT NULL,
    planta integer NOT NULL,
    edificio character varying(50) NOT NULL,
    despacho character varying(20),
    cod_dep character varying(10),
    cod_dir character varying(10)
);


ALTER TABLE public.ubicacion OWNER TO postgres;

--
-- TOC entry 3870 (class 0 OID 25126)
-- Dependencies: 220
-- Data for Name: departamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.departamento (cod_dep, nom_dep, presupuesto, cod_director) VALUES ('DEP-SIS', 'Sistemas', 50000.00, 'EMP-001');
INSERT INTO public.departamento (cod_dep, nom_dep, presupuesto, cod_director) VALUES ('DEP-RRHH', 'RRHH', 30000.00, 'EMP-004');
INSERT INTO public.departamento (cod_dep, nom_dep, presupuesto, cod_director) VALUES ('DEP-MKT', 'Marketing', 45000.00, 'EMP-005');


--
-- TOC entry 3869 (class 0 OID 25116)
-- Dependencies: 219
-- Data for Name: direccion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.direccion (cod_dir, calle, num, cp, ciudad) VALUES ('DIR-01', 'Calle Mayor', '10', '46001', 'Valencia');
INSERT INTO public.direccion (cod_dir, calle, num, cp, ciudad) VALUES ('DIR-02', 'Av. Puerto', '25', '46011', 'Valencia');
INSERT INTO public.direccion (cod_dir, calle, num, cp, ciudad) VALUES ('DIR-03', 'Gran Vía', '5', '28001', 'Madrid');


--
-- TOC entry 3873 (class 0 OID 25180)
-- Dependencies: 223
-- Data for Name: dispositivo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.dispositivo (cod_dispositivo, mac, num_serie, sistema_operativo, ip, tipo, cod_empleado) VALUES ('DISP-001', 'AA:BB:CC:DD:EE:01', 'SN001', 'Windows 10', '192.168.1.10', 'Portátil', 'EMP-002');
INSERT INTO public.dispositivo (cod_dispositivo, mac, num_serie, sistema_operativo, ip, tipo, cod_empleado) VALUES ('DISP-002', 'AA:BB:CC:DD:EE:02', 'SN002', 'Linux', '192.168.1.11', 'Servidor', 'EMP-001');
INSERT INTO public.dispositivo (cod_dispositivo, mac, num_serie, sistema_operativo, ip, tipo, cod_empleado) VALUES ('DISP-003', 'AA:BB:CC:DD:EE:03', 'SN003', 'Windows 11', '192.168.1.12', 'PC', 'EMP-003');
INSERT INTO public.dispositivo (cod_dispositivo, mac, num_serie, sistema_operativo, ip, tipo, cod_empleado) VALUES ('DISP-004', 'AA:BB:CC:DD:EE:04', 'SN004', 'Android', '192.168.1.13', 'Tablet', NULL);


--
-- TOC entry 3871 (class 0 OID 25137)
-- Dependencies: 221
-- Data for Name: empleado; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.empleado (cod_empleado, dni, nombre, apellido_1, apellido_2, rol, fecha_contratacion, salario, cod_dep, mail) VALUES ('EMP-001', '11111111A', 'Ana', 'López', 'García', 'Directora', '2018-03-10', 32000.00, 'DEP-SIS', 'ana@empresa.com');
INSERT INTO public.empleado (cod_empleado, dni, nombre, apellido_1, apellido_2, rol, fecha_contratacion, salario, cod_dep, mail) VALUES ('EMP-002', '22222222B', 'Luis', 'Martínez', 'Santos', 'Técnico', '2020-06-15', 24000.00, 'DEP-SIS', 'luis@empresa.com');
INSERT INTO public.empleado (cod_empleado, dni, nombre, apellido_1, apellido_2, rol, fecha_contratacion, salario, cod_dep, mail) VALUES ('EMP-003', 'X1234567J', 'John', 'Doe', NULL, 'Técnico', '2021-01-20', 23000.00, 'DEP-SIS', 'john@empresa.com');
INSERT INTO public.empleado (cod_empleado, dni, nombre, apellido_1, apellido_2, rol, fecha_contratacion, salario, cod_dep, mail) VALUES ('EMP-004', '44444444D', 'Carlos', 'Serrano', 'Gil', 'RRHH Manager', '2017-02-01', 35000.00, 'DEP-RRHH', 'carlos@empresa.com');
INSERT INTO public.empleado (cod_empleado, dni, nombre, apellido_1, apellido_2, rol, fecha_contratacion, salario, cod_dep, mail) VALUES ('EMP-005', '55555555E', 'Elena', 'Torres', 'Luna', 'Marketing', '2019-09-12', 28000.00, 'DEP-MKT', 'elena@empresa.com');


--
-- TOC entry 3874 (class 0 OID 25200)
-- Dependencies: 224
-- Data for Name: incidencias; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.incidencias (cod_incidencia, observaciones, estado, fecha_alta, fecha_cierre, cod_dispositivo, cod_responsable, cod_creador) VALUES ('INC-001', 'Fallo de red', 'Pendiente', '2026-05-01', NULL, 'DISP-001', 'EMP-002', 'EMP-001');
INSERT INTO public.incidencias (cod_incidencia, observaciones, estado, fecha_alta, fecha_cierre, cod_dispositivo, cod_responsable, cod_creador) VALUES ('INC-002', 'Reinicios constantes', 'En Progreso', '2026-05-02', NULL, 'DISP-001', 'EMP-002', 'EMP-003');
INSERT INTO public.incidencias (cod_incidencia, observaciones, estado, fecha_alta, fecha_cierre, cod_dispositivo, cod_responsable, cod_creador) VALUES ('INC-003', 'Virus detectado', 'En Progreso', '2026-04-28', NULL, 'DISP-003', 'EMP-003', 'EMP-002');
INSERT INTO public.incidencias (cod_incidencia, observaciones, estado, fecha_alta, fecha_cierre, cod_dispositivo, cod_responsable, cod_creador) VALUES ('INC-004', 'Actualización fallida', 'Cerrada', '2026-04-20', '2026-04-21', 'DISP-002', 'EMP-001', 'EMP-003');


--
-- TOC entry 3872 (class 0 OID 25162)
-- Dependencies: 222
-- Data for Name: ubicacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ubicacion (cod_ubicacion, planta, edificio, despacho, cod_dep, cod_dir) VALUES ('UBI-A1', 1, 'Edificio A', 'A-101', 'DEP-SIS', 'DIR-01');
INSERT INTO public.ubicacion (cod_ubicacion, planta, edificio, despacho, cod_dep, cod_dir) VALUES ('UBI-A2', 2, 'Edificio A', 'A-201', 'DEP-RRHH', 'DIR-02');
INSERT INTO public.ubicacion (cod_ubicacion, planta, edificio, despacho, cod_dep, cod_dir) VALUES ('UBI-B1', 3, 'Edificio B', 'B-301', 'DEP-MKT', 'DIR-03');


--
-- TOC entry 3697 (class 2606 OID 25136)
-- Name: departamento departamento_cod_director_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departamento
    ADD CONSTRAINT departamento_cod_director_key UNIQUE (cod_director);


--
-- TOC entry 3699 (class 2606 OID 25134)
-- Name: departamento departamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departamento
    ADD CONSTRAINT departamento_pkey PRIMARY KEY (cod_dep);


--
-- TOC entry 3695 (class 2606 OID 25125)
-- Name: direccion direccion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.direccion
    ADD CONSTRAINT direccion_pkey PRIMARY KEY (cod_dir);


--
-- TOC entry 3707 (class 2606 OID 25194)
-- Name: dispositivo dispositivo_ip_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dispositivo
    ADD CONSTRAINT dispositivo_ip_key UNIQUE (ip);


--
-- TOC entry 3709 (class 2606 OID 25192)
-- Name: dispositivo dispositivo_mac_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dispositivo
    ADD CONSTRAINT dispositivo_mac_key UNIQUE (mac);


--
-- TOC entry 3711 (class 2606 OID 25190)
-- Name: dispositivo dispositivo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dispositivo
    ADD CONSTRAINT dispositivo_pkey PRIMARY KEY (cod_dispositivo);


--
-- TOC entry 3701 (class 2606 OID 25151)
-- Name: empleado empleado_dni_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_dni_key UNIQUE (dni);


--
-- TOC entry 3703 (class 2606 OID 25149)
-- Name: empleado empleado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (cod_empleado);


--
-- TOC entry 3713 (class 2606 OID 25213)
-- Name: incidencias incidencias_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.incidencias
    ADD CONSTRAINT incidencias_pkey PRIMARY KEY (cod_incidencia);


--
-- TOC entry 3705 (class 2606 OID 25169)
-- Name: ubicacion ubicacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ubicacion
    ADD CONSTRAINT ubicacion_pkey PRIMARY KEY (cod_ubicacion);


--
-- TOC entry 3719 (class 2606 OID 25224)
-- Name: incidencias fk_creador_incidencia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.incidencias
    ADD CONSTRAINT fk_creador_incidencia FOREIGN KEY (cod_creador) REFERENCES public.empleado(cod_empleado) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3715 (class 2606 OID 25152)
-- Name: empleado fk_dep_empleado; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT fk_dep_empleado FOREIGN KEY (cod_dep) REFERENCES public.departamento(cod_dep) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3716 (class 2606 OID 25170)
-- Name: ubicacion fk_dep_ubicacion; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ubicacion
    ADD CONSTRAINT fk_dep_ubicacion FOREIGN KEY (cod_dep) REFERENCES public.departamento(cod_dep) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3717 (class 2606 OID 25175)
-- Name: ubicacion fk_dir_ubicacion; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ubicacion
    ADD CONSTRAINT fk_dir_ubicacion FOREIGN KEY (cod_dir) REFERENCES public.direccion(cod_dir) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3714 (class 2606 OID 25157)
-- Name: departamento fk_director; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departamento
    ADD CONSTRAINT fk_director FOREIGN KEY (cod_director) REFERENCES public.empleado(cod_empleado) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3720 (class 2606 OID 25214)
-- Name: incidencias fk_dispositivo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.incidencias
    ADD CONSTRAINT fk_dispositivo FOREIGN KEY (cod_dispositivo) REFERENCES public.dispositivo(cod_dispositivo) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3718 (class 2606 OID 25195)
-- Name: dispositivo fk_responsable; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dispositivo
    ADD CONSTRAINT fk_responsable FOREIGN KEY (cod_empleado) REFERENCES public.empleado(cod_empleado) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3721 (class 2606 OID 25219)
-- Name: incidencias fk_responsable_incidencia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.incidencias
    ADD CONSTRAINT fk_responsable_incidencia FOREIGN KEY (cod_responsable) REFERENCES public.empleado(cod_empleado) ON UPDATE CASCADE ON DELETE SET NULL;


-- Completed on 2026-05-02 22:09:20 CEST

--
-- PostgreSQL database dump complete
--

\unrestrict uv3Lp8jalsjvDhmFoduAfCCBhnR98qOobC6hnb5bLuvQ0mb6iKTlgro9yY2ZHNa

