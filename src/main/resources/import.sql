INSERT INTO `roles` (`ROL`) VALUES ('ROLE_ROOT');

INSERT INTO `usuarios_sistema` (`NOMBRE_USUARIO`, `CUENTA_SINEXPIRAR`, `CUENTA_SINBLOQUEAR`, `DESCRIPCION`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `CREDENCIAL_SINEXPIRAR`, `CORREO_ELECTRONICO`, `HABILITADO`, `FECHA_ULTACC`, `FECHA_ULTMODCRED`, `FECHA_ULTMOD`, `USUARIO_ULTMOD`, `CONTRASENA`) VALUES ('admin', '1', '1', 'Administrador', '2021-01-01 12:00:00', 'admin', '1', 'admincndr@icsnicaragua.org', '1', '2021-01-01 12:00:00', NULL, '2021-01-01 12:00:00', 'admin', '4f4e617568a86abdce12ac9eab87ea40c0d9824a6db6e21fd6d4c54a0b9e5d58ff25e2093f8caee6');
INSERT INTO `usuarios_roles` (`ROL`, `NOMBRE_USUARIO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `ESTADO`) VALUES ('ROLE_ROOT', 'admin', '0', '2021-01-01 12:00:00', 'admin', '1');


/*Plantilla*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'heading', 'Aplicación Web','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'title', 'Estudio A2CARES','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'footer', 'Instituto de Ciencias Sostenibles','0','0',0);

/*login page*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login', 'Ingresar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.accountExpired', 'Cuenta de usuario ha expirado!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.accountLocked', 'Cuenta de usuario esta bloqueada!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.accountNotLocked', 'Cuenta de usuario esta desbloqueada!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.badCredentials', 'Nombre de usuario o contraseña incorrectos!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.credentialsExpired', 'Credenciales de usuario han expirado!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.maxSessionsOut', 'Tiene una sesion activa! No puede crear otra!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.message', 'Por favor ingresar su nombre de usuario y contraseña','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.username', 'Nombre de usuario','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.password', 'Contraseña','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.forgot.password', 'Olvido contraseña?','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.userEnabled', 'Usuario esta activo!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.userDisabled', 'Usuario esta inactivo!','0','0',0);


/*Menu*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'home', 'Inicio','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dashboard', 'Panel de control','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'admin', 'Administracion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'users', 'Usuarios','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'logout', 'Salir','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'reports', 'Reportes','0','0',0);

/*Usuarios*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'username', 'Usuario','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'userdesc', 'Descripcion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'useremail', 'Correo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'userlock', 'Bloqueado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'usercred', 'Contrasena vencida','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'userexp', 'Cuenta vencida','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'userroles', 'Roles','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'user.updated', 'Usuario actualizado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'user.created', 'Usuario creado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'profile', 'Perfil','0','0',0);
/*Accesos*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'access', 'Accesos de usuario','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lastAccess', 'Ultimo acceso','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dateCredentials', 'Ultimo cambio de contrasena','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'session', 'Id de sesion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ipaddress', 'Direccion IP','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'logindate', 'Fecha ingreso','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'logoutdate', 'Fecha salida','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'logouturl', 'URL salida','0','0',0);

/*Audit trail*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'audittrail', 'Bitacora de cambios','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityClass', 'Clase','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityName', 'Nombre','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityProperty', 'Propiedad','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityPropertyOldValue', 'Valor anterior','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityPropertyNewValue', 'Nuevo valor','0','0',0);

/*Roles*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_ADMIN', 'Administrador','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_QC', 'Usuario web','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_MOVIL', 'Usuario movil','0','0',0);

/*Metadata*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'createdBy', 'Creado por','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dateCreated', 'Fecha creacion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'modifiedBy', 'Modificado por','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dateModified', 'Fecha de modificacion','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'active', 'Activo','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'addedBy', 'Agregado por','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dateAdded', 'Fecha','0','0',0);

/*Acciones, todas las paginas*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'actions', 'Acciones','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'add', 'Agregar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'save', 'Guardar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'edit', 'Editar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'back', 'Regresar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'cancel', 'Cancelar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'disable', 'Deshabilitar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'enable', 'Habilitar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'end', 'Finalizar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'unlock', 'Desbloquear','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lock', 'Bloquear','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'export', 'Exportar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ok', 'Aceptar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'search', 'Buscar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'confirm', 'Confirmar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'please.enter', 'Favor ingresar','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ('delete', 'Eliminar', '0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ('generate', 'Generar', '0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ('parameter', 'Parametro', '0','0',0);

/*Mensajes generales, todas las paginas*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'enabled', 'Habilitado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'locked', 'Bloqueado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'notenabled', 'Deshabilitado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'notlocked', 'Desbloqueado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'process.errors', 'Han ocurrido errores en el proceso!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'process.success', 'El proceso se ha completado exitosamente!','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'noResults', 'No hay registros!','0','0',0);

/*Cambio contrasenia*/
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'changepass', 'Cambiar contraseña..','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'credentials.expired', 'Su contrasena ha caducado','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'pass.updated', 'Su contrasena ha sido actualizada','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'password.repeat', 'Repita la contrasena','0','0',0);
INSERT INTO `mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'Pattern.password.format', 'Al menos 8 caracteres combinando mayusculas, minusculas, numeros y caracteres especiales','0','0',0);

