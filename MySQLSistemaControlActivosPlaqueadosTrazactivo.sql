/****************************************************
SISTEMA DE CONTROL PARA ACTIVOS PLAQUEADOS TRAZACTIVO
****************************************************/

create database if not exists `trazactivo`;
use `trazactivo`;

/****************
Tabla secundarias
****************/

create table `descripcion_activo` (
  `id_descripcion_activo` int not null auto_increment unique comment 'Descripción Activo Id',
  `detalle_descripcion_activo` varchar(80) not null comment 'Descripción Activo descripción sencilla',
  constraint PKDESCRIPCIONACTIVO primary key(`id_descripcion_activo`)  comment 'Tabla descripcion_activo llave primaria'
);
create table `estado_activo` (
  `id_estado_activo` int not null auto_increment unique comment 'Estado Activo Id',
  `detalle_estado_activo` varchar(80) not null comment 'Estado Activo descripción sencilla',
  constraint PKESTADOACTIVO primary key(`id_estado_activo`) comment 'Tabla estado_activo llave primaria'
);
create table `numero_aula` (
  `id_numero_aula` int not null auto_increment unique comment 'Número Aula Id',
  `detalle_numero_aula` varchar(80) not null comment 'Número Aula descripción sencilla',
  constraint PKNUMEROAULA primary key(`id_numero_aula`) comment 'Tabla numero_aula llave primaria'
);
create table `especialidad_academica` (
  `id_especialidad_academica` int not null auto_increment unique comment 'Especialidad Académica Id',
  `detalle_especialidad_academica` varchar(80) not null comment 'Especialidad Académica descripción sencilla',
  constraint PKESPECIALIDADACADEMICA primary key(`id_especialidad_academica`)  comment 'Tabla especialidad_academica llave primaria'
);
create table `persona_responsable` (
  `id_persona_responsable` int not null auto_increment unique comment 'Persona Responsable Id',
  `detalle_persona_responsable` varchar(80) not null comment 'Persona Responsable descripción sencilla',
  constraint PKPERSONARESPONSABLE primary key(`id_persona_responsable`)  comment 'Tabla persona_responsable llave primaria'
);
create table `modo_adquisicion` (
  `id_modo_adquisicion` int not null auto_increment unique comment 'Modo Adquisición Id',
  `detalle_modo_adquisicion` varchar(80) not null comment 'Modo Adquisición descripción sencilla',
  constraint PKMODOADQUISICION primary key(`id_modo_adquisicion`) comment 'Tabla modo_adquisicion llave primaria'
);
create table `tipo_anotacion` (
  `id_tipo_anotacion` int not null auto_increment unique comment 'Tipo Anotación Id',
  `detalle_tipo_anotacion` varchar(80) not null comment 'Tipo Anotación descripción sencilla',
  constraint PKTIPOANOTACION primary key(`id_tipo_anotacion`) comment 'Tabla tipo_anotacion llave primaria'
);

/***********************
Tabla principal - activo
***********************/

create table `activo` (
  -- [Campos obligatorios (not null) - Libro de Inventario]
  -- 1. Número de identificación del activo (placa)
  `numero_activo` smallint not null auto_increment unique comment 'Consecutivo del activo',
  `numero_junta` smallint not null comment 'Consecutivo de la junta administrativa',
  -- 2. Descripción
  -- id_descripcion_activo referencia a tabla descripcion_activo
  `id_descripcion_activo` int comment 'Descripción del activo',
  -- 3. Manufacturación
  `marca` varchar(100) not null default 'no indica' comment 'Manufacturación marca',
  `modelo` varchar(100) not null default 'no indica' comment 'Manufacturación modelo',
  `serie` varchar(100) not null default 'no indica' comment 'Manufacturación serie',
  -- 4. Registrado en
  `tomo` smallint not null comment 'Registrado en tomo',
  `folio` smallint not null comment 'Registrado en folio',
  `asiento` smallint not null  comment 'Registrado en asiento',
  
  -- [Campos opcionales]
  -- 5. Estado
  -- id_estado_activo referencia a tabla estado_activo -- para 1 solo estado físico
  `id_estado_activo` int comment 'Estado del activo',
    -- en_reparacion referencia un campo [true][false]
  `en_reparacion` boolean default false comment 'Estado en reparación',
  -- 6. Ubicación
  -- id_numero_aula referencia a tabla numero_aula -- para 1 sola aula en ubicación
  `id_numero_aula` int comment 'Ubicación número aula',
  -- 7. Especialidad Académica
  -- id_especialidad_academica referencia a tabla especialidad_academica -- para 1 sola especialidad académica
  `id_especialidad_academica` int comment 'Especialidad académica',
  -- 8. Persona Responsable
  -- id_persona_responsable referencia a tabla persona_responsable -- para 1 sola persona responsable
  `id_persona_responsable` int comment 'Persona responsable',
  -- 9. Modo de adquisición
  -- id_modo_adquisicion referencia a tabla modo_adquisicion -- para 1 solo tipo de adquisición
  `id_modo_adquisicion` int comment 'Modo de adquisición'
  -- 10. Observaciones
  -- se omite observaciones -- para varias observaciones - tabla intermedia
);

