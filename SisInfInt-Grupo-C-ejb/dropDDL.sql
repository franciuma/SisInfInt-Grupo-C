ALTER TABLE ASIGNATURA DROP CONSTRAINT FK_ASIGNATURA_TITULACION_Codigo_Titulacion
ALTER TABLE CLASE DROP CONSTRAINT FK_CLASE_ASIGNATURA_Referencia
ALTER TABLE CLASE DROP CONSTRAINT FK_CLASE_GRUPO_ID
ALTER TABLE ENCUESTA DROP CONSTRAINT FK_ENCUESTA_EXPEDIENTE_Numero_Expediente
ALTER TABLE EXPEDIENTE DROP CONSTRAINT FK_EXPEDIENTE_ALUMNO_ID
ALTER TABLE EXPEDIENTE DROP CONSTRAINT FK_EXPEDIENTE_TITULACION_Codigo_Titulacion
ALTER TABLE GRUPO DROP CONSTRAINT FK_GRUPO_TITULACION_Codigo_Titulacion
ALTER TABLE GRUPO DROP CONSTRAINT FK_GRUPO_GRUPO_ID
ALTER TABLE GRUPOSPORASIG DROP CONSTRAINT FK_GRUPOSPORASIG_GRUPO_ID
ALTER TABLE GRUPOSPORASIG DROP CONSTRAINT FK_GRUPOSPORASIG_ASIGNATURA_Referencia
ALTER TABLE MATRICULA DROP CONSTRAINT FK_MATRICULA_EXPEDIENTE_Numero_Expediente
ALTER TABLE ASIGNATURA_MATRICULA DROP CONSTRAINT FK_ASIGNATURA_MATRICULA_CURSOACADEMICO
ALTER TABLE ASIGNATURA_MATRICULA DROP CONSTRAINT FK_ASIGNATURA_MATRICULA_ASIGNATURA_Referencia
ALTER TABLE ASIGNATURA_MATRICULA DROP CONSTRAINT FK_ASIGNATURA_MATRICULA_GRUPO_ID
ALTER TABLE JND_Centro_Titulacion DROP CONSTRAINT FK_JND_Centro_Titulacion_Centro_FK
ALTER TABLE JND_Centro_Titulacion DROP CONSTRAINT FK_JND_Centro_Titulacion_Titulacion_FK
ALTER TABLE ENCUESTA_GRUPOSPORASIG DROP CONSTRAINT FK_ENCUESTA_GRUPOSPORASIG_Curso_Academico
ALTER TABLE ENCUESTA_GRUPOSPORASIG DROP CONSTRAINT ENCUESTAGRUPOSPORASIGcontieneEncuestas_Fecha_Envio
DROP TABLE ALUMNO
DROP TABLE ASIGNATURA
DROP TABLE CENTRO
DROP TABLE CLASE
DROP TABLE ENCUESTA
DROP TABLE EXPEDIENTE
DROP TABLE GRUPO
DROP TABLE GRUPOSPORASIG
DROP TABLE MATRICULA
DROP TABLE TITULACION
DROP TABLE ASIGNATURA_MATRICULA
DROP TABLE JND_Centro_Titulacion
DROP TABLE ENCUESTA_GRUPOSPORASIG
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
