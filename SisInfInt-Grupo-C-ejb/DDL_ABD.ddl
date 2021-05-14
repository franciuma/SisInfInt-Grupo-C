-- Generado por Oracle SQL Developer Data Modeler 20.2.0.167.1538
--   en:        2021-04-08 15:18:58 CEST
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE alumno (
    id                   INTEGER NOT NULL,
    dni                  VARCHAR2(9 CHAR) NOT NULL,
    nombre_completo      VARCHAR2(100 CHAR) NOT NULL,
    email_institucional  VARCHAR2(100 CHAR) NOT NULL,
    email_personal       VARCHAR2(100 CHAR),
    telefono_fijo        VARCHAR2(20 CHAR),
    telefono_movil       VARCHAR2(20 CHAR)
);

ALTER TABLE alumno ADD CONSTRAINT alumno_pk PRIMARY KEY ( id );

ALTER TABLE alumno ADD CONSTRAINT alumno_dni_un UNIQUE ( dni );

CREATE TABLE asignatura (
    referencia                     INTEGER NOT NULL,
    codigo_asignatura              INTEGER NOT NULL,
    creditos                       INTEGER NOT NULL,
    ofertada                       CHAR(1) NOT NULL,
    nombre                         VARCHAR2(20 CHAR) NOT NULL,
    curso                          VARCHAR2(10 CHAR),
    caracter                       VARCHAR2(20 CHAR),
    duracion                       INTEGER,
    unidad_temporal_ccuatrimestre  VARCHAR2(20 CHAR),
    idiomas_imparticion            VARCHAR2(100 CHAR),
    titulacion_codigo              INTEGER NOT NULL
);

ALTER TABLE asignatura ADD CONSTRAINT asignatura_pk PRIMARY KEY ( referencia );

CREATE TABLE asignatura_matricula (
    matricula_curso_académico              VARCHAR2(10 CHAR) NOT NULL, 
--  ERROR: Column name length exceeds maximum allowed length(30) 
         matricula_expedientes_num_expedientes  INTEGER NOT NULL,
    grupo_id                               INTEGER,
    asignatura_referencia                  INTEGER NOT NULL
);

ALTER TABLE asignatura_matricula
    ADD CONSTRAINT asignaturas_matricula_pk PRIMARY KEY ( matricula_curso_académico,
                                                          matricula_expedientes_num_expedientes,
                                                          asignatura_referencia );

CREATE TABLE centro (
    id                    INTEGER NOT NULL,
    nombre                VARCHAR2(20 CHAR) NOT NULL,
    direccion             VARCHAR2(200 CHAR) NOT NULL,
    telefono_conserjeria  VARCHAR2(20 CHAR)
);

ALTER TABLE centro ADD CONSTRAINT centro_pk PRIMARY KEY ( id );

ALTER TABLE centro ADD CONSTRAINT centro_nombre_un UNIQUE ( nombre );

CREATE TABLE clase (
    dia                    DATE NOT NULL,
    hora_inicio            DATE NOT NULL,
    hora_fin               DATE,
    asignatura_referencia  INTEGER NOT NULL,
    grupo_id               INTEGER NOT NULL
);

ALTER TABLE clase
    ADD CONSTRAINT clase_pk PRIMARY KEY ( dia,
                                          hora_inicio,
                                          grupo_id );

CREATE TABLE enc_gr_p_asig (
    encuesta_fecha_de_envio                      DATE NOT NULL, 
--  ERROR: Column name length exceeds maximum allowed length(30) 
         grupos_por_asignatura_curso_académico        VARCHAR2(10 CHAR) NOT NULL,
    grupos_por_asignatura_id                     INTEGER NOT NULL, 
--  ERROR: Column name length exceeds maximum allowed length(30) 
         grupos_por_asignatura_asignatura_referencia  INTEGER NOT NULL
);

ALTER TABLE enc_gr_p_asig
    ADD CONSTRAINT enc_gr_p_asig_pk PRIMARY KEY ( encuesta_fecha_de_envio,
                                                  grupos_por_asignatura_curso_académico,
                                                  grupos_por_asignatura_id,
                                                  grupos_por_asignatura_asignatura_referencia );

CREATE TABLE encuesta (
    fecha_envio                  DATE NOT NULL,
    expedientes_num_expedientes  INTEGER NOT NULL
);

ALTER TABLE encuesta ADD CONSTRAINT encuesta_pk PRIMARY KEY ( fecha_envio );

CREATE TABLE expediente (
    numero_expediente       INTEGER NOT NULL,
    activo                  CHAR(1),
    nota_media_provisional  INTEGER,
    titulacion_codigo       INTEGER NOT NULL,
    alumno_id               INTEGER NOT NULL
);

ALTER TABLE expediente ADD CONSTRAINT expedientes_pk PRIMARY KEY ( numero_expediente );

CREATE TABLE grupo (
    id                  INTEGER NOT NULL,
    curso               INTEGER NOT NULL,
    letra               VARCHAR2(1 CHAR) NOT NULL,
    turno_mañana_tarde  VARCHAR2(10 CHAR) NOT NULL,
    ingles              CHAR(1) NOT NULL,
    visible             CHAR(1),
    asignar             VARCHAR2(20 CHAR),
    plazas              INTEGER,
    grupo_id            INTEGER,
    titulacion_codigo   INTEGER NOT NULL
);

ALTER TABLE grupo ADD CONSTRAINT grupo_pk PRIMARY KEY ( id );