alter table `trazactivo`.`activo` add constraint PKACTIVO 
			primary key(`numero_activo`, `numero_junta`)
            comment 'Tabla activo llave primaria';
            -- Tabla activo llave primaria
alter table `trazactivo`.`activo` add constraint FKDESCRIPCIONACTIVO 
			foreign key(`id_descripcion_activo`) references `trazactivo`.`descripcion_activo`(`id_descripcion_activo`);
            -- Tabla activo llave foránea
alter table `trazactivo`.`activo` add constraint FKESTADOACTIVO
			foreign key(`id_estado_activo`) references `trazactivo`.`estado_activo`(`id_estado_activo`);
            -- Tabla activo llave foránea
alter table `trazactivo`.`activo` add constraint FKNUMEROAULA
			foreign key(`id_numero_aula`) references `trazactivo`.`numero_aula`(`id_numero_aula`);
alter table `trazactivo`.`activo` add constraint FKESPECIALIDADACADEMICA
       foreign key (`id_especialidad_academica`) references `trazactivo`.`especialidad_academica` (`id_especialidad_academica`);
alter table `trazactivo`.`activo` add constraint FKPERSONARESPONSABLE
       foreign key (`id_persona_responsable`) references `trazactivo`.`persona_responsable` (`id_persona_responsable`);
alter table `trazactivo`.`activo` add constraint FKMODOADQUISICION
			foreign key(`id_modo_adquisicion`) references `trazactivo`.`modo_adquisicion`(`id_modo_adquisicion`);
            
/****************
Tabla intermedias
****************/

create table `activo_observacion` (
  `id_activo_observacion` int not null auto_increment unique comment 'Activo Observación Id',
  `numero_activo` smallint not null comment 'Consecutivo del activo',
  `numero_junta` smallint not null comment 'Consecutivo de la junta administrativa',
  -- Referencia
  `tomo` smallint not null comment 'Registrado en tomo',
  `folio` smallint not null comment 'Registrado en folio',
  `asiento` smallint not null  comment 'Registrado en asiento',
  -- Tipo de anotación
  -- id_tipo_anotacion referencia a tabla tipo_anotacion -- para 1 solo tipo de anotación
  `id_tipo_anotacion` int comment 'Tipo Anotacion Id',
  -- Otro detalle
  `otro_detalle` varchar(255) comment 'Otro detalle',
  constraint PKOBSERVACION primary key(`id_activo_observacion`) comment 'Tabla activo observación llave primaria',
  constraint FKOBSERVACION1 foreign key(`numero_activo`,`numero_junta`) 
             references `trazactivo`.`activo`(`numero_activo`,`numero_junta`) on delete cascade,
             -- Tabla activo llave foránea
  constraint FKOBSERVACION2 foreign key(`id_tipo_anotacion`)
             references `trazactivo`.`tipo_anotacion`(`id_tipo_anotacion`)
             -- Tabla tipo_anotacion llave foránea
);


/**************************
Insertar datos a las tablas
**************************/

