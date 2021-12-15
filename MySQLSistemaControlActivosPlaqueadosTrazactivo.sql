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
(default, "archivador"), (default, "gabinete"), (default, "biblioteca"),
(default, "escritorio para oficina"), (default, "armario para oficina"), (default, "mesa para oficina"),
(default, "silla para oficina"), (default, "banco para oficina"),
(default, "mueble de varios tipos y uso en oficina"), (default, "mobiliario para computadora de oficina"),
(default, "juego de comedor para oficina"), (default, "juego de sala para oficina"),
(default, "estantería"), (default, "pieza y obra de colección"),
(default, "pintura"), (default, "escultura"), (default, "enciclopedia"),
(default, "video educacional"), (default, "máquina destructora de documentos"),
(default, "trituradora de documentos"),
(default, "máquina encuadernadora"), (default, "empastadora de litografía y poligrafía"),
(default, "artefacto eléctrico de oficina"), (default, "artefacto electrónico de oficina"),
(default, "calculadora"), (default, "sumadora"),
(default, "fotocopiadora"), (default, "máquina de escribir"),
(default, "abanico"), (default, "ventilador"), (default, "aire acondicionado"),
(default, "lámpara"), (default, "reloj"), (default, "reloj marcador"),
(default, "tarjetero");
-- Equipo de cómputo
insert into trazactivo.descripcion_activo values 
(default, "cpu"), (default, "monitor"), (default, "teclado"),
(default, "mouse o ratón"), (default, "laptop"),
(default, "notebook"), (default, "servidor"), (default, "impresora"),
(default, "licencia para software"), (default, "disco duro interno"), (default, "disco duro externo"),
(default, "módem"), (default, "cámara web"), (default, "scanner"),
(default, "unidad lectora"), (default, "quemador de discos interno"), (default, "quemador de discos externo"),
(default, "micrófono para multimedia"), (default, "parlantes para multimedia"),
(default, "auricular para multimedia"), (default, "headset para multimedia"),
(default, "llave maya");
-- Equipo electrónico
insert into trazactivo.descripcion_activo values 
(default, "equipo de grabación audio y video"), (default, "cámara para fotografía"),
(default, "cámara de video"),
(default, "cámara de seguridad"), (default, "televisor"), (default, "pantalla"),
(default, "dvd"), (default, "vhs"), (default, "otros proyectores de video"),
(default, "pantalla reflectora"), (default, "video beam"), (default, "proyector"),
(default, "equipo de sonido"), (default, "grabadora"), (default, "radio"),
(default, "amplificador"), (default, "planta electrónica"), (default, "pedestal"),
(default, "audífono"), (default, "megáfono"), (default, "grabadora tipo periodista"),
(default, "tarjeta de memoria para cámara digital"),
(default, "router"), (default, "hub o concentrador"), (default, "switch"),
(default, "equipo de proyección y videoconferencia"), (default, "equipo de comunicación"),
(default, "antena"),
(default, "central telefónica"), (default, "teléfono"), (default, "fax"),
(default, "equipo de radiocomunicación"), (default, "radar");
-- Herramientas eléctricas y manuales
insert into trazactivo.descripcion_activo values 
(default, "equipo solar colector"), (default, "equipo solar panel"), (default, "equipo solar rastreador"),
(default, "equipo solar planta eléctrica"), (default, "equipo para soldar"), (default, "equipo de bombeo"),
(default, "generador de electricidad"), (default, "equipo para pintar"), (default, "herramienta de mecánica automotriz"),
(default, "equipo de mecánica automotriz"), (default, "equipo de medición"), (default, "motor"),
(default, "equipo hidráulico"), (default, "cortadora"), (default, "máquina de coser");
-- Equipo de aula
insert into trazactivo.descripcion_activo values 
(default, "pizarra"), (default, "pupitre"),
(default, "escritorio para colegio"),
(default, "armario para colegio"), (default, "mesa para colegio"),
(default, "silla para colegio"), (default, "banco para colegio");
-- Otros equipos
insert into trazactivo.descripcion_activo values 
(default, "equipo sanitario"), (default, "equipo de laboratorio"), (default, "equipo de investigación"),
(default, "equipo meteorológico"), (default, "equipo médico"), (default, "mobiliario médico"),
(default, "equipo de rescate"), (default, "equipo de primeros auxilios"), (default, "equipo para animal"),
(default, "equipo veterinario");
-- Vehículos
insert into trazactivo.descripcion_activo values 
(default, "vehículo liviano"), (default, "vehículo de carga"), (default, "bus"),
(default, "microbús"), (default, "vehículo para transporte"),
(default, "vehículo para levantamiento de carga"),
(default, "motocicleta"), (default, "cuadriciclo"), (default, "bicicleta"),
(default, "lancha"), (default, "bote");
-- Instrumentos musicales
insert into trazactivo.descripcion_activo values 
(default, "guitarra"), (default, "flauta"), (default, "tambor");
-- Deportivo
insert into trazactivo.descripcion_activo values 
(default, "guante para baseball"), (default, "pelota"), (default, "rodillera");
-- Cocina
insert into trazactivo.descripcion_activo values 
(default, "olla"), (default, "sartén"), (default, "percolador"),
(default, "coffee maker"), (default, "cocina eléctrica"), (default, "cocina de gas"),
(default, "refrigeradora"), (default, "congelador"), (default, "enfriador"),
(default, "plantilla eléctrica"), (default, "plantilla de gas"), (default, "extintor"),
(default, "horno eléctrico"), (default, "microondas"), (default, "freidor"),
(default, "tanque de agua"), (default, "mueble para fregadero"), (default, "balanza"),
(default, "báscula"), (default, "romana"), (default, "extractor");
-- Implementos y equipo duradero de limpieza, seguridad y mantenimiento
insert into trazactivo.descripcion_activo values 
(default, "máquina para lavado a presión"), (default, "aspiradora"), (default, "lavadora"),
(default, "trapeador"), (default, "cepillo eléctrico"), (default, "secamanos"),
(default, "batería sanitaria"), (default, "inodoro"), (default, "urinal"),
(default, "lavamanos"), (default, "cortadora de zacate"), (default, "podadora de zacate"),
(default, "motoguadaña"), (default, "motosierra"), (default, "arma de fuego"),
(default, "caseta de seguridad"), (default, "alarma"), (default, "sirena"),
(default, "sensor"), (default, "reflector"), (default, "lámpara de emergencia");