CREATE TABLE gruposporasig (
    curso_academico        VARCHAR2(10 CHAR) NOT NULL,
    oferta                 CHAR(1),
    grupo_id               INTEGER NOT NULL,
    asignatura_referencia  INTEGER NOT NULL
);

ALTER TABLE gruposporasig
    ADD CONSTRAINT grupos_por_asignatura_pk PRIMARY KEY ( curso_academico,
                                                          grupo_id,
                                                          asignatura_referencia );

CREATE TABLE matricula (
    curso_academico              VARCHAR2(10 CHAR) NOT NULL,
    estado                       VARCHAR2(10 CHAR) NOT NULL,
    fecha_matricula              DATE NOT NULL,
    numero_archivo               INTEGER NOT NULL,
    turno_preferente             VARCHAR2(10 CHAR),
    nuevo_ingreso                CHAR(1),
    listado_asignaturas          VARCHAR2(500 CHAR),
    expedientes_num_expedientes  INTEGER NOT NULL
);

ALTER TABLE matricula ADD CONSTRAINT matricula_pk PRIMARY KEY ( curso_academico,
                                                                expedientes_num_expedientes );

CREATE TABLE optativa (
    referencia  INTEGER NOT NULL,
    plazas      INTEGER NOT NULL,
    mencion     VARCHAR2(20 CHAR)
);

ALTER TABLE optativa ADD CONSTRAINT optativa_pk PRIMARY KEY ( referencia );

CREATE TABLE tit_centro (
    titulacion_codigo  INTEGER NOT NULL,
    centro_id          INTEGER NOT NULL
);

ALTER TABLE tit_centro ADD CONSTRAINT tit_centro_pk PRIMARY KEY ( titulacion_codigo,
                                                                  centro_id );

CREATE TABLE titulacion (
    codigo_titulacion  INTEGER NOT NULL,
    nombre             VARCHAR2(20 CHAR) NOT NULL,
    creditos           INTEGER NOT NULL
);

ALTER TABLE titulacion ADD CONSTRAINT titulacion_pk PRIMARY KEY ( codigo_titulacion );

ALTER TABLE asignatura
    ADD CONSTRAINT asignatura_titulacion_fk FOREIGN KEY ( titulacion_codigo )
        REFERENCES titulacion ( codigo_titulacion );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE asignatura_matricula
    ADD CONSTRAINT asignaturas_matricula_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE asignatura_matricula
    ADD CONSTRAINT asignaturas_matricula_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE asignatura_matricula
    ADD CONSTRAINT asignaturas_matricula_matricula_fk FOREIGN KEY ( matricula_curso_académico,
                                                                    matricula_expedientes_num_expedientes )
        REFERENCES matricula ( curso_academico,
                               expedientes_num_expedientes );

ALTER TABLE clase
    ADD CONSTRAINT clase_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE clase
    ADD CONSTRAINT clase_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE enc_gr_p_asig
    ADD CONSTRAINT enc_gr_p_asig_encuesta_fk FOREIGN KEY ( encuesta_fecha_de_envio )
        REFERENCES encuesta ( fecha_envio );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE enc_gr_p_asig
    ADD CONSTRAINT enc_gr_p_asig_grupos_por_asignatura_fk FOREIGN KEY ( grupos_por_asignatura_curso_académico,
                                                                        grupos_por_asignatura_id,
                                                                        grupos_por_asignatura_asignatura_referencia )
        REFERENCES gruposporasig ( curso_academico,
                                   grupo_id,
                                   asignatura_referencia );

ALTER TABLE encuesta
    ADD CONSTRAINT encuesta_expedientes_fk FOREIGN KEY ( expedientes_num_expedientes )
        REFERENCES expediente ( numero_expediente );

ALTER TABLE expediente
    ADD CONSTRAINT expedientes_alumno_fk FOREIGN KEY ( alumno_id )
        REFERENCES alumno ( id );

ALTER TABLE expediente
    ADD CONSTRAINT expedientes_titulacion_fk FOREIGN KEY ( titulacion_codigo )
        REFERENCES titulacion ( codigo_titulacion );

ALTER TABLE grupo
    ADD CONSTRAINT grupo_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE grupo
    ADD CONSTRAINT grupo_titulacion_fk FOREIGN KEY ( titulacion_codigo )
        REFERENCES titulacion ( codigo_titulacion );

--  ERROR: FK name length exceeds maximum allowed length(30) 
ALTER TABLE gruposporasig
    ADD CONSTRAINT grupos_por_asignatura_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE gruposporasig
    ADD CONSTRAINT grupos_por_asignatura_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE matricula
    ADD CONSTRAINT matricula_expedientes_fk FOREIGN KEY ( expedientes_num_expedientes )
        REFERENCES expediente ( numero_expediente );

ALTER TABLE optativa
    ADD CONSTRAINT optativa_asignatura_fk FOREIGN KEY ( referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE tit_centro
    ADD CONSTRAINT tit_centro_centro_fk FOREIGN KEY ( centro_id )
        REFERENCES centro ( id );

ALTER TABLE tit_centro
    ADD CONSTRAINT tit_centro_titulacion_fk FOREIGN KEY ( titulacion_codigo )
        REFERENCES titulacion ( codigo_titulacion );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            14
-- CREATE INDEX                             0
-- ALTER TABLE                             35
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   7
-- WARNINGS                                 0