-- Tabla descripción
-- Fuente: Procedimiento general para el levantamiento del inventario en centros educativos
-- Fuente: Directriz DVM-PICR-235-2014, San José, 12 de marzo, 2014. MEP.
-- Mobiliario y equipo de oficina
insert into trazactivo.descripcion_activo values 
(default, "No indica"),
(default, "Archivador"), (default, "Gabinete"), (default, "Biblioteca"),
(default, "Escritorio para oficina"), (default, "Armario para oficina"), (default, "Mesa para oficina"),
(default, "Silla para oficina"), (default, "Banco para oficina"),
(default, "Mueble de varios tipos y uso en oficina"), (default, "Mobiliario para computadora de oficina"),
(default, "Juego de comedor para oficina"), (default, "Juego de sala para oficina"),
(default, "Estantería"), (default, "Pieza y obra de colección"),
(default, "Pintura"), (default, "Escultura"), (default, "Enciclopedia"),
(default, "Video educacional"), (default, "Máquina destructora de documentos"),
(default, "Trituradora de documentos"),
(default, "Máquina encuadernadora"), (default, "Empastadora de litografía y poligrafía"),
(default, "Artefacto eléctrico de oficina"), (default, "Artefacto electrónico de oficina"),
(default, "Calculadora"), (default, "Sumadora"),
(default, "Fotocopiadora"), (default, "Máquina de escribir"),
(default, "Abanico"), (default, "Ventilador"), (default, "Aire acondicionado"),
(default, "Lámpara"), (default, "Reloj"), (default, "Reloj marcador"),
(default, "Tarjetero");
-- Equipo de cómputo
insert into trazactivo.descripcion_activo values 
(default, "CPU"), (default, "Monitor"), (default, "Teclado"),
(default, "Mouse o ratón"), (default, "Laptop"),
(default, "Notebook"), (default, "Servidor"), (default, "Impresora"),
(default, "Licencia para software"), (default, "Disco duro interno"), (default, "Disco duro externo"),
(default, "Módem"), (default, "Cámara web"), (default, "Scanner"),
(default, "Unidad lectora"), (default, "Quemador de discos interno"), (default, "Quemador de discos externo"),
(default, "Micrófono para multimedia"), (default, "Parlantes para multimedia"),
(default, "Auricular para multimedia"), (default, "Headset para multimedia"),
(default, "Llave maya");
-- Equipo electrónico
insert into trazactivo.descripcion_activo values 
(default, "Equipo de grabación audio y video"), (default, "Cámara para fotografía"),
(default, "Cámara de video"),
(default, "Cámara de seguridad"), (default, "Televisor"), (default, "Pantalla"),
(default, "DVD"), (default, "VHS"), (default, "Otros proyectores de video"),
(default, "Pantalla reflectora"), (default, "Video beam"), (default, "Proyector"),
(default, "Equipo de sonido"), (default, "Grabadora"), (default, "Radio"),
(default, "Amplificador"), (default, "Planta electrónica"), (default, "Pedestal"),
(default, "Audífono"), (default, "Megáfono"), (default, "Grabadora tipo periodista"),
(default, "Tarjeta de memoria para cámara digital"),
(default, "Router"), (default, "Hub o concentrador"), (default, "Switch"),
(default, "Equipo de proyección y videoconferencia"), (default, "Equipo de comunicación"),
(default, "Antena"),
(default, "Central telefónica"), (default, "Teléfono"), (default, "Fax"),
(default, "Equipo de radiocomunicación"), (default, "Radar");
-- Herramientas eléctricas y manuales
insert into trazactivo.descripcion_activo values 
(default, "Equipo solar colector"), (default, "Equipo solar panel"), (default, "Equipo solar rastreador"),
(default, "Equipo solar planta eléctrica"), (default, "Equipo para soldar"), (default, "Equipo de bombeo"),
(default, "Generador de electricidad"), (default, "Equipo para pintar"), (default, "Herramienta de mecánica automotriz"),
(default, "Equipo de mecánica automotriz"), (default, "Equipo de medición"), (default, "Motor"),
(default, "Equipo hidráulico"), (default, "Cortadora"), (default, "Máquina de coser");
-- Equipo de aula
insert into trazactivo.descripcion_activo values 
(default, "Pizarra"), (default, "Pupitre"),
(default, "Escritorio para colegio"),
(default, "Armario para colegio"), (default, "Mesa para colegio"),
(default, "Silla para colegio"), (default, "Banco para colegio");
-- Otros equipos
insert into trazactivo.descripcion_activo values 
(default, "Equipo sanitario"), (default, "Equipo de laboratorio"), (default, "Equipo de investigación"),
(default, "Equipo meteorológico"), (default, "Equipo médico"), (default, "Mobiliario médico"),
(default, "Equipo de rescate"), (default, "Equipo de primeros auxilios"), (default, "Equipo para animal"),
(default, "Equipo veterinario");
-- Vehículos
insert into trazactivo.descripcion_activo values 
(default, "Vehículo liviano"), (default, "Vehículo de carga"), (default, "Bus"),
(default, "Microbús"), (default, "Vehículo para transporte"),
(default, "Vehículo para levantamiento de carga"),
(default, "Motocicleta"), (default, "Cuadriciclo"), (default, "Bicicleta"),
(default, "Lancha"), (default, "Bote");
-- Instrumentos musicales
insert into trazactivo.descripcion_activo values 
(default, "Guitarra"), (default, "Flauta"), (default, "Tambor");
-- Deportivo
insert into trazactivo.descripcion_activo values 
(default, "Guante para baseball"), (default, "Pelota"), (default, "Rodillera");
-- Cocina
insert into trazactivo.descripcion_activo values 
(default, "Olla"), (default, "Sartén"), (default, "Percolador"),
(default, "Coffee maker"), (default, "Cocina eléctrica"), (default, "Cocina de gas"),
(default, "Refrigeradora"), (default, "Congelador"), (default, "Enfriador"),
(default, "Plantilla eléctrica"), (default, "Plantilla de gas"), (default, "Extintor"),
(default, "Horno eléctrico"), (default, "Microondas"), (default, "Freidor"),
(default, "Tanque de agua"), (default, "Mueble para fregadero"), (default, "Balanza"),
(default, "Báscula"), (default, "Romana"), (default, "Extractor");
-- Implementos y equipo duradero de limpieza, seguridad y mantenimiento
insert into trazactivo.descripcion_activo values 
(default, "Máquina para lavado a presión"), (default, "Aspiradora"), (default, "Lavadora"),
(default, "Trapeador"), (default, "Cepillo eléctrico"), (default, "Secamanos"),
(default, "Batería sanitaria"), (default, "Inodoro"), (default, "Urinal"),
(default, "Lavamanos"), (default, "Cortadora de zacate"), (default, "Podadora de zacate"),
(default, "Motoguadaña"), (default, "Motosierra"), (default, "Arma de fuego"),
(default, "Caseta de seguridad"), (default, "Alarma"), (default, "Sirena"),
(default, "Sensor"), (default, "Reflector"), (default, "Lámpara de emergencia");