/*Catalogos*/

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SINO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Si/No');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SINO_SI', '1', 'CAT_SINO', NULL, '0', 1, '0', 'Si');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SINO_NO', '0', 'CAT_SINO', NULL, '0', 2, '0', 'No');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SEXO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Sexo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SEXO_M', 'M', 'CAT_SEXO', NULL, '0', 1, '0', 'Masculino');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SEXO_F', 'F', 'CAT_SEXO', NULL, '0', 2, '0', 'Femenino');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_TAMIZAJE', NULL, NULL, NULL, '1', 0, '0', 'Catalogo no acepta tamizaje persona');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_TAMIZAJE_01', '1', 'CAT_NO_TAMIZAJE', NULL, '0', 1, '0', 'No desea participar en el estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_TAMIZAJE_02', '2', 'CAT_NO_TAMIZAJE', NULL, '0', 2, '0', 'Cambiará de Domicilio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_TAMIZAJE_03', '3', 'CAT_NO_TAMIZAJE', NULL, '0', 3, '0', 'Tiene seguro médico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_TAMIZAJE_04', '4', 'CAT_NO_TAMIZAJE', NULL, '0', 4, '0', 'No considera que tiene beneficios en el estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_TAMIZAJE_05', '5', 'CAT_NO_TAMIZAJE', NULL, '0', 5, '0', 'No desean que sus niños sean muestreados');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_TAMIZAJE_998', '998', 'CAT_NO_TAMIZAJE', NULL, '0', 6, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_PARTICIPA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo no acepta estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_PARTICIPA_01', '1', 'CAT_NO_PARTICIPA', NULL, '0', 1, '0', 'No considera que tiene beneficios en el estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_PARTICIPA_02', '2', 'CAT_NO_PARTICIPA', NULL, '0', 2, '0', 'No desea continuar en el estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_PARTICIPA_03', '3', 'CAT_NO_PARTICIPA', NULL, '0', 3, '0', 'No desean que sus niños sean muestreados');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_PARTICIPA_998', '998', 'CAT_NO_PARTICIPA', NULL, '0', 4, '0', 'Otros motivos');


INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RF_TUTOR', NULL, NULL, NULL, '1', 0, '0', 'Catalogo relacion familiar tutor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RF_TUTOR_01', '1', 'CAT_RF_TUTOR', NULL, '0', 1, '0', 'Madre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RF_TUTOR_02', '2', 'CAT_RF_TUTOR', NULL, '0', 2, '0', 'Padre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RF_TUTOR_03', '3', 'CAT_RF_TUTOR', NULL, '0', 3, '0', 'Abuelo (a)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RF_TUTOR_04', '4', 'CAT_RF_TUTOR', NULL, '0', 4, '0', 'Tio (a)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RF_TUTOR_05', '5', 'CAT_RF_TUTOR', NULL, '0', 5, '0', 'Hermano (a)');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RF_TUTOR_06', '6', 'CAT_RF_TUTOR', NULL, '0', 6, '0', 'Otra relación familiar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RF_TUTOR_07', '7', 'CAT_RF_TUTOR', NULL, '0', 7, '0', 'Sin Relación Familiar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RF_TUTOR_08', '8', 'CAT_RF_TUTOR', NULL, '0', 8, '0', 'Participante');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_VIVIENDA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo de vivienda');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_VIVIENDA_01', '1', 'CAT_TIPO_VIVIENDA', NULL, '0', 1, '0', 'Propia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_VIVIENDA_02', '2', 'CAT_TIPO_VIVIENDA', NULL, '0', 2, '0', 'Alquilada');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TMP_RES', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tiempo de residencia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TMP_RES_01', '1', 'CAT_TMP_RES', NULL, '0', 1, '0', 'Menos de un año');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TMP_RES_02', '2', 'CAT_TMP_RES', NULL, '0', 2, '0', 'Un año ó Más');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERIFICA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo para verificar procedimientos con el tutor o participante');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERIFICA_01', '1', 'CAT_VERIFICA', NULL, '0', 1, '0', 'Una copia del consentimiento ha sido entregada al padre/tutor del part.?');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERIFICA_02', '2', 'CAT_VERIFICA', NULL, '0', 2, '0', 'Firmó carta de Consentimiento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERIFICA_03', '3', 'CAT_VERIFICA', NULL, '0', 3, '0', 'Fechó carta de Consentimiento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERIFICA_04', '4', 'CAT_VERIFICA', NULL, '0', 4, '0', 'Plasmó su huella digital');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERIFICA_05', '5', 'CAT_VERIFICA', NULL, '0', 5, '0', 'Testigo firmó y fechó carta de Consentimiento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VERIFICA_06', '6', 'CAT_VERIFICA', NULL, '0', 6, '0', 'Entendió los procedimientos del estudio');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_TEL', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de telefono');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_TEL_01', '1', 'CAT_TIPO_TEL', NULL, '0', 1, '0', 'Celular');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_TEL_02', '2', 'CAT_TIPO_TEL', NULL, '0', 2, '0', 'Convencional');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_OPER_TEL', NULL, NULL, NULL, '1', 0, '0', 'Catalogo operadoras de telefono');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_OPER_TEL_01', '1', 'CAT_OPER_TEL', NULL, '0', 1, '0', 'Movistar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_OPER_TEL_02', '2', 'CAT_OPER_TEL', NULL, '0', 2, '0', 'Claro');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_OPER_TEL_03', '3', 'CAT_OPER_TEL', NULL, '0', 3, '0', 'Cootel');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_VISITA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo visita no exitosa CP');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_VISITA_01', '1', 'CAT_NO_VISITA', NULL, '0', 1, '0', 'Casa Cerrada');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_VISITA_02', '2', 'CAT_NO_VISITA', NULL, '0', 2, '0', 'Participante ausente');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_VISITA_03', '3', 'CAT_NO_VISITA', NULL, '0', 3, '0', 'No se encontró la casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_VISITA_04', '4', 'CAT_NO_VISITA', NULL, '0', 4, '0', 'Padres Ausentes o Adultos Ausentes');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_VISITA_05', '5', 'CAT_NO_VISITA', NULL, '0', 5, '0', 'Se cambiaron de casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_VISITA_06', '6', 'CAT_NO_VISITA', NULL, '0', 6, '0', 'No se encontró tutor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_VISITA_07', '7', 'CAT_NO_VISITA', NULL, '0', 7, '0', 'Acude a Consulta Medica sin Tutor');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_VISITA_08', '8', 'CAT_NO_VISITA', NULL, '0', 8, '0', 'Fuera del pais');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_VISITA_998', '998', 'CAT_NO_VISITA', NULL, '0', 9, '0', 'Otro motivo');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DF', NULL, NULL, NULL, '1', 0, '0', 'Catalogo ubicacion dentro o fuera de casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DF_01', '1', 'CAT_DF', NULL, '0', 1, '0', 'Fuera');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DF_02', '2', 'CAT_DF', NULL, '0', 2, '0', 'Dentro');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DF_03', '3', 'CAT_DF', NULL, '0', 3, '0', 'Dentro y Fuera');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_COMPARTIDO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo llave agua compartida');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_COMPARTIDO_C', '1', 'CAT_COMPARTIDO', NULL, '0', 1, '0', 'Compartido');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_COMPARTIDO_N', '2', 'CAT_COMPARTIDO', NULL, '0', 2, '0', 'No compartido');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_PARED', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo material de paredes casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_PARED_01', '1', 'CAT_MAT_PARED', NULL, '0', 1, '0', 'Madera');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_PARED_02', '2', 'CAT_MAT_PARED', NULL, '0', 2, '0', 'Concreto');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_PARED_03', '3', 'CAT_MAT_PARED', NULL, '0', 3, '0', 'Plástico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_PARED_04', '4', 'CAT_MAT_PARED', NULL, '0', 4, '0', 'Cartón');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_PARED_05', '5', 'CAT_MAT_PARED', NULL, '0', 5, '0', 'Adobe');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_PARED_06', '6', 'CAT_MAT_PARED', NULL, '0', 6, '0', 'Zinc');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_PARED_998', '998', 'CAT_MAT_PARED', NULL, '0', 7, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_PISO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo material de piso casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_PISO_01', '1', 'CAT_MAT_PISO', NULL, '0', 1, '0', 'Concreto');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_PISO_02', '2', 'CAT_MAT_PISO', NULL, '0', 2, '0', 'Ladrillos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_PISO_03', '3', 'CAT_MAT_PISO', NULL, '0', 3, '0', 'Piso de tierra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_PISO_04', '4', 'CAT_MAT_PISO', NULL, '0', 4, '0', 'Cerámica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_PISO_998', '998', 'CAT_MAT_PISO', NULL, '0', 5, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_TECHO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipo material de techo casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_TECHO_01', '1', 'CAT_MAT_TECHO', NULL, '0', 1, '0', 'Zinc');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_TECHO_02', '2', 'CAT_MAT_TECHO', NULL, '0', 2, '0', 'Plástico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_TECHO_03', '3', 'CAT_MAT_TECHO', NULL, '0', 3, '0', 'Tejas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MAT_TECHO_998', '998', 'CAT_MAT_TECHO', NULL, '0', 4, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_FUN_AIRE', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tiempo de funcionamiento aire acondicionado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_FUN_AIRE_D', '1', 'CAT_FUN_AIRE', NULL, '0', 1, '0', 'Día');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_FUN_AIRE_N', '2', 'CAT_FUN_AIRE', NULL, '0', 2, '0', 'Noche');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_OTROS_SERVICIOS', NULL, NULL, NULL, '1', 0, '0', 'Catalogo otros servicios no básicos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_OTROS_SERVICIOS_1', '1', 'CAT_OTROS_SERVICIOS', NULL, '0', 1, '0', 'Cable pagado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_OTROS_SERVICIOS_2', '2', 'CAT_OTROS_SERVICIOS', NULL, '0', 2, '0', 'Netflix');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_OTROS_SERVICIOS_3', '3', 'CAT_OTROS_SERVICIOS', NULL, '0', 3, '0', 'Hulu');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_OTROS_SERVICIOS_4', '4', 'CAT_OTROS_SERVICIOS', NULL, '0', 4, '0', 'Amazon Video');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_OTROS_SERVICIOS_998', '998', 'CAT_OTROS_SERVICIOS', NULL, '0', 5, '0', 'Otros');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_COCINA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo frecuencia de uso cocina de lenia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_COCINA_1', '1', 'CAT_TIPO_COCINA', NULL, '0', 1, '0', 'Gas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_COCINA_2', '2', 'CAT_TIPO_COCINA', NULL, '0', 2, '0', 'Tropigas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIPO_COCINA_3', '3', 'CAT_TIPO_COCINA', NULL, '0', 3, '0', 'Eléctrica');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_FREC_COCINA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo frecuencia de uso cocina de lenia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_FREC_COCINA_D', '1', 'CAT_FREC_COCINA', NULL, '0', 1, '0', 'Diario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_FREC_COCINA_S', '2', 'CAT_FREC_COCINA', NULL, '0', 2, '0', 'Semanal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_FREC_COCINA_Q', '3', 'CAT_FREC_COCINA', NULL, '0', 3, '0', 'Quincenal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_FREC_COCINA_M', '4', 'CAT_FREC_COCINA', NULL, '0', 4, '0', 'Mensual');