-- Tabla estado
insert into trazactivo.estado_activo values
(default, "excelente"),
(default, "bueno"),
(default, "regular"),
(default, "malo");

-- Tabla aula
insert into trazactivo.numero_aula values
(default, "laboratorio 5"),
(default, "laboratorio 6"),
(default, "laboratorio 15"),
(default, "laboratorio portátil 1"),
(default, "laboratorio portátil 2"),
(default, "aula 01"), (default, "aula 02"), (default, "aula 03"),
(default, "aula 04"), (default, "aula 05"), (default, "aula 06"),
(default, "aula 07"), (default, "aula 08"), (default, "aula 09"),
(default, "aula 10"), (default, "aula 11"), (default, "aula 12"),
(default, "aula 13"), (default, "aula 14"), (default, "aula 15"),
(default, "aula 16"), (default, "aula 17"), (default, "aula 18"),
(default, "aula 19"), (default, "aula 20"), (default, "aula 21"),
(default, "aula 22"), (default, "aula 23"), (default, "aula 24"),
(default, "aula 25"), (default, "aula 26"), (default, "aula 27"),
(default, "aula 28"),
(default, "biblioteca"),
(default, "dirección"),
(default, "coordinación técnica"),
(default, "coordinación con la empresa"),
(default, "orientación"),
(default, "auxiliar administrativo"),
(default, "asistente de dirección");

-- Tabla especialidad
insert into trazactivo.especialidad_academica values
(default, "técnica"),
(default, "curso libre"),
(default, "plan de estudios"),
(default, "administrativo");

-- Tabla adquisicion
insert into trazactivo.modo_adquisicion values
(default, "por junta administrativa"),
(default, "por recursos del mep"),
(default, "por recursos del mep ley 7372"),
(default, "por donación persona física"),
(default, "por donación persona jurídica");

-- Tabla tipo_anotacion
insert into trazactivo.tipo_anotacion values
(default, "actualización de estado"),
(default, "robo o hurto"),
(default, "cambio de ubicación"),
(default, "baja de bienes"),
(default, "verificación aleatoria de inventario"),
(default, "reparación");


/******************************************
Insertar datos a la tabla activo - Ejemplos
******************************************/

insert into trazactivo.activo values
(default, 4864, 98, default, default, default, 1, 12, 39, default, default, default, default, default, default),
(2, 4864, 98,   default, default, default, 1, 12, 40, 1, 0,  2, 1, 1, 1),
(3, 4864, 98,   default, default, default, 1, 12, 41, 2, 0,  2, 1, 2, 3),
(4, 4864, 80,   default, default, default, 1, 12, 42, 4, 1,  3, 1, 1, 3),
(5, 4864, 80,   default, default, default, 1, 12, 43, 1, 0,  2, 2, 1, 1),
(6, 4864, 80,   default, default, default, 1, 14, 44, 2, 0,  8, 2, 2, 3),
(7, 4864, 30,   default, default, default, 1, 14, 45, 4, 1, 13, 1, 2, 3),
(8, 4864, 102,  default, default, default, 1, 14, 46, 1, 0, 10, 3, 2, 1),
(9, 4864, 102,  default, default, default, 1, 14, 47, 2, 1, 10, 3, 1, 3),
(10, 4864, 102, default, default, default, 1, 15, 48, 1, 0, 10, 4, 1, 3);

-- Tabla responsable
insert into trazactivo.persona_responsable values
(1, "profesor 1"),
(2, "profesor 2"),
(3, "profesor 3");

-- Tabla activo_observacion
insert into trazactivo.activo_observacion values
(default, 2, 4864, 2, 10, 30, 3, default),
(default, 2, 4864, 2, 15, 13, 3, default),
(default, 2, 4864, 2, 12, 30, 3, default),
(default, 4, 4864, 2, 25, 15, 3, default),
(default, 4, 4864, 2, 27, 50, 4, default),
(default, 5, 4864, 2, 23, 30, 3, default);