-- Tabla estado
insert into trazactivo.estado_activo values
(default, "No indica"),
(default, "Excelente"),
(default, "Bueno"),
(default, "Regular"),
(default, "Malo");

-- Tabla aula
insert into trazactivo.numero_aula values
(default, "No indica"),
(default, "Laboratorio 5"),
(default, "Laboratorio 6"),
(default, "Laboratorio 15"),
(default, "Laboratorio portátil 1"),
(default, "Laboratorio portátil 2"),
(default, "Aula 01"), (default, "Aula 02"), (default, "Aula 03"),
(default, "Aula 04"), (default, "Aula 05"), (default, "Aula 06"),
(default, "Aula 07"), (default, "Aula 08"), (default, "Aula 09"),
(default, "Aula 10"), (default, "Aula 11"), (default, "Aula 12"),
(default, "Aula 13"), (default, "Aula 14"), (default, "Aula 15"),
(default, "Aula 16"), (default, "Aula 17"), (default, "Aula 18"),
(default, "Aula 19"), (default, "Aula 20"), (default, "Aula 21"),
(default, "Aula 22"), (default, "Aula 23"), (default, "Aula 24"),
(default, "Aula 25"), (default, "Aula 26"), (default, "Aula 27"),
(default, "Aula 28"),
(default, "Biblioteca"),
(default, "Dirección"),
(default, "Coordinación técnica"),
(default, "Coordinación con la empresa"),
(default, "Orientación"),
(default, "Auxiliar administrativo"),
(default, "Asistente de dirección");

-- Tabla especialidad
insert into trazactivo.especialidad_academica values
(default, "No indica"),
(default, "Técnica"),
(default, "Curso libre"),
(default, "Plan de estudios"),
(default, "Administrativo");

-- Tabla adquisicion
insert into trazactivo.modo_adquisicion values
(default, "No indica"),
(default, "Por junta administrativa"),
(default, "Por recursos del MEP"),
(default, "Por recursos del MEP ley 7372"),
(default, "Por donación persona física"),
(default, "Por donación persona jurídica");

-- Tabla tipo_anotacion
insert into trazactivo.tipo_anotacion values
(default, "No indica"),
(default, "Actualización de estado"),
(default, "Robo o hurto"),
(default, "Cambio de ubicación"),
(default, "Baja de bienes"),
(default, "Verificación aleatoria de inventario"),
(default, "Reparación");

-- Tabla responsable
insert into trazactivo.persona_responsable values
(default, "No indica"),
(default, "profesor 1"),
(default, "profesor 2"),
(default, "profesor 3");

/******************************************
Insertar datos a la tabla activo - Ejemplos
******************************************/

insert into trazactivo.activo values
(default, 4864, 41, default, default, default, 1, 10, 40, 1, 0, 2, 1, 4, 1),
(default, 4864, 37, default, default, default, 1, 10, 41, 2, 0, 5, 2, 2, 3),
(default, 4864, 110, default, default, default, 1, 12, 42, 4, 1, 3, 3, 3, 3),
(default, 4864, 154, default, default, default, 1, 12, 43, 1, 0, 7, 2, 1, 1);

-- Tabla activo_observacion
insert into trazactivo.activo_observacion values
(default, 2, 4864, 2, 10, 30, 3, default),
(default, 2, 4864, 2, 15, 13, 3, default),
(default, 2, 4864, 2, 12, 30, 3, default),
(default, 4, 4864, 2, 25, 15, 3, default),
(default, 4, 4864, 2, 27, 50, 4, default),
(default, 1, 4864, 2, 23, 30, 3, default);