/*encuesta participante*/
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NIV_EDU', NULL, NULL, NULL, '1', 0, '0', 'Catalogo niveles de educacion');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NIV_EDU_01', '1', 'CAT_NIV_EDU', NULL, '0', 1, '0', 'Ninguno');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NIV_EDU_02', '2', 'CAT_NIV_EDU', NULL, '0', 2, '0', 'Primaria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NIV_EDU_03', '3', 'CAT_NIV_EDU', NULL, '0', 3, '0', 'Secundaria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NIV_EDU_04', '4', 'CAT_NIV_EDU', NULL, '0', 4, '0', 'Técnico');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NIV_EDU_05', '5', 'CAT_NIV_EDU', NULL, '0', 5, '0', 'Universitario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NIV_EDU_05', '6', 'CAT_NIV_EDU', NULL, '0', 6, '0', 'Profesional');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NIV_EDU_06', '7', 'CAT_NIV_EDU', NULL, '0', 7, '0', 'No sabe');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_EMAN', NULL, NULL, NULL, '1', 0, '0', 'Catalogo razon de emancipación');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_EMAN_01', '1', 'CAT_RAZON_EMAN', NULL, '0', 1, '0', 'Embarazada');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_EMAN_02', '2', 'CAT_RAZON_EMAN', NULL, '0', 2, '0', 'Casad@');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_EMAN_03', '3', 'CAT_RAZON_EMAN', NULL, '0', 3, '0', 'Con hijos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_EMAN_998', '4', 'CAT_RAZON_EMAN', NULL, '0', 4, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIP_TRABAJO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tipos de trabajo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIP_TRABAJO_01', '1', 'CAT_TIP_TRABAJO', NULL, '0', 1, '0', 'Trabajo Formal');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TIP_TRABAJO_02', '2', 'CAT_TIP_TRABAJO', NULL, '0', 2, '0', 'Trabajo Informal');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_FREC_FUMA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo frecuencia de uso cocina de lenia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_FREC_FUMA_01', '1', 'CAT_FREC_FUMA', NULL, '0', 1, '0', 'Diariamente');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_FREC_FUMA_02', '2', 'CAT_FREC_FUMA', NULL, '0', 2, '0', 'No diario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_FREC_FUMA_03', '3', 'CAT_FREC_FUMA', NULL, '0', 3, '0', 'Semanalmente');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_FREC_FUMA_04', '4', 'CAT_FREC_FUMA', NULL, '0', 4, '0', 'Mensualmente');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_FREC_FUMA_05', '5', 'CAT_FREC_FUMA', NULL, '0', 5, '0', 'De vez en cuando');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SND', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Si/No/Desconocido');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SND_01', '1', 'CAT_SND', NULL, '0', 1, '0', 'Si');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SND_02', '0', 'CAT_SND', NULL, '0', 2, '0', 'No');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SND_999', '9', 'CAT_SND', NULL, '0', 3, '0', 'Desconocido');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DONDEASISTE', NULL, NULL, NULL, '1', 0, '0', 'Catalogo dónde asiste por enfermedades');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DONDEASISTE_01', '1', 'CAT_DONDEASISTE', NULL, '0', 1, '0', 'Centro de Salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DONDEASISTE_02', '2', 'CAT_DONDEASISTE', NULL, '0', 2, '0', 'Puesto de Salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DONDEASISTE_03', '3', 'CAT_DONDEASISTE', NULL, '0', 3, '0', 'Hospital Público');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DONDEASISTE_04', '4', 'CAT_DONDEASISTE', NULL, '0', 4, '0', 'Hospital Privado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DONDEASISTE_05', '5', 'CAT_DONDEASISTE', NULL, '0', 5, '0', 'Médico Privado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DONDEASISTE_06', '6', 'CAT_DONDEASISTE', NULL, '0', 6, '0', 'Seguro Social');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DONDEASISTE_998', '998', 'CAT_DONDEASISTE', NULL, '0', 7, '0', 'Otro');


INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TURNO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo turnos de estudio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TURNO_01', '1', 'CAT_TURNO', NULL, '0', 1, '0', 'AM');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TURNO_02', '2', 'CAT_TURNO', NULL, '0', 2, '0', 'PM');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TURNO_03', '3', 'CAT_TURNO', NULL, '0', 3, '0', 'SAB');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TURNO_04', '4', 'CAT_TURNO', NULL, '0', 4, '0', 'DOM');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_GRD_EDU', NULL, NULL, NULL, '1', 0, '0', 'Catalogo grados escolares');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_GRD_EDU_01', '1', 'CAT_GRD_EDU', NULL, '0', 1, '0', 'Preescolar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_GRD_EDU_02', '2', 'CAT_GRD_EDU', NULL, '0', 2, '0', 'Primaria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_GRD_EDU_03', '3', 'CAT_GRD_EDU', NULL, '0', 3, '0', 'Secundaria');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_GRD_EDU_04', '4', 'CAT_GRD_EDU', NULL, '0', 4, '0', 'Universidad');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_CUIDAN_NINO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo donde cuidad al niño si no va a la escuela');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_CUIDAN_NINO_01', '1', 'CAT_CUIDAN_NINO', NULL, '0', 3, '0', 'CDI');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_CUIDAN_NINO_02', '2', 'CAT_CUIDAN_NINO', NULL, '0', 2, '0', 'Vecino');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_CUIDAN_NINO_03', '3', 'CAT_CUIDAN_NINO', NULL, '0', 1, '0', 'En su casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_CUIDAN_NINO_998', '998', 'CAT_CUIDAN_NINO', NULL, '0', 4, '0', 'Otro lugar');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VIVE_NINO', NULL, NULL, NULL, '1', 0, '0', 'Catalogo con quien vive el niño');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VIVE_NINO_01', '1', 'CAT_VIVE_NINO', NULL, '0', 1, '0', 'Madre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VIVE_NINO_02', '2', 'CAT_VIVE_NINO', NULL, '0', 2, '0', 'Padre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VIVE_NINO_03', '3', 'CAT_VIVE_NINO', NULL, '0', 3, '0', 'Ambos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_VIVE_NINO_998', '998', 'CAT_VIVE_NINO', NULL, '0', 4, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TMP_FIEBRE', NULL, NULL, NULL, '1', 0, '0', 'Catalogo tiempo duró la última fiebre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TMP_FIEBRE_01', '1', 'CAT_TMP_FIEBRE', NULL, '0', 1, '0', 'Menos de 24 Horas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_TMP_FIEBRE_02', '0', 'CAT_TMP_FIEBRE', NULL, '0', 2, '0', 'Más de 24 horas');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_CONSULTA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo dónde asiste a consulta');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_CONSULTA_01', '1', 'CAT_LUGAR_CONSULTA', NULL, '0', 1, '0', 'Seguro social - Clinical Previsional');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_CONSULTA_02', '2', 'CAT_LUGAR_CONSULTA', NULL, '0', 2, '0', 'CS Edgar Lang');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_CONSULTA_03', '3', 'CAT_LUGAR_CONSULTA', NULL, '0', 3, '0', 'Hospital');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_CONSULTA_04', '4', 'CAT_LUGAR_CONSULTA', NULL, '0', 4, '0', 'Médicos Privado');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_CONSULTA_998', '998', 'CAT_LUGAR_CONSULTA', NULL, '0', 5, '0', 'Otro');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_CONSULTA_05', '5', 'CAT_LUGAR_CONSULTA', NULL, '0', 6, '0', 'Puesto Médico');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_AUTOMED_FIEBRE', NULL, NULL, NULL, '1', 0, '0', 'Catalogo automedicó fiebre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_AUTOMED_FIEBRE_01', '1', 'CAT_AUTOMED_FIEBRE', NULL, '0', 1, '0', 'Acetaminofén');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_AUTOMED_FIEBRE_02', '2', 'CAT_AUTOMED_FIEBRE', NULL, '0', 2, '0', 'Aspirina');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_AUTOMED_FIEBRE_03', '3', 'CAT_AUTOMED_FIEBRE', NULL, '0', 3, '0', 'Antibióticos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_AUTOMED_FIEBRE_998', '998', 'CAT_AUTOMED_FIEBRE', NULL, '0', 5, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS', NULL, NULL, NULL, '1', 0, '0', 'Catalogo no acudió al centro de salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS_01', '1', 'CAT_NO_ACUDIO_CS', NULL, '0', 1, '0', 'Era de noche y no pudo llevarlo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS_02', '2', 'CAT_NO_ACUDIO_CS', NULL, '0', 2, '0', 'Estaba grave y fue directo al hospital');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS_03', '3', 'CAT_NO_ACUDIO_CS', NULL, '0', 3, '0', 'La fiebre duró poco tiempo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS_04', '4', 'CAT_NO_ACUDIO_CS', NULL, '0', 4, '0', 'No había quien lo llevara');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS_05', '5', 'CAT_NO_ACUDIO_CS', NULL, '0', 5, '0', 'No pensó que fuera necesario');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS_06', '6', 'CAT_NO_ACUDIO_CS', NULL, '0', 6, '0', 'No sabía que hay servicio las 24horas');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_NO_ACUDIO_CS_998', '998', 'CAT_NO_ACUDIO_CS', NULL, '0', 7, '0', 'Otras razones');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_CENTRO_SALUD', NULL, NULL, NULL, '1', 0, '0', 'Catalogo centros de salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_CENTRO_SALUD_01', '1', 'CAT_CENTRO_SALUD', NULL, '0', 1, '0', 'CS Edgar Lang');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_CENTRO_SALUD_998', '998', 'CAT_CENTRO_SALUD', NULL, '0', 2, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MESES', NULL, NULL, NULL, '1', 0, '0', 'Catalogo meses del año');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MESES_01', '01', 'CAT_MESES', NULL, '0', 1, '0', 'Enero');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MESES_02', '02', 'CAT_MESES', NULL, '0', 2, '0', 'Febrero');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MESES_03', '03', 'CAT_MESES', NULL, '0', 3, '0', 'Marzo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MESES_04', '04', 'CAT_MESES', NULL, '0', 4, '0', 'Abril');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MESES_05', '05', 'CAT_MESES', NULL, '0', 5, '0', 'Mayo');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MESES_06', '06', 'CAT_MESES', NULL, '0', 6, '0', 'Junio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MESES_07', '07', 'CAT_MESES', NULL, '0', 7, '0', 'Julio');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MESES_08', '08', 'CAT_MESES', NULL, '0', 8, '0', 'Agosto');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MESES_09', '09', 'CAT_MESES', NULL, '0', 9, '0', 'Septiembre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MESES_10', '10', 'CAT_MESES', NULL, '0', 10, '0', 'Octubre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MESES_11', '11', 'CAT_MESES', NULL, '0', 11, '0', 'Noviembre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_MESES_12', '12', 'CAT_MESES', NULL, '0', 12, '0', 'Diciembre');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DURO_ENROJECI', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Cuántos días duró el enrojecimiento');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DURO_ENROJECI_01', '1', 'CAT_DURO_ENROJECI', NULL, '0', 1, '0', '1 dia');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DURO_ENROJECI_02', '2', 'CAT_DURO_ENROJECI', NULL, '0', 2, '0', '2 a 4 días');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_DURO_ENROJECI_03', '3', 'CAT_DURO_ENROJECI', NULL, '0', 3, '0', '5 a más días');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_QUIEN_FUMA', NULL, NULL, NULL, '1', 0, '0', 'Catalogo quienes fuman en la casa');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_QUIEN_FUMA_01', '1', 'CAT_QUIEN_FUMA', NULL, '0', 1, '0', 'Madre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_QUIEN_FUMA_02', '2', 'CAT_QUIEN_FUMA', NULL, '0', 2, '0', 'Padre');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_QUIEN_FUMA_998', '998', 'CAT_QUIEN_FUMA', NULL, '0', 3, '0', 'Otros');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_PUESTO_SALUD', NULL, NULL, NULL, '1', 0, '0', 'Catalogo puesto de salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_PUESTO_SALUD_01', '1', 'CAT_PUESTO_SALUD', NULL, '0', 1, '0', 'P/S 1');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_PUESTO_SALUD_02', '2', 'CAT_PUESTO_SALUD', NULL, '0', 2, '0', 'P/S 2');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_PUESTO_SALUD_998', '998', 'CAT_PUESTO_SALUD', NULL, '0', 3, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_DIAG', NULL, NULL, NULL, '1', 0, '0', 'Catalogo lugar de diagnostico enfermedades');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_DIAG_01', '1', 'CAT_LUGAR_DIAG', NULL, '0', 1, '0', 'Centro Salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_DIAG_02', '2', 'CAT_LUGAR_DIAG', NULL, '0', 2, '0', 'Puesto Salud');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_DIAG_03', '3', 'CAT_LUGAR_DIAG', NULL, '0', 3, '0', 'Hospital');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_LUGAR_DIAG_04', '4', 'CAT_LUGAR_DIAG', NULL, '0', 4, '0', 'Médico privado');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_HOSPITAL', NULL, NULL, NULL, '1', 0, '0', 'Catalogo de hospitales');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_HOSPITAL_01', '1', 'CAT_HOSPITAL', NULL, '0', 1, '0', 'Hospital La Mascota');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_HOSPITAL_02', '2', 'CAT_HOSPITAL', NULL, '0', 2, '0', 'Hospital Militar');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_HOSPITAL_03', '3', 'CAT_HOSPITAL', NULL, '0', 3, '0', 'Hospital Velez Paiz');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_HOSPITAL_04', '4', 'CAT_HOSPITAL', NULL, '0', 4, '0', 'Hospital Central de Managua');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_HOSPITAL_05', '5', 'CAT_HOSPITAL', NULL, '0', 5, '0', 'Hospital Salud Integral');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_HOSPITAL_06', '6', 'CAT_HOSPITAL', NULL, '0', 6, '0', 'Hospital Cruz Azul');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_HOSPITAL_07', '7', 'CAT_HOSPITAL', NULL, '0', 7, '0', 'Hospital Carlos Roberto Huembes');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_HOSPITAL_998', '998', 'CAT_HOSPITAL', NULL, '0', 8, '0', 'Otro');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_PINCHAZOS', NULL, NULL, NULL, '1', 0, '0', 'Catalogo numero de pinchazos');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_PINCHAZOS_01', '0', 'CAT_PINCHAZOS', NULL, '0', 1, '0', '0');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_PINCHAZOS_02', '1', 'CAT_PINCHAZOS', NULL, '0', 2, '0', '1');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_PINCHAZOS_03', '2', 'CAT_PINCHAZOS', NULL, '0', 3, '0', '2');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_PINCHAZOS_04', '3', 'CAT_PINCHAZOS', NULL, '0', 4, '0', '3');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_PINCHAZOS_05', '4', 'CAT_PINCHAZOS', NULL, '0', 5, '0', '4');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_PINCHAZOS_06', '5', 'CAT_PINCHAZOS', NULL, '0', 6, '0', '5');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_MX', NULL, NULL, NULL, '1', 0, '0', 'Catalogo razón no se toma muestra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_MX_01', '1', 'CAT_RAZON_NO_MX', NULL, '0', 1, '0', 'Muestra difícil');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_MX_02', '2', 'CAT_RAZON_NO_MX', NULL, '0', 2, '0', 'Se descanalizó');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_MX_03', '3', 'CAT_RAZON_NO_MX', NULL, '0', 3, '0', 'Se pinchó mas de 2 veces');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_MX_04', '4', 'CAT_RAZON_NO_MX', NULL, '0', 4, '0', 'Participante o tutor no aceptó tomar muestra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_MX_05', '5', 'CAT_RAZON_NO_MX', NULL, '0', 5, '0', 'Participante o tutor después de pinchadazo no desea que se le tome muestra');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_RAZON_NO_MX_998', '998', 'CAT_RAZON_NO_MX', NULL, '0', 6, '0', 'Otra razón');


INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('select', '0', '0', '0', 'Seleccione');

/**HOJA CLINICA**/
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('fingering', '0', '0', '0', 'Digitación');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('clinical_sheet', '0', '0', '0', 'Hoja Clinica');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('search_participant', '0', '0', '0', 'Buscar participante');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('search_participant_holder', '0', '0', '0', 'Ingresar código de participante');

INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('datos_personales', '0', '0', '0', 'Datos Personales');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('nombre', '0', '0', '0', 'Nombre:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('fecha_nacimiento', '0', '0', '0', 'Fecha de Nacimiento:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('edad', '0', '0', '0', 'Edad:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('sexo', '0', '0', '0', 'Sexo:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('encabezado', '0', '0', '0', 'Encabezado');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('fecha_consulta', '0', '0', '0', 'Fecha Consulta:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('hora_consulta', '0', '0', '0', 'Hora Consulta:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('datos_enfermeria', '0', '0', '0', 'Datos de enfermería');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('peso', '0', '0', '0', 'Peso (Kg):');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('talla', '0', '0', '0', 'Talla (cm):');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('presion_arterial', '0', '0', '0', 'Presión arterial:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('frecuencia_cardiaca', '0', '0', '0', 'Frecuencia cardiaca:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('temperatura', '0', '0', '0', 'Temperatura:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('saturacion_oxigeno', '0', '0', '0', 'Saturación de oxígeno:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('datos_medico', '0', '0', '0', 'Datos para llenar el médico');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('hora_inicio_consulta', '0', '0', '0', 'Hora de inicio de la consulta:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('consulta', '0', '0', '0', 'Consulta:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('lugar_consulta', '0', '0', '0', 'Lugar de consulta:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('signos_vitales', '0', '0', '0', 'Signos vitales del paciente');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('frecuencia_respiratoria', '0', '0', '0', 'Frecuencia respiratoria:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('fecha_inicio_sintomas', '0', '0', '0', 'Fecha de inicio los síntomas:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('fecha_inicio_fiebre', '0', '0', '0', 'Fecha de inicio la fiebre:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ultimo_dia_fiebre', '0', '0', '0', 'Ultimo día de fiebre:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('hora', '0', '0', '0', 'Hora:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ultima_dosis_antiperetico', '0', '0', '0', 'Ultima dosis de antiperetico:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('general', '0', '0', '0', 'General');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('fiebre', '0', '0', '0', 'Fiebre');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('anormalmente_somnoliento', '0', '0', '0', 'Anormalmente somnoliento');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('mal_estado_general', '0', '0', '0', 'Mal estado general');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('perdida_consciencia', '0', '0', '0', 'Perdida de la Consciencia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('inquieto_irritable', '0', '0', '0', 'Inquieto irritable');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('convulsiones', '0', '0', '0', 'Convulsiones');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('letargia', '0', '0', '0', 'Letargia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('cabeza', '0', '0', '0', 'Cabeza');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dolor_cabeza', '0', '0', '0', 'Dolor de cabeza');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('conjuntivitis', '0', '0', '0', 'Conjuntivitis');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('hemorragia_subconjuntival', '0', '0', '0', 'Hemorragia Subconjuntival');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dolor_retroocular', '0', '0', '0', 'Dolor Retroocular');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('garganta', '0', '0', '0', 'Garganta');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dolor_garganta', '0', '0', '0', 'Dolor de garganta');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('eritema', '0', '0', '0', 'Eritema');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('adenopatias_cervicales', '0', '0', '0', 'Adenopatías cervicales');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('exudado', '0', '0', '0', 'Exudado');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('petequias_mucosa', '0', '0', '0', 'Petequias en mucosa');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('respiratorio', '0', '0', '0', 'Respiratorio');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('tos', '0', '0', '0', 'Tos');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rinorrea', '0', '0', '0', 'Rinorrea');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('congestion_nasal', '0', '0', '0', 'Congestión nasal');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('otalgia', '0', '0', '0', 'Otalgia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('aleteo_nasal', '0', '0', '0', 'Aleteo nasal');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('respiracion_rapida', '0', '0', '0', 'Respiración rápida');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('estridor_reposo', '0', '0', '0', 'Estridor en reposo');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('tirajes_subcostales', '0', '0', '0', 'Tirajes subcostales');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('sibilancia', '0', '0', '0', 'Sibilancia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('crepitos', '0', '0', '0', 'Crépitos');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('roncos', '0', '0', '0', 'Roncos');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('disnea', '0', '0', '0', 'Disnea');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('gastrointestinal', '0', '0', '0', 'Gastrointestinal');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('poco_apetito', '0', '0', '0', 'Poco apetito');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('nauseas', '0', '0', '0', 'Náuseas');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('vomito', '0', '0', '0', 'Vómito');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('vomitos_ultimas_12_hrs', '0', '0', '0', 'Numero de vómitos en las últimas 12 hrs');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('diarrea', '0', '0', '0', 'Diarrea');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dolor_abdominal', '0', '0', '0', 'Dolor abdominal');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('hepatomegalia', '0', '0', '0', 'Hepatomegalia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('osteomuscular', '0', '0', '0', 'Osteomuscular');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('artralgia', '0', '0', '0', 'Artralgia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('mialgia', '0', '0', '0', 'Mialgia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('lumbalgia', '0', '0', '0', 'Lumbalgia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dolor_cuello', '0', '0', '0', 'Dolor de cuello');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('edema', '0', '0', '0', 'Edema');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('cutaneo', '0', '0', '0', 'Cutáneo');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rash_localizado', '0', '0', '0', 'Rash localizado');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rash_generalizado', '0', '0', '0', 'Rash generalizado');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rash_eritematoso', '0', '0', '0', 'Rash eritematoso');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rash_macular', '0', '0', '0', 'Rash Macular');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rash_papular', '0', '0', '0', 'Rash papular');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('piel_moteada', '0', '0', '0', 'Piel moteada');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('rubor_facial', '0', '0', '0', 'Rubor facial');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('cianosis_central', '0', '0', '0', 'Cianosis central');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ictericia', '0', '0', '0', 'Ictericia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('estado_nutricional', '0', '0', '0', 'Estado nutricional');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('imc', '0', '0', '0', 'IMC');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('obeso', '0', '0', '0', 'Obeso');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('sobrepeso', '0', '0', '0', 'Sobrepeso');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('sospecha_problema', '0', '0', '0', 'Sospecha de problema');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('normal', '0', '0', '0', 'Normal');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('bajo_peso', '0', '0', '0', 'Bajo peso');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('bajo_peso_severo', '0', '0', '0', 'Bajo peso severo');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('categoria', '0', '0', '0', 'Categoría');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('cambio_categoria', '0', '0', '0', 'Cambio de categoría:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('completar_categoria_a_b', '0', '0', '0', 'Completar si es categoría A y B');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('manifestaciones_hemorragicas', '0', '0', '0', 'Manifestaciones hemorrágicas');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('prueba_torniquete', '0', '0', '0', 'Prueba del torniquete');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('petequias_10', '0', '0', '0', 'Petequias ≥10');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('petequias_20', '0', '0', '0', 'Petequias ≥20');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('piel_extremidades_frias', '0', '0', '0', 'Piel y extremidades frías');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('palidez_extremidades', '0', '0', '0', 'Palidez en extremidades');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('epistaxis', '0', '0', '0', 'Epistaxis');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('gingivorragia', '0', '0', '0', 'Gingivorragia');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('petequias_espontaneas', '0', '0', '0', 'Petequias espontaneas');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('llenado_capilar_segundos', '0', '0', '0', 'Llenado capilar ˃2 segundos');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('cianosis', '0', '0', '0', 'Cianosis');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('hipermenorrea', '0', '0', '0', 'Hipermenorrea');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('hematemesis', '0', '0', '0', 'Hematemesis');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('hemoconcentracion', '0', '0', '0', 'Hemoconcentración');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('preguntas_todos', '0', '0', '0', 'Preguntas para todos los pacientes');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('hospitalizado_ultimo_anio', '0', '0', '0', '¿Ha sido hospitalizado en el último año?');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('especifique_unidad_salud_hosp', '0', '0', '0', 'Si es un SI especifique nombre de la unidad de salud:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('transfusion_sangre_ultimo_anio', '0', '0', '0', '¿Recibió transfusión de sangre en el último año?');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('si_especifique', '0', '0', '0', 'Si es un SI especifique:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('medicamento_momento', '0', '0', '0', '¿Está tomando medicamento en este momento los últimos 6 meses');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('especifique_medicamento', '0', '0', '0', 'Si es SI especifique el medicamento:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('examenes_laboratorio', '0', '0', '0', 'Exámenes del laboratorio');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('bhc', '0', '0', '0', 'BHC');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('serologia_arbovirus', '0', '0', '0', 'Serología Arbovirus');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('gota_gruesa', '0', '0', '0', 'Gota gruesa');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('ego', '0', '0', '0', 'EGO');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('egh', '0', '0', '0', 'EGH');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('otros', '0', '0', '0', 'OTROS');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('especifique', '0', '0', '0', 'Especifique:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('tratamiento', '0', '0', '0', 'Tratamiento');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('acetaminofen', '0', '0', '0', 'Acetaminofén');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('amoxicilina', '0', '0', '0', 'Amoxicilina');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('dicloxacilina', '0', '0', '0', 'Dicloxacilina');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('penicilina', '0', '0', '0', 'Penicilina');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('furazolidona', '0', '0', '0', 'Furazolidona');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('metronidazol_tinidazol', '0', '0', '0', 'Metronidazol/Tinidazol');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('albendazol_mebendazol', '0', '0', '0', 'Albendazol/Mebendazol');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('suero_oral', '0', '0', '0', 'Suero oral');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('otro_tratamiento', '0', '0', '0', 'Otro tratamiento:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('planes', '0', '0', '0', 'Planes:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('historia_clínica', '0', '0', '0', 'Historia clínica:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('diagnostico', '0', '0', '0', 'Diagnóstico:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('tel_emergencia', '0', '0', '0', 'Tel. Emergencia:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('proxima_cita', '0', '0', '0', 'Próxima cita:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('medico', '0', '0', '0', 'Médico:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('fecha', '0', '0', '0', 'Fecha:');
INSERT INTO `mensajes` (`messageKey`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('enfermeria', '0', '0', '0', 'Enfermería:');

INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SND_HC', NULL, NULL, NULL, '1', 0, '0', 'Catalogo Si/No/Desconocido Hoja Clinica');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SND_HC_01', 'S', 'CAT_SND_HC', NULL, '0', 1, '0', 'S');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SND_HC_02', 'N', 'CAT_SND_HC', NULL, '0', 2, '0', 'N');
INSERT INTO `mensajes` (`messageKey`, `catKey`, `catRoot`, `en`, `isCat`, `orden`, `catPasive`, `es`) VALUES ('CAT_SND_HC_999', 'D', 'CAT_SND_HC', NULL, '0', 3, '0', 'D');