INSERT INTO `c_security_permission` (`id`, `permission_label`, `permission_value`) VALUES
('SYSTEM', 'Page System', 'ROLE_SYSTEM'),
('MASTER', 'Page Master', 'ROLE_MASTER'),
('LOKET', 'Page Loket', 'ROLE_LOKET'),
('REPORT', 'Page Report', 'ROLE_REPORT');

INSERT INTO `c_security_role` (`id`, `description`, `name`) VALUES
('ADMINISTRATOR', 'Application Administrator', 'Administrator'),
('USER_LOKET', 'User Loket', 'Loket');

INSERT INTO `c_security_role_permission` (`id_role`, `id_permission`) VALUES
('ADMINISTRATOR', 'SYSTEM'),
('ADMINISTRATOR', 'MASTER'),
('ADMINISTRATOR', 'LOKET'),
('ADMINISTRATOR', 'REPORT');


INSERT INTO `c_security_user` (`id`, `active`, `fullname`, `username`, `id_role`, `address`, `email`, `phone`, `id_loket`) VALUES
('1', b'1', 'Super User', 'admin', 'ADMINISTRATOR', '', '', '', NULL);

INSERT INTO `c_security_user_password` (`id_user`, `user_password`) VALUES
('1', '$2a$08$LS3sz9Ft014MNaIGCEyt4u6VflkslOW/xosyRbinIF9.uaVLpEhB6');

INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES
('management', 'antrian-server', 'antrian123', 'read,write', 'implicit', 'http://0.0.0.0:9000/', NULL, 86400, NULL, NULL, 'true');