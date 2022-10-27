INSERT INTO `roles` (`ROL`) VALUES ('ROLE_ROOT');
INSERT INTO `roles` (`ROL`) VALUES ('ROLE_ADMIN');
INSERT INTO `roles` (`ROL`) VALUES ('ROLE_MOVIL');
INSERT INTO `roles` (`ROL`) VALUES ('ROLE_DIGI');
INSERT INTO `roles` (`ROL`) VALUES ('ROLE_SUPER');
INSERT INTO `roles` (`ROL`) VALUES ('ROLE_LABO');

INSERT INTO `usuarios_sistema` (`NOMBRE_USUARIO`, `CUENTA_SINEXPIRAR`, `CUENTA_SINBLOQUEAR`, `DESCRIPCION`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `CREDENCIAL_SINEXPIRAR`, `CORREO_ELECTRONICO`, `HABILITADO`, `FECHA_ULTACC`, `FECHA_ULTMODCRED`, `FECHA_ULTMOD`, `USUARIO_ULTMOD`, `CONTRASENA`) VALUES ('admin', 1, 1, 'Administrador', '2021-01-01 12:00:00', 'admin', 1, 'admin@icsnicaragua.org', 1, '2021-01-01 12:00:00', NULL, '2021-01-01 12:00:00', 'admin', '4f4e617568a86abdce12ac9eab87ea40c0d9824a6db6e21fd6d4c54a0b9e5d58ff25e2093f8caee6');
INSERT INTO `usuarios_roles` (`ROL`, `NOMBRE_USUARIO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `ESTADO`) VALUES ('ROLE_ROOT', 'admin', '0', '2021-01-01 12:00:00', 'admin', '1');


/*Plantilla*/
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'heading', 'Aplicación Web','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'title', 'Estudio A2CARES','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'footer', 'Instituto de Ciencias Sostenibles','0','0',0);

/*login page*/
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'login', 'Ingresar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'login.accountExpired', 'Cuenta de usuario ha expirado!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'login.accountLocked', 'Cuenta de usuario esta bloqueada!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'login.accountNotLocked', 'Cuenta de usuario esta desbloqueada!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'login.badCredentials', 'Nombre de usuario o contraseña incorrectos!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'login.credentialsExpired', 'Credenciales de usuario han expirado!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'login.maxSessionsOut', 'Tiene una sesion activa! No puede crear otra!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'login.message', 'Por favor ingresar su nombre de usuario y contraseña','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'login.username', 'Nombre de usuario','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'login.password', 'Contraseña','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'login.forgot.password', 'Olvido contraseña?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'login.userEnabled', 'Usuario esta activo!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'login.userDisabled', 'Usuario esta inactivo!','0','0',0);


/*Menu*/
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'home', 'Inicio','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'dashboard', 'Panel de control','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'admin', 'Administracion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'users', 'Usuarios','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'logout', 'Salir','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'reports', 'Reportes','0','0',0);

/*Usuarios*/
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'username', 'Usuario','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'userdesc', 'Descripcion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'useremail', 'Correo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'userlock', 'Bloqueado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'usercred', 'Contrasena vencida','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'userexp', 'Cuenta vencida','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'userroles', 'Roles','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'user.updated', 'Usuario actualizado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'user.created', 'Usuario creado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'profile', 'Perfil','0','0',0);
/*Accesos*/
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'access', 'Accesos de usuario','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lastAccess', 'Ultimo acceso','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'dateCredentials', 'Ultimo cambio de contrasena','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'session', 'Id de sesion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'ipaddress', 'Direccion IP','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'logindate', 'Fecha ingreso','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'logoutdate', 'Fecha salida','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'logouturl', 'URL salida','0','0',0);

/*Audit trail*/
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'audittrail', 'Bitacora de cambios','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'entityClass', 'Clase','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'entityName', 'Nombre','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'entityProperty', 'Propiedad','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'entityPropertyOldValue', 'Valor anterior','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'entityPropertyNewValue', 'Nuevo valor','0','0',0);

/*Roles*/
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'ROLE_ADMIN', 'Administrador','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'ROLE_QC', 'Usuario web','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'ROLE_MOVIL', 'Usuario movil','0','0',0);

/*Metadata*/
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'createdBy', 'Creado por','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'dateCreated', 'Fecha creacion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'modifiedBy', 'Modificado por','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'dateModified', 'Fecha de modificacion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'active', 'Activo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'addedBy', 'Agregado por','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'dateAdded', 'Fecha','0','0',0);

/*Acciones, todas las paginas*/
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'actions', 'Acciones','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'add', 'Agregar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'save', 'Guardar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'edit', 'Editar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'back', 'Regresar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'cancel', 'Cancelar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'disable', 'Deshabilitar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'enable', 'Habilitar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'end', 'Finalizar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'unlock', 'Desbloquear','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lock', 'Bloquear','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'export', 'Exportar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'ok', 'Aceptar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'search', 'Buscar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'confirm', 'Confirmar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'please.enter', 'Favor ingresar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('delete', 'Eliminar', '0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('generate', 'Generar', '0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('parameter', 'Parametro', '0','0',0);

/*Mensajes generales, todas las paginas*/
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'enabled', 'Habilitado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'locked', 'Bloqueado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'notenabled', 'Deshabilitado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'notlocked', 'Desbloqueado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'process.errors', 'Han ocurrido errores en el proceso!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'process.success', 'El proceso se ha completado exitosamente!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'noResults', 'No hay registros!','0','0',0);

/*Cambio contrasenia*/
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'changepass', 'Cambiar contraseña..','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'credentials.expired', 'Su contrasena ha caducado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'pass.updated', 'Su contrasena ha sido actualizada','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'password.repeat', 'Repita la contrasena','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'Pattern.password.format', 'Al menos 8 caracteres combinando mayusculas, minusculas, numeros y caracteres especiales','0','0',0);

/*Catalogos*/

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SINO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Si/No');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SINO_SI', '1', 'CAT_SINO', NULL, '0', 1, '0', 'Si');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SINO_NO', '0', 'CAT_SINO', NULL, '0', 2, '0', 'No');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SEXO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Sexo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SEXO_M', 'M', 'CAT_SEXO', NULL, '0', 1, '0', 'Masculino');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SEXO_F', 'F', 'CAT_SEXO', NULL, '0', 2, '0', 'Femenino');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_TAMIZAJE', NULL, NULL, NULL, '1', 0, '0', 'Catalogo no acepta tamizaje persona');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_TAMIZAJE_01', '1', 'CAT_NO_TAMIZAJE', NULL, '0', 1, '0', 'No desea participar en el estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_TAMIZAJE_02', '2', 'CAT_NO_TAMIZAJE', NULL, '0', 2, '0', 'Cambiará de Domicilio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_TAMIZAJE_03', '3', 'CAT_NO_TAMIZAJE', NULL, '0', 3, '0', 'Tiene seguro médico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_TAMIZAJE_04', '4', 'CAT_NO_TAMIZAJE', NULL, '0', 4, '0', 'No considera que tiene beneficios en el estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_TAMIZAJE_05', '5', 'CAT_NO_TAMIZAJE', NULL, '0', 5, '0', 'No desean que sus niños sean muestreados');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_TAMIZAJE_998', '998', 'CAT_NO_TAMIZAJE', NULL, '0', 6, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_PARTICIPA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo no acepta estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_PARTICIPA_01', '1', 'CAT_NO_PARTICIPA', NULL, '0', 1, '0', 'No considera que tiene beneficios en el estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_PARTICIPA_02', '2', 'CAT_NO_PARTICIPA', NULL, '0', 2, '0', 'No desea continuar en el estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_PARTICIPA_03', '3', 'CAT_NO_PARTICIPA', NULL, '0', 3, '0', 'No desean que sus niños sean muestreados');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_PARTICIPA_998', '998', 'CAT_NO_PARTICIPA', NULL, '0', 4, '0', 'Otros motivos');


INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RF_TUTOR', NULL, NULL, NULL, '1', 0, '0', 'Catalogo relacion familiar tutor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RF_TUTOR_01', '1', 'CAT_RF_TUTOR', NULL, '0', 1, '0', 'Madre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RF_TUTOR_02', '2', 'CAT_RF_TUTOR', NULL, '0', 2, '0', 'Padre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RF_TUTOR_03', '3', 'CAT_RF_TUTOR', NULL, '0', 3, '0', 'Abuelo (a)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RF_TUTOR_04', '4', 'CAT_RF_TUTOR', NULL, '0', 4, '0', 'Tio (a)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RF_TUTOR_05', '5', 'CAT_RF_TUTOR', NULL, '0', 5, '0', 'Hermano (a)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RF_TUTOR_06', '6', 'CAT_RF_TUTOR', NULL, '0', 6, '0', 'Otra relación familiar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RF_TUTOR_07', '7', 'CAT_RF_TUTOR', NULL, '0', 7, '0', 'Sin Relación Familiar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RF_TUTOR_08', '8', 'CAT_RF_TUTOR', NULL, '0', 8, '0', 'Participante');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_VIVIENDA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo de vivienda');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_VIVIENDA_01', '1', 'CAT_TIPO_VIVIENDA', NULL, '0', 1, '0', 'Propia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_VIVIENDA_02', '2', 'CAT_TIPO_VIVIENDA', NULL, '0', 2, '0', 'Alquilada');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TMP_RES', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tiempo de residencia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TMP_RES_01', '1', 'CAT_TMP_RES', NULL, '0', 1, '0', 'Menos de un año');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TMP_RES_02', '2', 'CAT_TMP_RES', NULL, '0', 2, '0', 'Un año ó Más');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_VERIFICA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo para verificar procedimientos con el tutor o participante');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_VERIFICA_01', '1', 'CAT_VERIFICA', NULL, '0', 1, '0', 'Una copia del consentimiento ha sido entregada al padre/tutor del part.?');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_VERIFICA_02', '2', 'CAT_VERIFICA', NULL, '0', 2, '0', 'Firmó carta de Consentimiento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_VERIFICA_03', '3', 'CAT_VERIFICA', NULL, '0', 3, '0', 'Fechó carta de Consentimiento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_VERIFICA_04', '4', 'CAT_VERIFICA', NULL, '0', 4, '0', 'Plasmó su huella digital');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_VERIFICA_05', '5', 'CAT_VERIFICA', NULL, '0', 5, '0', 'Testigo firmó y fechó carta de Consentimiento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_VERIFICA_06', '6', 'CAT_VERIFICA', NULL, '0', 6, '0', 'Entendió los procedimientos del estudio');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_TEL', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de telefono');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_TEL_01', '1', 'CAT_TIPO_TEL', NULL, '0', 1, '0', 'Celular');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_TEL_02', '2', 'CAT_TIPO_TEL', NULL, '0', 2, '0', 'Convencional');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_OPER_TEL', NULL, NULL, NULL, '1', 0, '0', 'Catalogo operadoras de telefono');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_OPER_TEL_01', '1', 'CAT_OPER_TEL', NULL, '0', 1, '0', 'Tigo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_OPER_TEL_02', '2', 'CAT_OPER_TEL', NULL, '0', 2, '0', 'Claro');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_OPER_TEL_03', '3', 'CAT_OPER_TEL', NULL, '0', 3, '0', 'Cootel');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_VISITA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo visita no exitosa CP');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_VISITA_01', '1', 'CAT_NO_VISITA', NULL, '0', 1, '0', 'Casa Cerrada');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_VISITA_02', '2', 'CAT_NO_VISITA', NULL, '0', 2, '0', 'Participante ausente');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_VISITA_03', '3', 'CAT_NO_VISITA', NULL, '0', 3, '0', 'No se encontró la casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_VISITA_04', '4', 'CAT_NO_VISITA', NULL, '0', 4, '0', 'Padres Ausentes o Adultos Ausentes');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_VISITA_05', '5', 'CAT_NO_VISITA', NULL, '0', 5, '0', 'Se cambiaron de casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_VISITA_06', '6', 'CAT_NO_VISITA', NULL, '0', 6, '0', 'No se encontró tutor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_VISITA_07', '7', 'CAT_NO_VISITA', NULL, '0', 7, '0', 'Acude a Consulta Medica sin Tutor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_VISITA_08', '8', 'CAT_NO_VISITA', NULL, '0', 8, '0', 'Fuera del pais');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_VISITA_998', '998', 'CAT_NO_VISITA', NULL, '0', 9, '0', 'Otro motivo');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DF', NULL, NULL, NULL, '1', 0, '0', 'Catalogo ubicacion dentro o fuera de casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DF_01', '1', 'CAT_DF', NULL, '0', 1, '0', 'Fuera');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DF_02', '2', 'CAT_DF', NULL, '0', 2, '0', 'Dentro');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DF_03', '3', 'CAT_DF', NULL, '0', 3, '0', 'Dentro y Fuera');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_COMPARTIDO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo llave agua compartida');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_COMPARTIDO_C', '1', 'CAT_COMPARTIDO', NULL, '0', 1, '0', 'Compartido');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_COMPARTIDO_N', '2', 'CAT_COMPARTIDO', NULL, '0', 2, '0', 'No compartido');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_PARED', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo material de paredes casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_PARED_01', '1', 'CAT_MAT_PARED', NULL, '0', 1, '0', 'Madera');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_PARED_02', '2', 'CAT_MAT_PARED', NULL, '0', 2, '0', 'Concreto');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_PARED_03', '3', 'CAT_MAT_PARED', NULL, '0', 3, '0', 'Plástico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_PARED_04', '4', 'CAT_MAT_PARED', NULL, '0', 4, '0', 'Cartón');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_PARED_05', '5', 'CAT_MAT_PARED', NULL, '0', 5, '0', 'Adobe');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_PARED_06', '6', 'CAT_MAT_PARED', NULL, '0', 6, '0', 'Zinc');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_PARED_998', '998', 'CAT_MAT_PARED', NULL, '0', 7, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_PISO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo material de piso casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_PISO_01', '1', 'CAT_MAT_PISO', NULL, '0', 1, '0', 'Concreto');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_PISO_02', '2', 'CAT_MAT_PISO', NULL, '0', 2, '0', 'Ladrillos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_PISO_03', '3', 'CAT_MAT_PISO', NULL, '0', 3, '0', 'Piso de tierra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_PISO_04', '4', 'CAT_MAT_PISO', NULL, '0', 4, '0', 'Cerámica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_PISO_998', '998', 'CAT_MAT_PISO', NULL, '0', 5, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_TECHO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo material de techo casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_TECHO_01', '1', 'CAT_MAT_TECHO', NULL, '0', 1, '0', 'Zinc');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_TECHO_02', '2', 'CAT_MAT_TECHO', NULL, '0', 2, '0', 'Plástico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_TECHO_03', '3', 'CAT_MAT_TECHO', NULL, '0', 3, '0', 'Tejas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MAT_TECHO_998', '998', 'CAT_MAT_TECHO', NULL, '0', 4, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FUN_AIRE', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tiempo de funcionamiento aire acondicionado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FUN_AIRE_D', '1', 'CAT_FUN_AIRE', NULL, '0', 1, '0', 'Día');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FUN_AIRE_N', '2', 'CAT_FUN_AIRE', NULL, '0', 2, '0', 'Noche');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_OTROS_SERVICIOS', NULL, NULL, NULL, '1', 0, '0', 'Catalogo otros servicios no básicos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_OTROS_SERVICIOS_1', '1', 'CAT_OTROS_SERVICIOS', NULL, '0', 1, '0', 'Cable pagado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_OTROS_SERVICIOS_2', '2', 'CAT_OTROS_SERVICIOS', NULL, '0', 2, '0', 'Netflix');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_OTROS_SERVICIOS_3', '3', 'CAT_OTROS_SERVICIOS', NULL, '0', 3, '0', 'Hulu');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_OTROS_SERVICIOS_4', '4', 'CAT_OTROS_SERVICIOS', NULL, '0', 4, '0', 'Amazon Video');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_OTROS_SERVICIOS_998', '998', 'CAT_OTROS_SERVICIOS', NULL, '0', 5, '0', 'Otros');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_COCINA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo frecuencia de uso cocina de lenia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_COCINA_1', '1', 'CAT_TIPO_COCINA', NULL, '0', 1, '0', 'Gas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_COCINA_2', '2', 'CAT_TIPO_COCINA', NULL, '0', 2, '0', 'Eléctrica');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_COCINA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo frecuencia de uso cocina de lenia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_COCINA_D', '1', 'CAT_FREC_COCINA', NULL, '0', 1, '0', 'Diario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_COCINA_S', '2', 'CAT_FREC_COCINA', NULL, '0', 2, '0', 'Semanal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_COCINA_Q', '3', 'CAT_FREC_COCINA', NULL, '0', 3, '0', 'Quincenal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_COCINA_M', '4', 'CAT_FREC_COCINA', NULL, '0', 4, '0', 'Mensual');

/*encuesta participante*/
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NIV_EDU', NULL, NULL, NULL, '1', 0, '0', 'Catalogo niveles de educacion');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NIV_EDU_01', '1', 'CAT_NIV_EDU', NULL, '0', 1, '0', 'Ninguno');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NIV_EDU_02', '2', 'CAT_NIV_EDU', NULL, '0', 2, '0', 'Primaria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NIV_EDU_03', '3', 'CAT_NIV_EDU', NULL, '0', 3, '0', 'Secundaria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NIV_EDU_04', '4', 'CAT_NIV_EDU', NULL, '0', 4, '0', 'Técnico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NIV_EDU_05', '5', 'CAT_NIV_EDU', NULL, '0', 5, '0', 'Universitario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NIV_EDU_06', '6', 'CAT_NIV_EDU', NULL, '0', 6, '0', 'Profesional');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NIV_EDU_07', '7', 'CAT_NIV_EDU', NULL, '0', 7, '0', 'No sabe');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_EMAN', NULL, NULL, NULL, '1', 0, '0', 'Catalogo razon de emancipación');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_EMAN_01', '1', 'CAT_RAZON_EMAN', NULL, '0', 1, '0', 'Embarazada');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_EMAN_02', '2', 'CAT_RAZON_EMAN', NULL, '0', 2, '0', 'Casad@');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_EMAN_03', '3', 'CAT_RAZON_EMAN', NULL, '0', 3, '0', 'Con hijos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_EMAN_998', '4', 'CAT_RAZON_EMAN', NULL, '0', 4, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIP_TRABAJO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de trabajo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIP_TRABAJO_01', '1', 'CAT_TIP_TRABAJO', NULL, '0', 1, '0', 'Trabajo Formal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIP_TRABAJO_02', '2', 'CAT_TIP_TRABAJO', NULL, '0', 2, '0', 'Trabajo Informal');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_FUMA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo frecuencia de uso cocina de lenia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_FUMA_01', '1', 'CAT_FREC_FUMA', NULL, '0', 1, '0', 'Diariamente');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_FUMA_02', '2', 'CAT_FREC_FUMA', NULL, '0', 2, '0', 'No diario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_FUMA_03', '3', 'CAT_FREC_FUMA', NULL, '0', 3, '0', 'Semanalmente');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_FUMA_04', '4', 'CAT_FREC_FUMA', NULL, '0', 4, '0', 'Mensualmente');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_FUMA_05', '5', 'CAT_FREC_FUMA', NULL, '0', 5, '0', 'De vez en cuando');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SND', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Si/No/Desconocido');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SND_01', '1', 'CAT_SND', NULL, '0', 1, '0', 'Si');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SND_02', '0', 'CAT_SND', NULL, '0', 2, '0', 'No');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SND_999', '9', 'CAT_SND', NULL, '0', 3, '0', 'Desconocido');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DONDEASISTE', NULL, NULL, NULL, '1', 0, '0', 'Catalogo dónde asiste por enfermedades');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DONDEASISTE_01', '1', 'CAT_DONDEASISTE', NULL, '0', 1, '0', 'Centro de Salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DONDEASISTE_02', '2', 'CAT_DONDEASISTE', NULL, '0', 2, '0', 'Puesto de Salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DONDEASISTE_03', '3', 'CAT_DONDEASISTE', NULL, '0', 3, '0', 'Hospital Público');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DONDEASISTE_04', '4', 'CAT_DONDEASISTE', NULL, '0', 4, '0', 'Hospital Privado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DONDEASISTE_05', '5', 'CAT_DONDEASISTE', NULL, '0', 5, '0', 'Médico Privado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DONDEASISTE_06', '6', 'CAT_DONDEASISTE', NULL, '0', 6, '0', 'Seguro Social');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DONDEASISTE_998', '998', 'CAT_DONDEASISTE', NULL, '0', 7, '0', 'Otro');


INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TURNO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo turnos de estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TURNO_01', '1', 'CAT_TURNO', NULL, '0', 1, '0', 'AM');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TURNO_02', '2', 'CAT_TURNO', NULL, '0', 2, '0', 'PM');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TURNO_03', '3', 'CAT_TURNO', NULL, '0', 3, '0', 'SAB');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TURNO_04', '4', 'CAT_TURNO', NULL, '0', 4, '0', 'DOM');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_GRD_EDU', NULL, NULL, NULL, '1', 0, '0', 'Catalogo grados escolares');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_GRD_EDU_01', '1', 'CAT_GRD_EDU', NULL, '0', 1, '0', 'Preescolar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_GRD_EDU_02', '2', 'CAT_GRD_EDU', NULL, '0', 2, '0', 'Primaria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_GRD_EDU_03', '3', 'CAT_GRD_EDU', NULL, '0', 3, '0', 'Secundaria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_GRD_EDU_04', '4', 'CAT_GRD_EDU', NULL, '0', 4, '0', 'Universidad');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CUIDAN_NINO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo donde cuidad al niño si no va a la escuela');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CUIDAN_NINO_01', '1', 'CAT_CUIDAN_NINO', NULL, '0', 3, '0', 'CDI');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CUIDAN_NINO_02', '2', 'CAT_CUIDAN_NINO', NULL, '0', 2, '0', 'Vecino');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CUIDAN_NINO_03', '3', 'CAT_CUIDAN_NINO', NULL, '0', 1, '0', 'En su casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CUIDAN_NINO_998', '998', 'CAT_CUIDAN_NINO', NULL, '0', 4, '0', 'Otro lugar');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_VIVE_NINO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo con quien vive el niño');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_VIVE_NINO_01', '1', 'CAT_VIVE_NINO', NULL, '0', 1, '0', 'Madre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_VIVE_NINO_02', '2', 'CAT_VIVE_NINO', NULL, '0', 2, '0', 'Padre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_VIVE_NINO_03', '3', 'CAT_VIVE_NINO', NULL, '0', 3, '0', 'Ambos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_VIVE_NINO_998', '998', 'CAT_VIVE_NINO', NULL, '0', 4, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TMP_FIEBRE', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tiempo duró la última fiebre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TMP_FIEBRE_01', '1', 'CAT_TMP_FIEBRE', NULL, '0', 1, '0', 'Menos de 24 Horas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TMP_FIEBRE_02', '0', 'CAT_TMP_FIEBRE', NULL, '0', 2, '0', 'Más de 24 horas');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_CONSULTA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo dónde asiste a consulta');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_CONSULTA_01', '1', 'CAT_LUGAR_CONSULTA', NULL, '0', 1, '0', 'Seguro social - Clinical Previsional');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_CONSULTA_02', '2', 'CAT_LUGAR_CONSULTA', NULL, '0', 2, '0', 'CS Edgar Lang');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_CONSULTA_03', '3', 'CAT_LUGAR_CONSULTA', NULL, '0', 3, '0', 'Hospital');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_CONSULTA_04', '4', 'CAT_LUGAR_CONSULTA', NULL, '0', 4, '0', 'Médicos Privado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_CONSULTA_998', '998', 'CAT_LUGAR_CONSULTA', NULL, '0', 5, '0', 'Otro');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_CONSULTA_05', '5', 'CAT_LUGAR_CONSULTA', NULL, '0', 6, '0', 'Puesto Médico');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_AUTOMED_FIEBRE', NULL, NULL, NULL, '1', 0, '0', 'Catalogo automedicó fiebre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_AUTOMED_FIEBRE_01', '1', 'CAT_AUTOMED_FIEBRE', NULL, '0', 1, '0', 'Acetaminofén');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_AUTOMED_FIEBRE_02', '2', 'CAT_AUTOMED_FIEBRE', NULL, '0', 2, '0', 'Aspirina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_AUTOMED_FIEBRE_03', '3', 'CAT_AUTOMED_FIEBRE', NULL, '0', 3, '0', 'Antibióticos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_AUTOMED_FIEBRE_998', '998', 'CAT_AUTOMED_FIEBRE', NULL, '0', 5, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS', NULL, NULL, NULL, '1', 0, '0', 'Catalogo no acudió al centro de salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS_01', '1', 'CAT_NO_ACUDIO_CS', NULL, '0', 1, '0', 'Era de noche y no pudo llevarlo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS_02', '2', 'CAT_NO_ACUDIO_CS', NULL, '0', 2, '0', 'Estaba grave y fue directo al hospital');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS_03', '3', 'CAT_NO_ACUDIO_CS', NULL, '0', 3, '0', 'La fiebre duró poco tiempo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS_04', '4', 'CAT_NO_ACUDIO_CS', NULL, '0', 4, '0', 'No había quien lo llevara');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS_05', '5', 'CAT_NO_ACUDIO_CS', NULL, '0', 5, '0', 'No pensó que fuera necesario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS_06', '6', 'CAT_NO_ACUDIO_CS', NULL, '0', 6, '0', 'No sabía que hay servicio las 24horas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS_998', '998', 'CAT_NO_ACUDIO_CS', NULL, '0', 7, '0', 'Otras razones');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CENTRO_SALUD', NULL, NULL, NULL, '1', 0, '0', 'Catalogo centros de salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CENTRO_SALUD_01', '1', 'CAT_CENTRO_SALUD', NULL, '0', 1, '0', 'CS Edgar Lang');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CENTRO_SALUD_998', '998', 'CAT_CENTRO_SALUD', NULL, '0', 2, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MESES', NULL, NULL, NULL, '1', 0, '0', 'Catalogo meses del año');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MESES_01', '01', 'CAT_MESES', NULL, '0', 1, '0', 'Enero');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MESES_02', '02', 'CAT_MESES', NULL, '0', 2, '0', 'Febrero');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MESES_03', '03', 'CAT_MESES', NULL, '0', 3, '0', 'Marzo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MESES_04', '04', 'CAT_MESES', NULL, '0', 4, '0', 'Abril');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MESES_05', '05', 'CAT_MESES', NULL, '0', 5, '0', 'Mayo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MESES_06', '06', 'CAT_MESES', NULL, '0', 6, '0', 'Junio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MESES_07', '07', 'CAT_MESES', NULL, '0', 7, '0', 'Julio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MESES_08', '08', 'CAT_MESES', NULL, '0', 8, '0', 'Agosto');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MESES_09', '09', 'CAT_MESES', NULL, '0', 9, '0', 'Septiembre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MESES_10', '10', 'CAT_MESES', NULL, '0', 10, '0', 'Octubre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MESES_11', '11', 'CAT_MESES', NULL, '0', 11, '0', 'Noviembre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MESES_12', '12', 'CAT_MESES', NULL, '0', 12, '0', 'Diciembre');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DURO_ENROJECI', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Cuántos días duró el enrojecimiento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DURO_ENROJECI_01', '1', 'CAT_DURO_ENROJECI', NULL, '0', 1, '0', '1 dia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DURO_ENROJECI_02', '2', 'CAT_DURO_ENROJECI', NULL, '0', 2, '0', '2 a 4 días');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_DURO_ENROJECI_03', '3', 'CAT_DURO_ENROJECI', NULL, '0', 3, '0', '5 a más días');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_QUIEN_FUMA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo quienes fuman en la casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_QUIEN_FUMA_01', '1', 'CAT_QUIEN_FUMA', NULL, '0', 1, '0', 'Madre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_QUIEN_FUMA_02', '2', 'CAT_QUIEN_FUMA', NULL, '0', 2, '0', 'Padre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_QUIEN_FUMA_998', '998', 'CAT_QUIEN_FUMA', NULL, '0', 3, '0', 'Otros');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_PUESTO_SALUD', NULL, NULL, NULL, '1', 0, '0', 'Catalogo puesto de salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_PUESTO_SALUD_01', '1', 'CAT_PUESTO_SALUD', NULL, '0', 1, '0', 'P/S 1');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_PUESTO_SALUD_02', '2', 'CAT_PUESTO_SALUD', NULL, '0', 2, '0', 'P/S 2');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_PUESTO_SALUD_998', '998', 'CAT_PUESTO_SALUD', NULL, '0', 3, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_DIAG', NULL, NULL, NULL, '1', 0, '0', 'Catalogo lugar de diagnostico enfermedades');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_DIAG_01', '1', 'CAT_LUGAR_DIAG', NULL, '0', 1, '0', 'Centro Salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_DIAG_02', '2', 'CAT_LUGAR_DIAG', NULL, '0', 2, '0', 'Puesto Salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_DIAG_03', '3', 'CAT_LUGAR_DIAG', NULL, '0', 3, '0', 'Hospital');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_DIAG_04', '4', 'CAT_LUGAR_DIAG', NULL, '0', 4, '0', 'Médico privado');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_HOSPITAL', NULL, NULL, NULL, '1', 0, '0', 'Catalogo de hospitales');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_HOSPITAL_01', '1', 'CAT_HOSPITAL', NULL, '0', 1, '0', 'Hospital La Mascota');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_HOSPITAL_02', '2', 'CAT_HOSPITAL', NULL, '0', 2, '0', 'Hospital Militar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_HOSPITAL_03', '3', 'CAT_HOSPITAL', NULL, '0', 3, '0', 'Hospital Velez Paiz');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_HOSPITAL_04', '4', 'CAT_HOSPITAL', NULL, '0', 4, '0', 'Hospital Central de Managua');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_HOSPITAL_05', '5', 'CAT_HOSPITAL', NULL, '0', 5, '0', 'Hospital Salud Integral');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_HOSPITAL_06', '6', 'CAT_HOSPITAL', NULL, '0', 6, '0', 'Hospital Cruz Azul');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_HOSPITAL_07', '7', 'CAT_HOSPITAL', NULL, '0', 7, '0', 'Hospital Carlos Roberto Huembes');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_HOSPITAL_998', '998', 'CAT_HOSPITAL', NULL, '0', 8, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_PINCHAZOS', NULL, NULL, NULL, '1', 0, '0', 'Catalogo numero de pinchazos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_PINCHAZOS_01', '0', 'CAT_PINCHAZOS', NULL, '0', 1, '0', '0');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_PINCHAZOS_02', '1', 'CAT_PINCHAZOS', NULL, '0', 2, '0', '1');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_PINCHAZOS_03', '2', 'CAT_PINCHAZOS', NULL, '0', 3, '0', '2');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_PINCHAZOS_04', '3', 'CAT_PINCHAZOS', NULL, '0', 4, '0', '3');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_PINCHAZOS_05', '4', 'CAT_PINCHAZOS', NULL, '0', 5, '0', '4');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_PINCHAZOS_06', '5', 'CAT_PINCHAZOS', NULL, '0', 6, '0', '5');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_MX', NULL, NULL, NULL, '1', 0, '0', 'Catalogo razón no se toma muestra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_MX_01', '1', 'CAT_RAZON_NO_MX', NULL, '0', 1, '0', 'Muestra difícil');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_MX_02', '2', 'CAT_RAZON_NO_MX', NULL, '0', 2, '0', 'Se descanalizó');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_MX_03', '3', 'CAT_RAZON_NO_MX', NULL, '0', 3, '0', 'Se pinchó mas de 2 veces');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_MX_04', '4', 'CAT_RAZON_NO_MX', NULL, '0', 4, '0', 'Participante o tutor no aceptó tomar muestra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_MX_05', '5', 'CAT_RAZON_NO_MX', NULL, '0', 5, '0', 'Participante o tutor después de pinchadazo no desea que se le tome muestra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_MX_998', '998', 'CAT_RAZON_NO_MX', NULL, '0', 6, '0', 'Otra razón');


INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MUROCERCO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tiene muero o cerco');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MUROCERCO_01', '1', 'CAT_MUROCERCO', NULL, '0', 1, '0', 'Muro');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MUROCERCO_02', '2', 'CAT_MUROCERCO', NULL, '0', 2, '0', 'Cerco');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MUROCERCO_03', '3', 'CAT_MUROCERCO', NULL, '0', 3, '0', 'Ambos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_MUROCERCO_996', '996', 'CAT_MUROCERCO', NULL, '0', 4, '0', 'Ninguno');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_INODOROLET', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tiene inodoro o letrina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_INODOROLET_01', '1', 'CAT_INODOROLET', NULL, '0', 1, '0', 'Inodoro');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_INODOROLET_02', '2', 'CAT_INODOROLET', NULL, '0', 2, '0', 'Letrina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_INODOROLET_03', '3', 'CAT_INODOROLET', NULL, '0', 3, '0', 'Ambos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_INODOROLET_996', '996', 'CAT_INODOROLET', NULL, '0', 4, '0', 'Ninguno');

INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('select', '0', '0', '0', 'Seleccione');

/**HOJA CLINICA**/
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('fingering', '0', '0', '0', 'Digitación');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('clinical_sheet', '0', '0', '0', 'Hoja Clinica');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('search_participant', '0', '0', '0', 'Buscar participante');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('search_participant_holder', '0', '0', '0', 'Ingresar código de participante');

INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('datos_personales', '0', '0', '0', 'Datos Personales');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('nombre', '0', '0', '0', 'Nombre:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('fecha_nacimiento', '0', '0', '0', 'Fecha de Nacimiento:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('edad', '0', '0', '0', 'Edad:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('sexo', '0', '0', '0', 'Sexo:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('encabezado', '0', '0', '0', 'Encabezado');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('fecha_consulta', '0', '0', '0', 'Fecha Consulta:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('hora_consulta', '0', '0', '0', 'Hora Consulta:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('datos_enfermeria', '0', '0', '0', 'Datos de enfermería');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('peso', '0', '0', '0', 'Peso (Kg):');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('talla', '0', '0', '0', 'Talla (cm):');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('presion_arterial', '0', '0', '0', 'Presión arterial:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('frecuencia_cardiaca', '0', '0', '0', 'Frecuencia cardiaca:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('temperatura', '0', '0', '0', 'Temperatura:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('saturacion_oxigeno', '0', '0', '0', 'Saturación de oxígeno:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('datos_medico', '0', '0', '0', 'Datos para llenar el médico');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('hora_inicio_consulta', '0', '0', '0', 'Hora de inicio de la consulta:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('consulta', '0', '0', '0', 'Consulta:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lugar_consulta', '0', '0', '0', 'Lugar de consulta:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('signos_vitales', '0', '0', '0', 'Signos vitales del paciente');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('frecuencia_respiratoria', '0', '0', '0', 'Frecuencia respiratoria:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('fecha_inicio_sintomas', '0', '0', '0', 'Fecha de inicio los síntomas:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('fecha_inicio_fiebre', '0', '0', '0', 'Fecha de inicio la fiebre:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ultimo_dia_fiebre', '0', '0', '0', 'Ultimo día de fiebre:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('hora', '0', '0', '0', 'Hora:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ultima_dosis_antiperetico', '0', '0', '0', 'Ultima dosis de antiperetico:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('general', '0', '0', '0', 'General');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('fiebre', '0', '0', '0', 'Fiebre');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('anormalmente_somnoliento', '0', '0', '0', 'Anormalmente somnoliento');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('mal_estado_general', '0', '0', '0', 'Mal estado general');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('perdida_consciencia', '0', '0', '0', 'Perdida de la Consciencia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('inquieto_irritable', '0', '0', '0', 'Inquieto irritable');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('convulsiones', '0', '0', '0', 'Convulsiones');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('letargia', '0', '0', '0', 'Letargia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('cabeza', '0', '0', '0', 'Cabeza');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('dolor_cabeza', '0', '0', '0', 'Dolor de cabeza');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('conjuntivitis', '0', '0', '0', 'Conjuntivitis');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('hemorragia_subconjuntival', '0', '0', '0', 'Hemorragia Subconjuntival');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('dolor_retroocular', '0', '0', '0', 'Dolor Retroocular');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('garganta', '0', '0', '0', 'Garganta');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('dolor_garganta', '0', '0', '0', 'Dolor de garganta');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('eritema', '0', '0', '0', 'Eritema');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('adenopatias_cervicales', '0', '0', '0', 'Adenopatías cervicales');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('exudado', '0', '0', '0', 'Exudado');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('petequias_mucosa', '0', '0', '0', 'Petequias en mucosa');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('respiratorio', '0', '0', '0', 'Respiratorio');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('tos', '0', '0', '0', 'Tos');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('rinorrea', '0', '0', '0', 'Rinorrea');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('congestion_nasal', '0', '0', '0', 'Congestión nasal');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('otalgia', '0', '0', '0', 'Otalgia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('aleteo_nasal', '0', '0', '0', 'Aleteo nasal');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('respiracion_rapida', '0', '0', '0', 'Respiración rápida');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('estridor_reposo', '0', '0', '0', 'Estridor en reposo');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('tirajes_subcostales', '0', '0', '0', 'Tirajes subcostales');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('sibilancia', '0', '0', '0', 'Sibilancia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('crepitos', '0', '0', '0', 'Crépitos');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('roncos', '0', '0', '0', 'Roncos');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('disnea', '0', '0', '0', 'Disnea');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('gastrointestinal', '0', '0', '0', 'Gastrointestinal');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('poco_apetito', '0', '0', '0', 'Poco apetito');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('nauseas', '0', '0', '0', 'Náuseas');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('vomito', '0', '0', '0', 'Vómito');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('vomitos_ultimas_12_hrs', '0', '0', '0', 'Numero de vómitos en las últimas 12 hrs');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('diarrea', '0', '0', '0', 'Diarrea');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('dolor_abdominal', '0', '0', '0', 'Dolor abdominal');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('hepatomegalia', '0', '0', '0', 'Hepatomegalia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('osteomuscular', '0', '0', '0', 'Osteomuscular');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('artralgia', '0', '0', '0', 'Artralgia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('mialgia', '0', '0', '0', 'Mialgia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lumbalgia', '0', '0', '0', 'Lumbalgia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('dolor_cuello', '0', '0', '0', 'Dolor de cuello');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('edema', '0', '0', '0', 'Edema');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('cutaneo', '0', '0', '0', 'Cutáneo');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('rash_localizado', '0', '0', '0', 'Rash localizado');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('rash_generalizado', '0', '0', '0', 'Rash generalizado');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('rash_eritematoso', '0', '0', '0', 'Rash eritematoso');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('rash_macular', '0', '0', '0', 'Rash Macular');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('rash_papular', '0', '0', '0', 'Rash papular');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('piel_moteada', '0', '0', '0', 'Piel moteada');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('rubor_facial', '0', '0', '0', 'Rubor facial');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('cianosis_central', '0', '0', '0', 'Cianosis central');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ictericia', '0', '0', '0', 'Ictericia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('estado_nutricional', '0', '0', '0', 'Estado nutricional');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('imc', '0', '0', '0', 'IMC');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('obeso', '0', '0', '0', 'Obeso');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('sobrepeso', '0', '0', '0', 'Sobrepeso');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('sospecha_problema', '0', '0', '0', 'Sospecha de problema');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('normal', '0', '0', '0', 'Normal');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('bajo_peso', '0', '0', '0', 'Bajo peso');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('bajo_peso_severo', '0', '0', '0', 'Bajo peso severo');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('categoria', '0', '0', '0', 'Categoría');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('cambio_categoria', '0', '0', '0', 'Cambio de categoría:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('completar_categoria_a_b', '0', '0', '0', 'Completar si es categoría A y B');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('manifestaciones_hemorragicas', '0', '0', '0', 'Manifestaciones hemorrágicas');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('prueba_torniquete', '0', '0', '0', 'Prueba del torniquete');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('petequias_10', '0', '0', '0', 'Petequias ≥10');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('petequias_20', '0', '0', '0', 'Petequias ≥20');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('piel_extremidades_frias', '0', '0', '0', 'Piel y extremidades frías');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('palidez_extremidades', '0', '0', '0', 'Palidez en extremidades');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('epistaxis', '0', '0', '0', 'Epistaxis');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('gingivorragia', '0', '0', '0', 'Gingivorragia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('petequias_espontaneas', '0', '0', '0', 'Petequias espontaneas');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('llenado_capilar_segundos', '0', '0', '0', 'Llenado capilar ˃2 segundos');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('cianosis', '0', '0', '0', 'Cianosis');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('hipermenorrea', '0', '0', '0', 'Hipermenorrea');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('hematemesis', '0', '0', '0', 'Hematemesis');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('hemoconcentracion', '0', '0', '0', 'Hemoconcentración');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('preguntas_todos', '0', '0', '0', 'Preguntas para todos los pacientes');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('hospitalizado_ultimo_anio', '0', '0', '0', '¿Ha sido hospitalizado en el último año?');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('especifique_unidad_salud_hosp', '0', '0', '0', 'Si es un SI especifique nombre de la unidad de salud:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('transfusion_sangre_ultimo_anio', '0', '0', '0', '¿Recibió transfusión de sangre en el último año?');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('si_especifique', '0', '0', '0', 'Si es un SI especifique:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('medicamento_momento', '0', '0', '0', '¿Está tomando medicamento en este momento los últimos 6 meses');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('especifique_medicamento', '0', '0', '0', 'Si es SI especifique el medicamento:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('examenes_laboratorio', '0', '0', '0', 'Exámenes del laboratorio');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('bhc', '0', '0', '0', 'BHC');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('serologia_arbovirus', '0', '0', '0', 'Serología Arbovirus');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('gota_gruesa', '0', '0', '0', 'Gota gruesa');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ego', '0', '0', '0', 'EGO');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('egh', '0', '0', '0', 'EGH');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('otros', '0', '0', '0', 'OTROS');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('especifique', '0', '0', '0', 'Especifique:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('tratamiento', '0', '0', '0', 'Tratamiento');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('acetaminofen', '0', '0', '0', 'Acetaminofén');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('amoxicilina', '0', '0', '0', 'Amoxicilina');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('dicloxacilina', '0', '0', '0', 'Dicloxacilina');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('penicilina', '0', '0', '0', 'Penicilina');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('furazolidona', '0', '0', '0', 'Furazolidona');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('metronidazol_tinidazol', '0', '0', '0', 'Metronidazol/Tinidazol');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('albendazol_mebendazol', '0', '0', '0', 'Albendazol/Mebendazol');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('suero_oral', '0', '0', '0', 'Suero oral');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('otro_tratamiento', '0', '0', '0', 'Otro tratamiento:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('planes', '0', '0', '0', 'Planes:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('historia_clinica', '0', '0', '0', 'Historia clínica:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('diagnostico', '0', '0', '0', 'Diagnóstico:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('tel_emergencia', '0', '0', '0', 'Tel. Emergencia:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('proxima_cita', '0', '0', '0', 'Próxima cita:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('medico', '0', '0', '0', 'Médico:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('fecha', '0', '0', '0', 'Fecha:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('enfermeria', '0', '0', '0', 'Enfermería:');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SND_HC', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Si/No/Desconocido Hoja Clinica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SND_HC_01', '1', 'CAT_SND_HC', NULL, '0', 1, '0', 'S');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SND_HC_02', '0', 'CAT_SND_HC', NULL, '0', 2, '0', 'N');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SND_HC_999', '999', 'CAT_SND_HC', NULL, '0', 3, '0', 'D');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CATEGORIA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Categoria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CATEGORIA_A', 'A', 'CAT_CATEGORIA', NULL, '0', 1, '0', 'A');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CATEGORIA_B', 'B', 'CAT_CATEGORIA', NULL, '0', 2, '0', 'B');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CATEGORIA_C', 'C', 'CAT_CATEGORIA', NULL, '0', 3, '0', 'C');

INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('1', '1', '0', '2021-11-10 12:02:07', 'admin', 'Nejapa - Martha Aguilar');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('2', '1', '0', '2021-11-10 12:02:07', 'admin', 'Nejapa - Germán Pomares');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('3', '1', '0', '2021-11-10 12:02:07', 'admin', 'Nejapa - Martín Lutero');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('4', '1', '0', '2021-11-10 12:02:07', 'admin', 'Nejapa - Maria Mora');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('5', '1', '0', '2021-11-10 12:02:07', 'admin', 'Nejapa - Divino Pastor');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('6', '1', '0', '2021-11-10 12:02:07', 'admin', 'Nejapa - Vladimir Hernández');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('7', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - Berthílda Olegario');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('8', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - Bloque K');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('9', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - Tangará (Buena Vista)');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('10', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - Alexis Arguello # 2');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('11', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - Villa Nueva');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('12', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - Arges Sequeira');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('13', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - Raúl Cerna');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('14', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - German Pomares Ticomo');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('15', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - Solidaridad');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('16', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - San José de las Colinas');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('17', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - Camilo Ortega # 1');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('18', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - Villa Esperanza');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('19', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - William Galeano');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('20', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - El Laurel');
INSERT INTO `barrios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('21', '1', '0', '2021-11-10 12:02:07', 'admin', 'CO - La Zacatera');

INSERT INTO `estudios` (`CODIGO`, `ESTADO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE`) VALUES ('1', '1', '0', '2021-11-10 12:36:25', 'admin', 'A2CARES');

/*hoja clinica*/
INSERT INTO `a2cares`.`mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('participant', '0', '0', '0', 'Participante');
INSERT INTO `a2cares`.`mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('fecha_inicio_consulta', '0', '0', '0', 'Fecha Inicio Consulta');
INSERT INTO `a2cares`.`mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('fecha_fin_consulta', '0', '0', '0', 'Fecha Fin Consulta');
INSERT INTO `a2cares`.`mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('fecha_holder', '0', '0', '0', 'dd/MM/yyyy');
INSERT INTO `a2cares`.`mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('code', '0', '0', '0', 'Código');

/*catalogos*/
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('messageKey', '0', '0', '0', 'Código mensaje');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('spanish', '0', '0', '0', 'Español');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('pasive', '0', '0', '0', 'De baja');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('catKey', '0', '0', '0', 'Valor de la respuesta');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('order', '0', '0', '0', 'Orden');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('disableRecord', '0', '0', '0', 'Deshabilitar este registro en la base de datos');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('enableRecord', '0', '0', '0', 'Habilitar este registro en la base de datos');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ident', '0', '0', '0', 'Identificador');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('english', '0', '0', '0', 'Inglés');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('editEntidadToolTip', '0', '0', '0', 'Editar este registro');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('catalogs', '0', '0', '0', 'Catálogos');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('options', '0', '0', '0', 'Opciones');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('disabled', '0', '0', '0', 'Deshabilitado');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('catRoot', '0', '0', '0', 'Catálogo');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('saveRecord', '0', '0', '0', 'Guardar registro en la base de datos');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('cancelRecord', '0', '0', '0', 'Cancelar la edición del registro e ir a la lista');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('returnRecord', '0', '0', '0', 'Ir a la lista');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('addEntityToolTip', '0', '0', '0', 'Click para agregar un registro');

/*Personal*/
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('personal', '0', '0', '0', 'Personal');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('charge', '0', '0', '0', 'Cargo');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('name', '0', '0', '0', 'Nombre');

INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ROLE_DIGI', '0', '0', '0', 'Digitador');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ROLE_ROOT', '0', '0', '0', 'Super usuario');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ROLE_SUPER', '0', '0', '0', 'Supervisor');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ROLE_LABO', '0', '0', '0', 'Laboratorio');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CARGO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Cargo personal estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CARGO_1', '1', 'CAT_CARGO', NULL, '0', 1, '0', 'Médico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_CARGO_2', '2', 'CAT_CARGO', NULL, '0', 2, '0', 'Enfermería');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_VA_AGUA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo frecuencia se va el agua');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_VA_AGUA_1', '1', 'CAT_FREC_VA_AGUA', NULL, '0', 1, '0', 'Todos los días');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_VA_AGUA_2', '2', 'CAT_FREC_VA_AGUA', NULL, '0', 2, '0', 'Día de por medio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_VA_AGUA_3', '3', 'CAT_FREC_VA_AGUA', NULL, '0', 3, '0', 'Cada 2 días');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_VA_AGUA_4', '4', 'CAT_FREC_VA_AGUA', NULL, '0', 4, '0', 'Cada 3 días');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FREC_VA_AGUA_998', '998', 'CAT_FREC_VA_AGUA', NULL, '0', 5, '0', 'Otros');

/* LABEL SCANCARTA */
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'more', 'mas','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'back.list', 'Volver al Listado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.Back', 'Volver','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'ecasa.Pediatric', 'Casas Pediátrica','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'ecasa.Family', 'Casas Familia','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'cat.version', 'Catálogo Versión','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'cat.part', 'Catálogo Parte','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'cat.extension', 'Catálogo Extensión','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'comparison', 'Comparación','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'letters', 'Cartas','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'consent', 'Consentimiento','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'processes', 'Procesos','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'view', 'Ver','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'remove', 'Retirar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'retirement', 'Retiro','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'change.address', 'Cambios de Domicilio','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'address', 'Domicilio','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'Latitude', 'Latitud','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'Length', 'Longitud','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'Information','Información','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.land','Terreno','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('Reception','Recepción','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('Letter.Parts','Partes Cartas','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.resource','Recurso','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('type.assent','Tipo Asentimiento:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.assent','Asentimiento:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.project','Proyecto:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('Guardian / Witness','Tutor/Testigo:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('first.name','1er. Nombre','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('second.name','2do. Nombre','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('first.surname','1er. Apellido','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('second.surname','2do. Apellido','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.Accept.future.contact','Acepta Contacto Futuro?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.witness.present','Testigo presente?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.witness','Testigo:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.required','Requerido','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.years','años:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.mounths','meses:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.days','dias:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.update','Actualizar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.error','Error!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.invalidate','Anular','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.close','Cerrar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.ok','Acepta?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('select.options','Selecciona una opción','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('Extension','Extensión','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('WithOut.Extension','Sin Extensión','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('family.relationship','Relación Familiar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.temporary','temporal','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.To.assign','Asignar','0','0',0);

/**************************  CATALOGO PROYECTO SCANCARTA A2CARES */

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SCAN_PROYECTO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo para el tipo de Proyecto ScanCarta');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SCAN_PROYECTO_A2CARES', '1', 'CAT_SCAN_PROYECTO', NULL, '0', 1, '0', 'A2CARES');

/**RECEPCION SEROLOGIA*/
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.Observation', 'Observación','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.new.entry', 'Nuevo Ingreso','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.Hour', 'Hora','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.new', 'Nuevo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.from', 'Dessde','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.until', 'Hasta','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.send', 'Envío','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.dispatch', 'Enviar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'sample', 'Muestra','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.invalid','Anulada?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('why.invalid','Pq Anulada?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('rason.invalid','Anulada por:','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.house','Casa','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ('lbl.envoy','enviada','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.Accepted', 'Aceptadas','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.Parts', 'Partes','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.tutor', 'Tutor','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.version', 'Versión','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.participant', 'Participante','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.study', 'Estudio','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.names.surnames', 'Nombres y Apellidos','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.father', 'Padre','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.mother', 'Madre','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.user', 'Usuario','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.serologia', 'Serología','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.letters.comparison.1', 'Participantes registrados sin carta digitada','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.letters.comparison.2', 'Diferencias en partes de la carta','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.letters.comparison.3', 'Diferencias de Tutor y Relación Familiar','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.samples.comparison.1', 'Tubos Rojos del supervisor que no tienen las estaciones','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.samples.comparison.2', 'Tubos Rojos del supervisor que no tiene el laboratorio','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.samples.comparison.3', 'Tubos Rojos de las estaciones que no tiene el supervisor','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.samples.comparison.4', 'Tubos Rojos de las estaciones que no tiene el laboratorio','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.samples.comparison.5', 'Tubos Rojos de laboratorio que no tiene el supervisor','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.samples.comparison.6', 'Tubos Rojos de laboratorio que no tienen las estaciones','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.samples', 'Muestras','0','0',0);


INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('SCANCARTA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Si/No/No Aplica ScanCarta');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SCANCARTA_NO', '0','SCANCARTA', NULL, '0', 0, '0', 'NO');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SCANCARTA_SI', '1','SCANCARTA', NULL, '0', 1, '0', 'SI');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SCANCARTA_NA', '3','SCANCARTA', NULL, '0', 3, '0', 'NA');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `Pasive`, `es`) VALUES ('CAT_TIPO_ASENT', NULL, NULL, NULL, '1', 0, '0', 'Catalogo para el tipo de asentimiento que da el participante');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `Pasive`, `es`) VALUES ('CAT_TIPO_ASENT_01', '1', 'CAT_TIPO_ASENT', NULL, '0', 1, '0', 'Verbal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `Pasive`, `es`) VALUES ('CAT_TIPO_ASENT_02', '2', 'CAT_TIPO_ASENT', NULL, '0', 2, '0', 'Escrito');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `Pasive`, `es`) VALUES ('CAT_TIPO_ASENT_03', '3', 'CAT_TIPO_ASENT', NULL, '0', 3, '0', 'No da asentimiento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `Pasive`, `es`) VALUES ('CAT_TIPO_ASENT_04', '4', 'CAT_TIPO_ASENT', NULL, '0', 4, '0', 'NA');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_ENVIO_SEROLOGIA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Numero de Envio Serologia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_ENVIO_SEROLOGIA_1', '1', 'CAT_ENVIO_SEROLOGIA', NULL, '0', 1, '0', '1');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_ENVIO_SEROLOGIA_2', '2', 'CAT_ENVIO_SEROLOGIA', NULL, '0', 2, '0', '2');

INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.age', 'Edad','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.version.letter', 'Versión de la Carta','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.Position', 'Cargos','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.Person', 'Personal','0','0',0);


INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_DATA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo razones no completa información');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_01', '1', 'CAT_RAZON_NO_DATA', NULL, '0', 1, '0', 'Acompañante desconoce información');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_02', '2', 'CAT_RAZON_NO_DATA', NULL, '0', 2, '0', 'No se pudo tomar todas las muestras');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_03', '3', 'CAT_RAZON_NO_DATA', NULL, '0', 3, '0', 'Abandono de Muestreo Anual');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_04', '4', 'CAT_RAZON_NO_DATA', NULL, '0', 4, '0', 'No desea dar información');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_05', '5', 'CAT_RAZON_NO_DATA', NULL, '0', 5, '0', 'Falta de tiempo por parte del acompañante');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_06', '6', 'CAT_RAZON_NO_DATA', NULL, '0', 6, '0', 'Solo revisaba información');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_07', '7', 'CAT_RAZON_NO_DATA', NULL, '0', 7, '0', 'Solo se realiza peso y talla');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_08', '8', 'CAT_RAZON_NO_DATA', NULL, '0', 8, '0', 'Peso y talla en otro equipo móvil');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_09', '9', 'CAT_RAZON_NO_DATA', NULL, '0', 9, '0', 'No comprendo las preguntas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_NO_DATA_998', '998', 'CAT_RAZON_NO_DATA', NULL, '0', 10, '0', 'Otra razón');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_DESCARTE', NULL, NULL, NULL, '1', 0, '0', 'Catalogo descarta punto gps candidato');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_DESCARTE_01', '1', 'CAT_RAZON_DESCARTE', NULL, '0', 1, '0', 'Casa Cerrada');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_RAZON_DESCARTE_998', '998', 'CAT_RAZON_DESCARTE', NULL, '0', 2, '0', 'Otra razón');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_CONS_HC', NULL, NULL, NULL, '1', 0, '0', 'Catalogo lugar de consulta hoja clinica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_CONS_HC_01', '1', 'CAT_LUGAR_CONS_HC', NULL, '0', 1, '0', 'Puesto de Salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_CONS_HC_02', '2', 'CAT_LUGAR_CONS_HC', NULL, '0', 2, '0', 'Terreno');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_CONSULTA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de consulta');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_CONSULTA_01', '1', 'CAT_TIPO_CONSULTA', NULL, '0', 1, '0', 'Inicial');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_CONSULTA_02', '2', 'CAT_TIPO_CONSULTA', NULL, '0', 2, '0', 'Seguimiento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_CONSULTA_03', '3', 'CAT_TIPO_CONSULTA', NULL, '0', 3, '0', 'Convaleciente');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_RECEP_MX', NULL, NULL, NULL, '1', 0, '0', 'Catalogo lugar de recepcion muestras');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_RECEP_MX_01', '1', 'CAT_LUGAR_RECEP_MX', NULL, '0', 1, '0', 'Puesto de Salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_LUGAR_RECEP_MXC_02', '2', 'CAT_LUGAR_RECEP_MX', NULL, '0', 2, '0', 'Terreno');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SITIOS_ENVIO_SEROLOGIA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Sitios de Envios Serologia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SITIOS_ENVIO_SEROLOGIA_1', '1', 'CAT_SITIOS_ENVIO_SEROLOGIA', NULL, '0', 1, '0', 'Puesto de Salud Nejapa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SITIOS_ENVIO_SEROLOGIA_2', '2', 'CAT_SITIOS_ENVIO_SEROLOGIA', NULL, '0', 2, '0', 'Puesto de Salud Camilo Ortega');


INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.lugar', 'Lugar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.volumen', 'Volumen','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.fecha.toma', 'Fecha Toma','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.fecha.recepcion', 'Fecha Recepción','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.supervisor', 'Supervisor','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.laboratorista', 'Laboratorista','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.encuestador', 'Encuestador','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.pinchazos', 'Pinchazos','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.neighborhood', 'Barrio','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.latitude', 'Latitud','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.longitude', 'Longitud','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.discarded', 'Descartado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.discard.reason', 'Razón descarte','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.discard.date', 'Fecha descarte','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.discard.user', 'Usuario descarte','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.gps', 'Puntos Candidatos','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.list', 'Listado','0','0',0);


INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_OBSERV_MX', NULL, NULL, NULL, '1', 0, '0', 'Catalogo observaciones toma muestra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_OBSERV_MX_01', '1', 'CAT_OBSERV_MX', NULL, '0', 1, '0', 'Muestra difícil');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_OBSERV_MX_02', '2', 'CAT_OBSERV_MX', NULL, '0', 2, '0', 'Ninguna');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_OBSERV_MX_998', '998', 'CAT_OBSERV_MX', NULL, '0', 3, '0', 'Otra observación');

/*RETIRO*/
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ( 'date_of_death', 'Fecha Fallecimiento','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ( 'Details', 'Detalles','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ( 'house', 'Casa','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('others','Otros','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('family_relationship','Relación Familiar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('close','Cerrado','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('supervised_by','Supervisado Por','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('documented_by','Documentado Por','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('communicated_by','Comunicado Por','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('returned_card','Devolvió Carnet?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('relationship','Parentesco','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('corrections','Correcciones','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('lbl.correction.tutor','Corregir datos de tutor','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('msg.correction.tutor.success','Tutor del participante %s actualizado exitosamente!!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('msg.correction.tutor.error','Error al guardar correcciones!!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('lbl.correction.tutor.actual','Información actuales del tutor','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('lbl.correction.tutor.new','Información nueva del tutor','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('lbl.correction.observacion','Motivo de correción','0','0',0);


INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('receives_document','Personal del Estudio quien recibe el retiro','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('fill_document','Personal quien llena el Documento','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('passed_away','Fallecido','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('person_communicates','Padre o Tutor con quien había comunicación sobre el retiro','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `Pasive`, `isCat`, `orden`) VALUES ('cause_withdrawal','Causas de la No Participación','0','0',0);

/*MODULO MX ENFERMOS*/
INSERT INTO `a2cares`.`roles` (`ROL`) VALUES ('ROLE_MED');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ROLE_MED', '0', '0', '0', 'Médico');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FASE_MX', NULL, NULL, NULL, '1', 0, '0', 'Catalogo fases de muestras');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FASE_MX_01', '1', 'CAT_FASE_MX', NULL, '0', 1, '0', 'Aguda');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_FASE_MX_02', '2', 'CAT_FASE_MX', NULL, '0', 2, '0', 'Convaleciente');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_ORDEN', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de ordenes');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_TIPO_ORDEN_01', 'R', 'CAT_TIPO_ORDEN', NULL, '0', 1, '0', 'Serología');

INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.description', 'Descripción','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lbl.shipping.start.date', '0', '0', '0', 'Fecha Inicio Envío');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lbl.shipping.end.date', '0', '0', '0', 'Fecha Fin Envío');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lbl.pdf', '0', '0', '0', 'PDF');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lbl.excel', '0', '0', '0', 'Excel');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lbl.report.shipment.specimens.patients', '0', '0', '0', 'Reporte de envio de muestras de enfermos');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lbl.shipment.report', '0', '0', '0', 'Reporte Envío');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lbl.mx.sick', '0', '0', '0', 'Mx Enfermo');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lbl.serologia.enfermo', '0', '0', '0', 'Serología Enfermo');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lbl.sample.type.phase', '0', '0', '0', 'Tipo Muestra');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lbl.fis', '0', '0', '0', 'FIS');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lbl.fif', '0', '0', '0', 'FIF');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lbl.form', '0', '0', '0', 'Formulario');

INSERT INTO `a2cares`.`mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('code.mx', '0', '0', '0', 'Código Muestra');
INSERT INTO `a2cares`.`mensajes` (`messageKey`, `isCat`, `orden`, `pasive`, `es`) VALUES ('lbl.print', '0', '0', '0', 'Imprimir');
UPDATE `a2cares`.`mensajes` SET `es`='Enviada' WHERE  `messageKey`='lbl.envoy';

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('CAT_SITIOS_ENVIO_SEROLOGIA_3', '3', 'CAT_SITIOS_ENVIO_SEROLOGIA', NULL, '0', 3, '0', 'Centro de Salud Edgard Lang');

/*ENTOMOLOGIA**/
INSERT INTO roles (rol) VALUES ('ROLE_ENTO');
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'ROLE_ENTO', 'Entomologia - Equipo Harold','0','0',0);


INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.ento', 'Entomología','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.ento.start.date', 'Fecha Inicio Cuestionario','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `pasive`, `isCat`, `orden`) VALUES ( 'lbl.ento.end.date', 'Fecha Fin Cuestionario','0','0',0);

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P16', NULL, NULL, NULL, '1', 0, '0', 'Catalogo entomologia Quién está contestando éste cuestionario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P16_01', '1', 'ENTO_CAT_P16', NULL, '0', 1, '0', 'Persona jefa de hogar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P16_02', '2', 'ENTO_CAT_P16', NULL, '0', 2, '0', 'Tutor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P16_03', '3', 'ENTO_CAT_P16', NULL, '0', 3, '0', 'Persona Jefa de hogar/Tutor/Participante');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P16_998', '998', 'ENTO_CAT_P16', NULL, '0', 4, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P18', NULL, NULL, NULL, '1', 0, '0', 'Catalogo entomologia Cuál es su último año aprobado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P18_01', '1', 'ENTO_CAT_P18', NULL, '0', 1, '0', 'Ninguno/no estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P18_02', '2', 'ENTO_CAT_P18', NULL, '0', 2, '0', 'Primaria incompleta');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P18_03', '3', 'ENTO_CAT_P18', NULL, '0', 3, '0', 'Primaria Completa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P18_04', '4', 'ENTO_CAT_P18', NULL, '0', 4, '0', 'Secundaria incompleta');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P18_05', '5', 'ENTO_CAT_P18', NULL, '0', 5, '0', 'Secundaria completa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P18_06', '6', 'ENTO_CAT_P18', NULL, '0', 6, '0', 'Universidad');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P19', NULL, NULL, NULL, '1', 0, '0', 'Catalogo entomologia Cuánto tiempo tienen de vivir en este barrio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P19_01', '1', 'ENTO_CAT_P19', NULL, '0', 1, '0', '5 años o menos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P19_02', '2', 'ENTO_CAT_P19', NULL, '0', 2, '0', 'De 6 a 10 años');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P19_03', '3', 'ENTO_CAT_P19', NULL, '0', 3, '0', 'De 11 a 20 años');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P19_04', '4', 'ENTO_CAT_P19', NULL, '0', 4, '0', 'De 21 a 30 años');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P19_05', '5', 'ENTO_CAT_P19', NULL, '0', 5, '0', 'Más de 30 años');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P27', NULL, NULL, NULL, '1', 0, '0', 'Catalogo entomologia quien les ha visitado para enseñarles como buscar y eliminar las larvas o clavitos de los zancudos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P27_01', '1', 'ENTO_CAT_P27', NULL, '0', 1, '0', 'MINSA');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P27_02', '2', 'ENTO_CAT_P27', NULL, '0', 2, '0', 'Trabajadores de la salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P27_03', '3', 'ENTO_CAT_P27', NULL, '0', 3, '0', 'Brigadista de Salud del barrio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P27_04', '4', 'ENTO_CAT_P27', NULL, '0', 4, '0', 'Líderes del barrio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P27_998', '998', 'ENTO_CAT_P27', NULL, '0', 5, '0', 'Otro (Especificar)');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P30', NULL, NULL, NULL, '1', 0, '0', 'Catalogo entomologia Cada cuánto tiempo buscan y eliminan criaderos de zancudos aquí en su casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P30_01', '1', 'ENTO_CAT_P30', NULL, '0', 1, '0', 'Entre 1-7 días');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P30_02', '2', 'ENTO_CAT_P30', NULL, '0', 2, '0', '8 días o más');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P32', NULL, NULL, NULL, '1', 0, '0', 'Catalogo entomologia Qué hace falta en esta casa para evitar los criaderos de zancudos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P32_01', '1', 'ENTO_CAT_P32', NULL, '0', 1, '0', 'Nada');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P32_02', '2', 'ENTO_CAT_P32', NULL, '0', 2, '0', 'Fumigación');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P32_03', '3', 'ENTO_CAT_P32', NULL, '0', 3, '0', 'Abate');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P32_04', '4', 'ENTO_CAT_P32', NULL, '0', 4, '0', 'BTI');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P32_05', '5', 'ENTO_CAT_P32', NULL, '0', 5, '0', 'Cipermetrina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P32_06', '6', 'ENTO_CAT_P32', NULL, '0', 6, '0', 'Tiempo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P32_07', '7', 'ENTO_CAT_P32', NULL, '0', 7, '0', 'Limpieza/Aseo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P32_08', '8', 'ENTO_CAT_P32', NULL, '0', 8, '0', 'No sé');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P32_998', '998', 'ENTO_CAT_P32', NULL, '0', 9, '0', 'Otro (Escribir)');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P34', NULL, NULL, NULL, '1', 0, '0', 'Catalogo entomologia Qué productos compraron');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P34_01', '1', 'ENTO_CAT_P34', NULL, '0', 1, '0', 'Plagatox');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P34_02', '2', 'ENTO_CAT_P34', NULL, '0', 2, '0', 'Baygón / Raid / aerosol');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P34_03', '3', 'ENTO_CAT_P34', NULL, '0', 3, '0', 'Cipermetrina / Veneno');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P34_04', '4', 'ENTO_CAT_P34', NULL, '0', 4, '0', 'Gas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P34_05', '5', 'ENTO_CAT_P34', NULL, '0', 5, '0', 'Cloro');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P34_06', '6', 'ENTO_CAT_P34', NULL, '0', 6, '0', 'Repelentes de mosquito para piel');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P34_07', '7', 'ENTO_CAT_P34', NULL, '0', 7, '0', 'No sabe');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P34_998', '998', 'ENTO_CAT_P34', NULL, '0', 8, '0', 'Otro (Escribir)');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P36', NULL, NULL, NULL, '1', 0, '0', 'Catalogo entomologia Cuándo fue la última vez que el MINSA visitó su casa para aplicar BTI en sus recipientes con agua y Cuándo fue la última vez que el MINSA fumigó su casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P36_01', '1', 'ENTO_CAT_P36', NULL, '0', 1, '0', 'Hoy');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P36_02', '2', 'ENTO_CAT_P36', NULL, '0', 2, '0', 'Ayer');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P36_03', '3', 'ENTO_CAT_P36', NULL, '0', 3, '0', 'Entre 3 - 7 días');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P36_04', '4', 'ENTO_CAT_P36', NULL, '0', 4, '0', 'Entre 8 - 15 días');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P36_05', '5', 'ENTO_CAT_P36', NULL, '0', 5, '0', 'Entre 16 - 30');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P36_06', '6', 'ENTO_CAT_P36', NULL, '0', 6, '0', 'Más de 30 días');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P38', NULL, NULL, NULL, '1', 0, '0', 'Catalogo entomologia Qué tanto riesgo hay en su casa de enfermar por el virus del dengue');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P38_01', '1', 'ENTO_CAT_P38', NULL, '0', 1, '0', 'No hay');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P38_02', '2', 'ENTO_CAT_P38', NULL, '0', 2, '0', 'Poco');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P38_03', '3', 'ENTO_CAT_P38', NULL, '0', 3, '0', 'Moderado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P38_04', '4', 'ENTO_CAT_P38', NULL, '0', 4, '0', 'Alto');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P38_05', '5', 'ENTO_CAT_P38', NULL, '0', 5, '0', 'Muy alto');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P40', NULL, NULL, NULL, '1', 0, '0', 'Catalogo entomologia Cada cuánto se les va el agua');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P40_01', '1', 'ENTO_CAT_P40', NULL, '0', 1, '0', 'No se va');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P40_02', '2', 'ENTO_CAT_P40', NULL, '0', 2, '0', 'Todos los días / Diario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P40_03', '3', 'ENTO_CAT_P40', NULL, '0', 3, '0', 'Dia de por medio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P40_998', '998', 'ENTO_CAT_P40', NULL, '0', 4, '0', 'Otro (Describir)');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P42', NULL, NULL, NULL, '1', 0, '0', 'Catalogo entomologia Qué hacen con la basura del hogar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P42_01', '1', 'ENTO_CAT_P42', NULL, '0', 1, '0', 'Se la lleva el camión recolector');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P42_02', '2', 'ENTO_CAT_P42', NULL, '0', 2, '0', 'La queman');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P42_03', '3', 'ENTO_CAT_P42', NULL, '0', 3, '0', 'La tiran al predio baldío');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P42_04', '4', 'ENTO_CAT_P42', NULL, '0', 4, '0', 'La tiran al cauce');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P42_05', '5', 'ENTO_CAT_P42', NULL, '0', 5, '0', 'La entierran');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P42_998', '998', 'ENTO_CAT_P42', NULL, '0', 6, '0', 'Otro (Describir)');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P45', NULL, NULL, NULL, '1', 0, '0', 'Catalogo entomologia Qué acciones realizaron');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P45_01', '1', 'ENTO_CAT_P45', NULL, '0', 1, '0', 'Plan Calache');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P45_02', '2', 'ENTO_CAT_P45', NULL, '0', 2, '0', 'Jornada limpieza');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P45_998', '998', 'ENTO_CAT_P45', NULL, '0', 3, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P05', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo ingreso de códigos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P05_01', '1', 'ENTO_CAT_P05', NULL, '0', 1, '0', 'Manual');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P05_02', '2', 'ENTO_CAT_P05', NULL, '0', 2, '0', 'Escanear código QR');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P06', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo ingreso de códigos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P06_01', '1', 'ENTO_CAT_P06', NULL, '0', 1, '0', 'Seguimiento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P06_02', '2', 'ENTO_CAT_P06', NULL, '0', 2, '0', 'Reposición');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P10_P15', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo ingreso de códigos intra y peri');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P10_P15_01', '1', 'ENTO_CAT_P10_P15', NULL, '0', 1, '0', 'Manual');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P10_P15_02', '2', 'ENTO_CAT_P10_P15', NULL, '0', 2, '0', 'Escanear código QR');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `pasive`, `es`) VALUES ('ENTO_CAT_P10_P15_03', '3', 'ENTO_CAT_P10_P15', NULL, '0', 3, '0', 'No hay');