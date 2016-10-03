CREATE TABLE IF NOT EXISTS `c_security_permission` (
  `id` varchar(255) NOT NULL,
  `permission_label` varchar(255) NOT NULL,
  `permission_value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_k4suda9cvcsoikdgquscypmt6` (`permission_value`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `c_security_role` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hliaoojt6u3a11d8svttju10l` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `c_security_role_permission` (
  `id_role` varchar(255) NOT NULL,
  `id_permission` varchar(255) NOT NULL,
  PRIMARY KEY (`id_role`,`id_permission`),
  KEY `FK_d89p0a0x87scb5s3830jx7xq0` (`id_permission`),
  CONSTRAINT `FK_fvynt2q4rxk27e0bxuon50tp4` FOREIGN KEY (`id_role`) REFERENCES `c_security_role` (`id`),
  CONSTRAINT `FK_d89p0a0x87scb5s3830jx7xq0` FOREIGN KEY (`id_permission`) REFERENCES `c_security_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `m_category` (
  `id` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_805u58snn6h9tr2jcao7libdu` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `m_loket` (
  `id` varchar(255) NOT NULL,
  `nomor_loket` int(11) NOT NULL,
  `id_category` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kdw3h16608yjtegmqu0ud4hf5` (`nomor_loket`),
  KEY `FK_bfdtpow629veh96jl7dpgn7ag` (`id_category`),
  CONSTRAINT `FK_bfdtpow629veh96jl7dpgn7ag` FOREIGN KEY (`id_category`) REFERENCES `m_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `c_security_user` (
  `id` varchar(255) NOT NULL,
  `active` bit(1) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `id_loket` varchar(255) DEFAULT NULL,
  `id_role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_at8if7a9lnl90wxllb9divpdf` (`username`),
  KEY `FK_oi1dd3h3m0m8p2xqq6ki23gh3` (`id_loket`),
  KEY `FK_my18sie96bgbncypva3fxboxy` (`id_role`),
  CONSTRAINT `FK_my18sie96bgbncypva3fxboxy` FOREIGN KEY (`id_role`) REFERENCES `c_security_role` (`id`),
  CONSTRAINT `FK_oi1dd3h3m0m8p2xqq6ki23gh3` FOREIGN KEY (`id_loket`) REFERENCES `m_loket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `c_security_user_password` (
  `id_user` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  PRIMARY KEY (`id_user`),
  CONSTRAINT `FK_9a26m4sjx4ddi35n3w0s6b5os` FOREIGN KEY (`id_user`) REFERENCES `c_security_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `m_poli` (
  `id` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qy81ge6f8xaceyhpyymvdhsyp` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `runnum` (
  `id` varchar(255) NOT NULL,
  `run_date` varchar(255) NOT NULL,
  `lastnumber` bigint(20) NOT NULL,
  `run_month` varchar(255) NOT NULL,
  `run_prefix` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `run_year` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qtnff32gosvs4k8s6by1jqas7` (`run_date`,`run_month`,`run_year`,`run_prefix`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `m_jadwal_dokter` (
  `id` varchar(255) NOT NULL,
  `kuota_jumat` int(11) NOT NULL,
  `kuota_kamis` int(11) NOT NULL,
  `kuota_minggu` int(11) NOT NULL,
  `kuota_rabu` int(11) NOT NULL,
  `kuota_sabtu` int(11) NOT NULL,
  `kuota_selasa` int(11) NOT NULL,
  `kuota_senin` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `id_poli` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_itaena7o6t8teu8ry8a3qo6g7` (`code`),
  KEY `FK_sg5solicf7m5kw4jmx430mw06` (`id_poli`),
  CONSTRAINT `FK_sg5solicf7m5kw4jmx430mw06` FOREIGN KEY (`id_poli`) REFERENCES `m_poli` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `t_kuota` (
  `id` varchar(255) NOT NULL,
  `current_kuota` int(11) NOT NULL,
  `kuota_date` date NOT NULL,
  `maximum_kuota` int(11) NOT NULL,
  `nama_dokter` varchar(255) NOT NULL,
  `description_dokter` varchar(255) NOT NULL,
  `code_dokter` varchar(255) NOT NULL,
  `id_poli` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cedsi46x2ngu7rc9g47s4rvsa` (`id_poli`),
  CONSTRAINT `FK_cedsi46x2ngu7rc9g47s4rvsa` FOREIGN KEY (`id_poli`) REFERENCES `m_poli` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `t_log_antrian` (
  `id` varchar(255) NOT NULL,
  `antrian_date` varchar(255) NOT NULL,
  `timestamp` datetime NOT NULL,
  `jenis_loket` varchar(255) NOT NULL,
  `nomor_antrian` varchar(255) NOT NULL,
  `nomor_pasien` varchar(255) NOT NULL,
  `nomor_loket` int(11) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `id_kuota` varchar(255),
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fkkhvtkmwjybkpef32flp4nli` (`nomor_antrian`),
  KEY `FK_jrrcfni173ooo9dg24ha9w6hs` (`id_kuota`),
  CONSTRAINT `FK_jrrcfni173ooo9dg24ha9w6hs` FOREIGN KEY (`id_kuota`) REFERENCES `t_kuota` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `t_log_antrian_sound` (
  `id` varchar(255) NOT NULL,
  `nomor_antrian` varchar(255) NOT NULL,
  `status` bit(1) NOT NULL,
  `terbilang` varchar(255) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS  `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS  `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS  `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
