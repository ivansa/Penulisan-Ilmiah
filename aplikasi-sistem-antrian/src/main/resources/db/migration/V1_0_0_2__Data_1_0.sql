INSERT INTO `c_security_permission` (`id`, `permission_label`, `permission_value`) VALUES
('SYSTEM', 'Page System', 'ROLE_SYSTEM'),
('MASTER', 'Page Master', 'ROLE_MASTER'),
('LOKET', 'Page Loket', 'ROLE_LOKET'),
('REPORT', 'Page Report', 'ROLE_REPORT'),
('PENDAFTARAN', 'Page Pendaftaran', 'ROLE_PENDAFTARAN'),
('SCREEN_TV', 'Page Screen Tv', 'ROLE_SCREEN_TV'),
('USER_LOGGED_IN', 'Get User Logged In Data', 'ROLE_USER_LOGGED_IN'),
('GENERATE', 'Page Report', 'ROLE_GENERATE');

INSERT INTO `c_security_role` (`id`, `description`, `name`) VALUES
('ADMINISTRATOR', 'Application Administrator', 'Administrator'),
('PENDAFTARAN', 'Menu Pendaftaran', 'Pendaftaran'),
('SCREEN_MONITOR', 'Layar Pemanggilan', 'Screen'),
('USER_LOKET', 'User Loket', 'Loket');

INSERT INTO `c_security_role_permission` (`id_role`, `id_permission`) VALUES
('ADMINISTRATOR', 'SYSTEM'),
('ADMINISTRATOR', 'MASTER'),
('ADMINISTRATOR', 'REPORT'),
('ADMINISTRATOR', 'GENERATE'),
('ADMINISTRATOR', 'USER_LOGGED_IN'),
('USER_LOKET', 'LOKET'),
('USER_LOKET', 'USER_LOGGED_IN'),
('SCREEN_MONITOR', 'SCREEN_TV'),
('SCREEN_MONITOR', 'USER_LOGGED_IN'),
('PENDAFTARAN', 'PENDAFTARAN');


INSERT INTO `c_security_user` (`id`, `active`, `fullname`, `username`, `id_role`, `address`, `email`, `phone`, `id_loket`) VALUES
('1', b'1', 'Super User', 'admin', 'ADMINISTRATOR', '', '', '', NULL),
('2', b'1', 'User Pendaftaran', 'pendaftaran', 'PENDAFTARAN', '', '', '', NULL);

INSERT INTO `c_security_user_password` (`id_user`, `user_password`) VALUES
('1', '$2a$08$LS3sz9Ft014MNaIGCEyt4u6VflkslOW/xosyRbinIF9.uaVLpEhB6'),
('2', '$2a$08$LS3sz9Ft014MNaIGCEyt4u6VflkslOW/xosyRbinIF9.uaVLpEhB6');

INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES
('management', 'antrian-server', 'antrian123', 'read,write', 'implicit', 'http://0.0.0.0:9000/', NULL, 86400, NULL, NULL, 'true'),
('pendaftaran', 'antrian-server', 'antrian123', 'read,write', 'implicit', 'http://0.0.0.0:9100/', NULL, 86400, NULL, NULL, 'true');