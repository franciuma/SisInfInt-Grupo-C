CREATE TABLE ALUMNO (ID INTEGER NOT NULL, DNI VARCHAR(9) NOT NULL UNIQUE, Email_Institucional VARCHAR(100) NOT NULL, Email_Personal VARCHAR(100), Telefono_Movil VARCHAR(20), Nombre_Completo VARCHAR(100) NOT NULL, Telefono_Fijo VARCHAR(20), PRIMARY KEY (ID))
<<<<<<< refs/remotes/origin/elrager
CREATE TABLE ASIGNATURA (Referencia INTEGER NOT NULL, DTYPE VARCHAR(31), Duracion INTEGER, Caracter VARCHAR(20), Codigo_Asignatura INTEGER NOT NULL, Creditos INTEGER NOT NULL, Curso VARCHAR(10), Idiomas_Imparticion VARCHAR(100), Nombre VARCHAR(20) NOT NULL, Ofertada BOOLEAN NOT NULL, Unidad_Temporal_Cuatrimestre VARCHAR(20), TITULACION_Codigo_Titulacion INTEGER, Mencion VARCHAR(20), Plazas INTEGER NOT NULL, PRIMARY KEY (Referencia))
=======
CREATE TABLE ASIGNATURA (Referencia INTEGER NOT NULL, DTYPE VARCHAR(31), Duracion INTEGER, Caracter VARCHAR(20), Codigo_Asignatura INTEGER NOT NULL, Creditos INTEGER NOT NULL, Curso VARCHAR(10), Idiomas_Imparticion VARCHAR(100), Nombre VARCHAR(100) NOT NULL, Ofertada BOOLEAN NOT NULL, Unidad_Temporal_Cuatrimestre VARCHAR(20), TITULACION_Codigo_Titulacion INTEGER, Mencion VARCHAR(20), Plazas INTEGER, PRIMARY KEY (Referencia))
>>>>>>> local
CREATE TABLE CENTRO (ID INTEGER NOT NULL, Direccion VARCHAR(200) NOT NULL, Nombre VARCHAR(50) NOT NULL UNIQUE, Telefono_Conserjeria VARCHAR(20), PRIMARY KEY (ID))
CREATE TABLE CLASE (Hora_Fin DATE, GRUPO INTEGER NOT NULL, Dia DATE NOT NULL, Hora_Inicio DATE NOT NULL, ASIGNATURA_Referencia INTEGER, GRUPO_ID INTEGER NOT NULL, PRIMARY KEY (GRUPO, Dia, Hora_Inicio, GRUPO_ID))
CREATE TABLE ENCUESTA (Fecha_Envio TIMESTAMP NOT NULL, CAMPOS VARCHAR, EXPEDIENTE_Numero_Expediente INTEGER, PRIMARY KEY (Fecha_Envio))
CREATE TABLE EXPEDIENTE (Numero_Expediente INTEGER NOT NULL, Activo BOOLEAN, Nota_Media_Provisional INTEGER, ALUMNO_ID INTEGER, TITULACION_Codigo_Titulacion INTEGER, PRIMARY KEY (Numero_Expediente))
CREATE TABLE GRUPO (ID INTEGER NOT NULL, Asignar VARCHAR(20), Curso INTEGER NOT NULL, Ingles BOOLEAN NOT NULL, Letra VARCHAR(1) NOT NULL, Plazas INTEGER, Turno_Mañana_Tarde VARCHAR(10) NOT NULL, Visible BOOLEAN, GRUPO_ID INTEGER, TITULACION_Codigo_Titulacion INTEGER, PRIMARY KEY (ID))
CREATE TABLE GRUPOSPORASIG (Oferta BOOLEAN, Curso_Academico VARCHAR(10) NOT NULL, ASIGNATURA INTEGER NOT NULL, GRUPO INTEGER NOT NULL, ASIGNATURA_Referencia INTEGER, GRUPO_ID INTEGER, PRIMARY KEY (Curso_Academico, ASIGNATURA, GRUPO))
CREATE TABLE MATRICULA (Curso_Academico VARCHAR(10), Estado VARCHAR(10) NOT NULL, Fecha_Matricula TIMESTAMP NOT NULL, Listado_Asignaturas VARCHAR(500), Nuevo_Ingreso BOOLEAN, Numero_Archivo INTEGER, Turno_Preferente VARCHAR(10), CURSOACADEMICO VARCHAR NOT NULL, EXPEDIENTE BIGINT NOT NULL, EXPEDIENTE_Numero_Expediente INTEGER, PRIMARY KEY (CURSOACADEMICO, EXPEDIENTE))
CREATE TABLE TITULACION (Codigo_Titulacion INTEGER NOT NULL, Creditos INTEGER NOT NULL, Nombre VARCHAR(40) NOT NULL, PRIMARY KEY (Codigo_Titulacion))
CREATE TABLE ASIGNATURA_MATRICULA (ASIGNATURA_Referencia INTEGER NOT NULL, EXPEDIENTE BIGINT NOT NULL, CURSOACADEMICO VARCHAR NOT NULL, GRUPO_ID INTEGER NOT NULL, PRIMARY KEY (ASIGNATURA_Referencia, EXPEDIENTE, CURSOACADEMICO))
CREATE TABLE JND_Centro_Titulacion (Centro_FK INTEGER NOT NULL, Titulacion_FK INTEGER NOT NULL, PRIMARY KEY (Centro_FK, Titulacion_FK))
CREATE TABLE ENCUESTA_GRUPOSPORASIG (contieneEncuestas_Fecha_Envio TIMESTAMP NOT NULL, Curso_Academico VARCHAR(10) NOT NULL, ASIGNATURA INTEGER NOT NULL, GRUPO INTEGER NOT NULL, PRIMARY KEY (contieneEncuestas_Fecha_Envio, Curso_Academico, ASIGNATURA, GRUPO))
ALTER TABLE ASIGNATURA ADD CONSTRAINT FK_ASIGNATURA_TITULACION_Codigo_Titulacion FOREIGN KEY (TITULACION_Codigo_Titulacion) REFERENCES TITULACION (Codigo_Titulacion)
ALTER TABLE CLASE ADD CONSTRAINT FK_CLASE_ASIGNATURA_Referencia FOREIGN KEY (ASIGNATURA_Referencia) REFERENCES ASIGNATURA (Referencia)
ALTER TABLE CLASE ADD CONSTRAINT FK_CLASE_GRUPO_ID FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO (ID)
ALTER TABLE ENCUESTA ADD CONSTRAINT FK_ENCUESTA_EXPEDIENTE_Numero_Expediente FOREIGN KEY (EXPEDIENTE_Numero_Expediente) REFERENCES EXPEDIENTE (Numero_Expediente)
ALTER TABLE EXPEDIENTE ADD CONSTRAINT FK_EXPEDIENTE_ALUMNO_ID FOREIGN KEY (ALUMNO_ID) REFERENCES ALUMNO (ID)
ALTER TABLE EXPEDIENTE ADD CONSTRAINT FK_EXPEDIENTE_TITULACION_Codigo_Titulacion FOREIGN KEY (TITULACION_Codigo_Titulacion) REFERENCES TITULACION (Codigo_Titulacion)
ALTER TABLE GRUPO ADD CONSTRAINT FK_GRUPO_TITULACION_Codigo_Titulacion FOREIGN KEY (TITULACION_Codigo_Titulacion) REFERENCES TITULACION (Codigo_Titulacion)
ALTER TABLE GRUPO ADD CONSTRAINT FK_GRUPO_GRUPO_ID FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO (ID)
ALTER TABLE GRUPOSPORASIG ADD CONSTRAINT FK_GRUPOSPORASIG_GRUPO_ID FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO (ID)
ALTER TABLE GRUPOSPORASIG ADD CONSTRAINT FK_GRUPOSPORASIG_ASIGNATURA_Referencia FOREIGN KEY (ASIGNATURA_Referencia) REFERENCES ASIGNATURA (Referencia)
ALTER TABLE MATRICULA ADD CONSTRAINT FK_MATRICULA_EXPEDIENTE_Numero_Expediente FOREIGN KEY (EXPEDIENTE_Numero_Expediente) REFERENCES EXPEDIENTE (Numero_Expediente)
ALTER TABLE ASIGNATURA_MATRICULA ADD CONSTRAINT FK_ASIGNATURA_MATRICULA_CURSOACADEMICO FOREIGN KEY (CURSOACADEMICO, EXPEDIENTE) REFERENCES MATRICULA (CURSOACADEMICO, EXPEDIENTE)
ALTER TABLE ASIGNATURA_MATRICULA ADD CONSTRAINT FK_ASIGNATURA_MATRICULA_ASIGNATURA_Referencia FOREIGN KEY (ASIGNATURA_Referencia) REFERENCES ASIGNATURA (Referencia)
ALTER TABLE ASIGNATURA_MATRICULA ADD CONSTRAINT FK_ASIGNATURA_MATRICULA_GRUPO_ID FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO (ID)
ALTER TABLE JND_Centro_Titulacion ADD CONSTRAINT FK_JND_Centro_Titulacion_Centro_FK FOREIGN KEY (Centro_FK) REFERENCES CENTRO (ID)
ALTER TABLE JND_Centro_Titulacion ADD CONSTRAINT FK_JND_Centro_Titulacion_Titulacion_FK FOREIGN KEY (Titulacion_FK) REFERENCES TITULACION (Codigo_Titulacion)
ALTER TABLE ENCUESTA_GRUPOSPORASIG ADD CONSTRAINT FK_ENCUESTA_GRUPOSPORASIG_Curso_Academico FOREIGN KEY (Curso_Academico, ASIGNATURA, GRUPO) REFERENCES GRUPOSPORASIG (Curso_Academico, ASIGNATURA, GRUPO)
ALTER TABLE ENCUESTA_GRUPOSPORASIG ADD CONSTRAINT ENCUESTAGRUPOSPORASIGcontieneEncuestas_Fecha_Envio FOREIGN KEY (contieneEncuestas_Fecha_Envio) REFERENCES ENCUESTA (Fecha_Envio)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT NUMERIC(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
