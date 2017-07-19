--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: customers; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE customers (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE customers OWNER TO "Guest";

--
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE customers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customers_id_seq OWNER TO "Guest";

--
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE customers_id_seq OWNED BY customers.id;


--
-- Name: products; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE products (
    id integer NOT NULL,
    name character varying,
    price integer
);


ALTER TABLE products OWNER TO "Guest";

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE products_id_seq OWNER TO "Guest";

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE products_id_seq OWNED BY products.id;


--
-- Name: sales; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE sales (
    id integer NOT NULL,
    customer_id integer,
    sale_date timestamp without time zone
);


ALTER TABLE sales OWNER TO "Guest";

--
-- Name: sales_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE sales_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sales_id_seq OWNER TO "Guest";

--
-- Name: sales_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE sales_id_seq OWNED BY sales.id;


--
-- Name: sold_products; Type: TABLE; Schema: public; Owner: Guest
--

CREATE TABLE sold_products (
    id integer NOT NULL,
    product_id integer,
    sale_id integer,
    price integer
);


ALTER TABLE sold_products OWNER TO "Guest";

--
-- Name: sold_products_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE sold_products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sold_products_id_seq OWNER TO "Guest";

--
-- Name: sold_products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE sold_products_id_seq OWNED BY sold_products.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY customers ALTER COLUMN id SET DEFAULT nextval('customers_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY products ALTER COLUMN id SET DEFAULT nextval('products_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY sales ALTER COLUMN id SET DEFAULT nextval('sales_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY sold_products ALTER COLUMN id SET DEFAULT nextval('sold_products_id_seq'::regclass);


--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY customers (id, name) FROM stdin;
\.


--
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('customers_id_seq', 1, false);


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY products (id, name, price) FROM stdin;
\.


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('products_id_seq', 1, false);


--
-- Data for Name: sales; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY sales (id, customer_id, sale_date) FROM stdin;
\.


--
-- Name: sales_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('sales_id_seq', 1, false);


--
-- Data for Name: sold_products; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY sold_products (id, product_id, sale_id, price) FROM stdin;
\.


--
-- Name: sold_products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('sold_products_id_seq', 1, false);


--
-- Name: customers_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- Name: products_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: sales_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY sales
    ADD CONSTRAINT sales_pkey PRIMARY KEY (id);


--
-- Name: sold_products_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY sold_products
    ADD CONSTRAINT sold_products_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

